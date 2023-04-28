package com.lhp.hdfs;

import com.lhp.bean.Apple;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author : lihp
 * @Description :
 * @date : 2023/4/24 10:34
 */
public class FileClient {
    public static void main(String[] args) {


        ccc();
    }

    private static void ddd() {
        Apple apple = null;

        Integer weight = apple.getWeight();
    }

    private static void ccc() {
        ddd();

    }

    @Test
    public void testdd() {

        final int[] a = {0};

        List<Integer> integers = Arrays.asList(1, 2, 3);
        integers.forEach((t) -> {
            if (a[0] > 0) {
            }
            a[0]++;
        });

    }
//    private FileSystem initFileSystem(String ip) throws URISyntaxException, IOException, InterruptedException {
//        Configuration configuration = new Configuration();
//        configuration.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
//        return FileSystem.get(new URI("hdfs://" + ip + ":8020"), configuration, "hdfs");
//    }

}
