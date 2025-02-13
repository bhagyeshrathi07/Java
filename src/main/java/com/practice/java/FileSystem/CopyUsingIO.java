package com.practice.java.FileSystem;

import java.io.*;

public class CopyUsingIO {

    File input = new File("Input.txt");
    File output = new File("Output.txt");

    public void copy() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("Output.txt"));

//            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("Input.txt"));
//            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("Output.txt"));
//
//            byte[] buffer = new byte[1024];
//
//            int bytesRead = bis.read(buffer);
//            bos.write(buffer, 0, bytesRead);

//            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Person.ser"));
//            ObjectInputStream  ois = new ObjectInputStream(new FileInputStream("Person.ser"));
//
//            oos.writeObject(object);
//            ois.readObject(object);

            String line;
            while((line = br.readLine()) != null) {
                bw.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
