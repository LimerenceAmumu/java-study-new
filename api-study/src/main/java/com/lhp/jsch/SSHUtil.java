package com.lhp.jsch;

import cn.hutool.core.util.StrUtil;
import com.jcraft.jsch.*;
import lombok.Getter;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * @author chenxiongfeng@supcon.com
 * @since 2022-10-10 16:17
 */
@Getter
public class SSHUtil {
    private static final Integer TIME_OUT = 60000;
    private static final String ENCODING = "UTF-8";
    private Session session;
    private String username;
    private String password;
    private String host;
    private Integer port;
    private ChannelSftp channelSftp;
    private ChannelExec channelExec;
    private InputStream inputStream;

    public SSHUtil(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public SSHUtil(String host, Integer port, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public SSHUtil(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.port = 22;
    }

    @Override
    public String toString() {
        return "SSHUtil{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                '}';
    }

    /**
     * 设置连接信息
     *
     * @throws JSchException
     */
    public void getConnect() throws JSchException {
        getConnect(null);
    }

    public void getConnect(String priKeyBasePath) throws JSchException {
        JSch jsch = new JSch();
        if (!StrUtil.isEmpty(priKeyBasePath)) {
            jsch.addIdentity(priKeyBasePath);
        }
        session = jsch.getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect(TIME_OUT);
    }

    public void disConnect() {
        if (session != null) {
            session.disconnect();
        }
    }

    public boolean isConnected() {
        if (session != null) {
            return session.isConnected();
        }
        return false;
    }

    /**
     * ssh 命令通道
     *
     * @param command
     * @return
     * @throws JSchException
     * @throws IOException
     */
    public String exeCommand(String command, Boolean isResponse) throws JSchException, IOException {
        channelExec = (ChannelExec) session.openChannel("exec");
        if (isResponse) {
            inputStream = channelExec.getInputStream();
        }
        channelExec.setCommand(command);
        channelExec.setErrStream(System.err);
        channelExec.connect();
        return isResponse ? IOUtils.toString(inputStream, ENCODING) : null;
    }

    public String exeCommand(String command) throws JSchException, IOException {
        exeCommandNoResponse(command);
        return null;
    }

    public void exeCommandNoResponse(String command) throws JSchException, IOException {
        channelExec = (ChannelExec) session.openChannel("exec");
        channelExec.setCommand(command);
        channelExec.setErrStream(System.err);
        channelExec.connect();
    }

    public void closeExeCommand() {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {

        }
        channelExec.disconnect();
    }

    public void close() {
        if (inputStream != null) {
            closeExeCommand();
        }
        session.disconnect();
    }

    /**
     * 上传文件
     *
     * @param dir         文件目录
     * @param fileName    文件名称
     * @param inputStream 文件流
     *                    throws JSchException
     *                    throws SftpException
     *                    throws UnsupportedEncodingException
     */
    public void uploadFile(String dir, String fileName, InputStream inputStream)
            throws JSchException, SftpException, UnsupportedEncodingException {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            channelSftp.cd(dir);
            channelSftp.put(inputStream, new String(fileName.getBytes(), ENCODING));
            channelSftp.disconnect();
        } finally {
            if (channelSftp != null) {
                channelSftp.disconnect();
            }
        }

    }
}