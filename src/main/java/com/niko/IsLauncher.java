package com.niko;

import org.kohsuke.args4j.Argument;
        import org.kohsuke.args4j.CmdLineException;
        import org.kohsuke.args4j.CmdLineParser;
        import org.kohsuke.args4j.Option;

import java.util.ArrayList;

public class IsLauncher {

    @Option(name = "-l", metaVar = "long", usage = "switch output to long format")
    boolean longFormat;

    @Option(name = "-h", metaVar = "human-readable", usage = "switch output to human-readable format", depends = "-l")
    boolean humanReadable;

    @Option(name = "-r", metaVar = "reverse", usage = "reverse output")
    boolean reverse;

    @Option(name = "-o", metaVar = "output", usage = "output to this file")
    String output;

    @Argument(metaVar = "directory_or_file", required = true)
    String directory_or_file;

    public static void main(String[] args) {
        new IsLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Is.jar -l -h -r -o output.file directory_or_file");
            parser.printUsage(System.err);
            return;
        }

        Is obj = new Is(longFormat, humanReadable, reverse, output, directory_or_file);
        try {
            if(output != null) {
                obj.toFile();
            }else {
                ArrayList<String> result = obj.getInfo();
                System.out.println(result);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}