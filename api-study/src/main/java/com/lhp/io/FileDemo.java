package com.lhp.io;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.lhp.util.DBUtil;
import com.xiaoleilu.hutool.io.FileUtil;
import com.xiaoleilu.hutool.io.IoUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.Data;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


    public static final String baseSql = "update keystone_ods.ds_patent_8101 set is_exist =0  where patent_id in ( ";
    //按每3个一组分割
    private static final Integer MAX_NUMBER = 1000;

    /**
     * 计算切分次数
     */
    private static Integer countStep(Integer size) {
        return (size + MAX_NUMBER - 1) / MAX_NUMBER;
    }

    @SneakyThrows
    @Test
    public void test00() {
        File file = new File("/Users/lihepeng/IdeaProjects/java-study/api-study/sss.xlsx");


        System.out.println("FileUtil.exist(file) = " + FileUtil.exist(file));

    }

    /**
     * 读取txt内容 换行
     */
    @Test
    public void testReadFile() throws SQLException {
        File file = new File((new File("")).getAbsolutePath() + "/error_id.txt");
        List<String> ids = FileUtil.readLines(file, "utf-8");
        int limit = ids.size();
        List<List<String>> splitList = Stream.iterate(0, n -> n + 1)
//
                .limit(limit)
                .parallel()
                .map(a -> ids.stream().skip(a * MAX_NUMBER)
                        .limit(MAX_NUMBER).parallel()
                        .map(s -> StrUtil.wrap(s, "'", "'"))
                        .collect(Collectors.toList()))
                .filter(CollectionUtil::isNotEmpty)
                .collect(Collectors.toList());

        DBUtil dbUtil = new DBUtil();

        for (List<String> idsPer : splitList) {
            Connection connection = dbUtil.getConnection("///jdbc:postgresql://192.168.201.30:5432/hsdata",
                    "hsdata",
                    "HJN9FBDxs8vBkKgw",
                    "org.postgresql.Driver");

            connection.setSchema("keystone_ods");
            connection.setAutoCommit(false);
            String join = Joiner.on(",").join(idsPer);
            String sql = baseSql + join + ")";
            dbUtil.update(connection, sql);
        }


    }


    @Test
    public void testDB() {
        DBUtil dbUtil = new DBUtil();
        Connection connection = dbUtil.getConnection("jdbc:postgresql://192.168.201.30:5432/hsdata",
                "hsdata",
                "HJN9FBDxs8vBkKgw",
                "org.postgresql.Driver");

        try {
            connection.setSchema("keystone_ods");
            Map<String, Object> one = dbUtil.getOne(connection, "Select * from ds_patent_8101 limit 1");

            DatabaseMetaData metaData = connection.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
