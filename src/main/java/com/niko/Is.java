package com.niko;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@SuppressWarnings("WeakerAccess")
public class Is {

    private final String directory_or_file;
    private final Boolean l;
    private final Boolean r;
    private final Boolean h;
    private final Boolean o;
    private final String outPut;

    public Is(Boolean l, Boolean h, Boolean r, String o, String directory_or_file) {
        this.directory_or_file = directory_or_file;
        this.l = l;
        this.h = h;
        this.r = r;
        this.outPut = o;
        this.o = this.outPut != null;
    }

    private String statusFile(File item) {
        StringBuilder result = new StringBuilder();
        if(!h){
            if (item.canRead()) result.append("1"); else result.append("0");
            if (item.canWrite()) result.append("1"); else result.append("0");
            if (item.canExecute()) result.append("1"); else result.append("0");
        } else {
            if (item.canRead()) result.append("r");
            if (item.canWrite()) result.append("w");
            if (item.canExecute()) result.append("x");
        }
        result.append(" ");
        return result.toString();
    }

    private String getFullInfo (File item) {
        StringBuilder result = new StringBuilder();
        result.append(item.getName() + " ");
        if (h) {
            result.append(statusFile(item));
            result.append(new Date(item.lastModified()) + " ");
            result.append(FileUtils.byteCountToDisplaySize(item.length()));
        } else {
            result.append(statusFile(item));
            result.append(item.lastModified() + " ");
            result.append(item.length() + " bytes");
        }
        return result.toString();
    }

    public ArrayList<String> getInfo () {
        ArrayList<String> result = new ArrayList<>();
        File file = new File(directory_or_file);
        if (!file.isDirectory()) {
            if (!l) result.add(file.getName());
                else result.add(getFullInfo(file));
        } else {
            File[] listFiles = file.listFiles();
            if (!l) for (File item : listFiles) result.add(item.getName());
            else for (File item : listFiles) result.add(getFullInfo(item));
        }
        if (r) Collections.reverse(result);
        return result;
    }

    public void toFile(){
        ArrayList<String> result = getInfo();
        try(FileWriter writer = new FileWriter(outPut))
        {
            for (String item : result) {
                writer.write(item + "\n");
            }
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }


}
