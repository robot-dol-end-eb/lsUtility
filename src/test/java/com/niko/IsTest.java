package com.niko;

import org.apache.commons.io.output.TeeOutputStream;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.io.FileUtils;

public class IsTest {
    @Test
    void test1() throws IOException {
        String[] command = "-l -h -r -o result.txt .\\src\\test\\java\\resources\\TestDir".split(" ");
        IsLauncher.main(command);
        File file1 = new File(".\\src\\test\\java\\resources\\result.txt");
        File file2 = new File(".\\src\\test\\java\\resources\\expTest1.txt");
        assertTrue(FileUtils.contentEquals(file1, file2));
    }

    @Test
    void test2() throws IOException {
        String[] command = "-l -r -o result.txt .\\src\\test\\java\\resources\\TestDir\\a1.txt".split(" ");
        IsLauncher.main(command);
        File file1 = new File(".\\src\\test\\java\\resources\\result.txt");
        File file2 = new File(".\\src\\test\\java\\resources\\expTest2.txt");
        assertTrue(FileUtils.contentEquals(file1, file2));
    }

    @Test
    void test3() throws IOException {
        String[] command = "-o result.txt .\\src\\test\\java\\resources\\TestDir".split(" ");
        IsLauncher.main(command);
        File file1 = new File(".\\src\\test\\java\\resources\\result.txt");
        File file2 = new File(".\\src\\test\\java\\resources\\expTest3.txt");
        assertTrue(FileUtils.contentEquals(file1, file2));
    }

    @Test
    void test4() throws IOException {
        String[] command = ".\\src\\test\\java\\resources\\TestDir".split(" ");
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        OutputStream teeStream = new TeeOutputStream(System.out, buffer);
        System.setOut(new PrintStream(teeStream));
        IsLauncher.main(command);
        try(OutputStream fileStream = new FileOutputStream(".\\src\\test\\java\\resources\\result.txt")) {
            buffer.writeTo(fileStream);
        }
        File file1 = new File(".\\src\\test\\java\\resources\\result.txt");
        File file2 = new File(".\\src\\test\\java\\resources\\expTest4.txt");
        assertTrue(FileUtils.contentEquals(file1, file2));
    }
}
