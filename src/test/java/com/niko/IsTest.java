package com.niko;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.io.FileUtils;
import org.kohsuke.args4j.CmdLineException;

public class IsTest {
    @Test
    void test1() throws IOException, CmdLineException {
        String[] command = "-l -h -o result.txt C:\\Users\\VAS\\IdeaProjects\\untitled\\src\\test\\java\\resources\\Test".split(" ");

        IsLauncher.main(command);

       // File file1 = new File(".\\src\\test\\resources\\testOutput\\output1.txt");
       // File file2 = new File(".\\src\\test\\resources\\expectedOutput\\eOutput1.txt");

        //assertTrue(FileUtils.contentEquals(file1, file2));

    }
}
