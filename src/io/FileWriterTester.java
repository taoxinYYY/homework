package io;

import java.awt.*;
import java.io.*;

/**
 * Created by DELL on 2018/7/20.
 */
public class FileWriterTester {
    public static void main(String[] args)  throws IOException {
        String fileName = "D:\\Hello.txt";
        FileWriter writer = new FileWriter(fileName);
        writer.write("Hello!\n");
        writer.write("This is my first text file,\n");
        writer.write("You can see how this is done.\n");
        writer.write("输入一行中文也可以\n");
        writer.close();
    }


private void reader2Control(){
    try {
        FileInputStream input = new FileInputStream("abc.txt");
        int content = -1;
        while ((content = input.read()) != -1){
            char c = (char) content;
            System.out.print(c);
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}