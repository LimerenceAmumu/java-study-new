package com.lhp.io;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.xiaoleilu.hutool.io.IoUtil;
import lombok.Data;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件输入输出流
 *
 * @author Amumu
 * @create 2021/6/25 15:25
 */
public class FileDemo {

    // 写入信息到json 并重新读取到对象中.
    public static void main(String[] args) {
        ImmutableMap<String, Object> map = ImmutableMap.of("name", "amumu", "age", "200");
        String s = JSON.toJSONString(map);
        System.out.println("s = " + s);
        //输出到文件
        UUID uuid = UUID.randomUUID();
        File file = new File(uuid + ".json");
        try {
            IoUtil.write(new FileOutputStream(file), "utf-8", true, s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取文件
        File file1 = new File(uuid + ".json");
        try {
            String read = IoUtil.read(new FileInputStream(file1), "utf-8");
            System.out.println("read = " + read);

            User user = JSON.parseObject(read, User.class);
            System.out.println("user = " + user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Test
    public void testFileCopy() {
        File file = new File("xxx.json");
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
        System.out.println("file.getPath() = " + file.getPath());
    }

    @Data
    static class User {
        private String name;
        private String age;
    }

}
