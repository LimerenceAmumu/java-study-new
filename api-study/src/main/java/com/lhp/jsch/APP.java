package com.lhp.jsch;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : lihp
 * @Description :
 * @date : 2023/2/21 16:54
 */
@Slf4j
public class APP {

    @SneakyThrows
    public static void main(String[] args) {

        String host = "192.168.110.41";
        Integer port = 22;
        SSHUtil sshUtil = new SSHUtil(host, port, "wangwj", "");
        sshUtil.getConnect("/Users/lihepeng/programLife/wangwj-rsa");
        String s = sshUtil.exeCommand("ls \n", true);
        log.info("ls:{}", s);
        sshUtil.closeExeCommand();
//        Session wangwj = JschUtil.openSession(host, port, "wangwj", "/Users/lihepeng/programLife/wangwj-rsa");
//        wangwj.connect();
//        ChannelExec exec = (ChannelExec)wangwj.openChannel("exec");
//        if(isResponse) {
//            inputStream = exec.getInputStream();
//        }
//        exec.setCommand("ls");
//        exec.setErrStream(System.err);
//        exec.connect();

    }


}
