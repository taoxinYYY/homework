package io;

import java.io.File;
import java.io.IOException;

/**
 * Created by DELL on 2018/7/20.
 * file
 */
public class FileDemo {
    public static void main(String[] args){
        File file = new File("a\\b\\c");
        String  path = file.getAbsolutePath();
        System.out.println(path);
        file.mkdirs();
        if(file.isDirectory()){
            System.out.println("目录");
        }else if(file.isFile()){
            System.out.println("文件");
        }
        if(file.exists()){
            System.out.println("file exists true");
        }else{
            System.out.println("file exists false");
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


