package com.lhp.collections;

import org.junit.Test;

import java.io.*;
import java.util.*;

public class ExternalSortLimitedMemory {


    public static final String BIG_FILE = "/Users/lihepeng/IdeaProjects/java-study/api-study/BIG_FILE.txt";

    public static void main(String[] args) throws IOException {

        // External sorting with limited memory
        externalSort(BIG_FILE, "sorted_data.txt");
    }

    public static void generateTestData(String filename, int numStrings) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            Random random = new Random();
            for (int i = 0; i < numStrings; i++) {
                writer.write(generateRandomString(random, 50));
                writer.newLine();
            }
        }
    }

    public static String generateRandomString(Random random, int length) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public static void externalSort(String inputFilename, String outputFilename) throws IOException {
        // 50w 25mb   5w  2.5m
        int maxStringsInMemory = 50000; // Maximum number of strings to fit in memory at once

        // Read input file and split into smaller blocks
        List<String> block = new ArrayList<>(maxStringsInMemory);
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename))) {
            String line;
            int blockCount = 1;
            while ((line = reader.readLine()) != null) {
                block.add(line);
                if (block.size() >= maxStringsInMemory) {
                    Collections.sort(block);
                    writeBlockToFile(block, "block_" + blockCount + ".txt");
                    block.clear();
                    blockCount++;
                }
            }
            if (!block.isEmpty()) {
                Collections.sort(block);
                writeBlockToFile(block, "block_" + blockCount + ".txt");
            }
        }

        // Merge sorted blocks
        List<BufferedReader> blockReaders = new ArrayList<>();
        for (int i = 1; i <= Math.ceil(4000000 / maxStringsInMemory); i++) {
            BufferedReader blockReader = new BufferedReader(new FileReader("block_" + i + ".txt"));
            blockReaders.add(blockReader);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            PriorityQueue<BufferedReaderLine> heap = new PriorityQueue<>(blockReaders.size(),
                    Comparator.comparing(BufferedReaderLine::getLine));
            //把各个小文件的 头部加进去
            for (BufferedReader blockReader : blockReaders) {
                String line = blockReader.readLine();
                if (line != null) {
                    heap.add(new BufferedReaderLine(blockReader, line));
                }
            }

            while (!heap.isEmpty()) {
                //不停地取队首
                BufferedReaderLine minLine = heap.poll();
                writer.write(minLine.getLine());
                writer.newLine();

                //再把取出来的 BufferedReaderLine 更新 直至某个reader 读取完毕
                String newLine = minLine.getReader().readLine();
                if (newLine != null) {
                    heap.add(new BufferedReaderLine(minLine.getReader(), newLine));
                } else {
                    minLine.getReader().close();
                }
            }
        }

        // Clean up temporary files
        for (int i = 1; i <= Math.ceil(500.0 / maxStringsInMemory); i++) {
            new File("block_" + i + ".txt").delete();
        }
    }

    public static void writeBlockToFile(List<String> block, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String s : block) {
                writer.write(s);
                writer.newLine();
            }
        }
    }

    // Simulated input data generation
    @Test
    public void test() throws IOException {
        //400w  204MB
        generateTestData(BIG_FILE, 4000000); // Generate a file with 2 million random strings

    }
}

class BufferedReaderLine {
    private BufferedReader reader;
    private String line;

    public BufferedReaderLine(BufferedReader reader, String line) {
        this.reader = reader;
        this.line = line;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public String getLine() {
        return line;
    }
}