package com.company.one;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class One {

    public static void writeToFile(String path, String content) {
        try {
            OutputStream output = new FileOutputStream(path);
            PrintStream out = new PrintStream(output);
            System.setOut(out);
            System.out.println(content);
            out.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String path) {
        try {
            Scanner scanner = new Scanner(new FileInputStream(path));
            return scanner.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void bufferStreamFileOutput(String path, String content) {
        try {
            OutputStream outputStream = new FileOutputStream(path);
            BufferedOutputStream out = new BufferedOutputStream(outputStream);
            out.write(content.getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String bufferStreamFileInput(String path) {
        try {
            InputStream in = new FileInputStream(path);
            BufferedInputStream inputStream = new BufferedInputStream(in);
            return new String(inputStream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        String path = "/home/son/Desktop/CV và bảng điểm - Trần Thanh Sơn/untitled/src/com/company/one/txt.txt";
//        String path = "/home/son/Desktop/sofa-goc-ha-noi-skyn310.jpg";
        String path = "/media/son/Games/ubuntu-20.04-desktop-amd64.iso";
        // First question page 38
//        writeToFile(path, "a");
//        System.out.println(readFile(path));

        // Second question page 38
//        bufferStreamFileOutput(path, "b");
//        bufferStreamFileInput(path);
        System.out.println(bufferStreamFileInput(path));

    }
}
