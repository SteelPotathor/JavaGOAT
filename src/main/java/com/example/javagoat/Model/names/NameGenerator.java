package com.example.javagoat.Model.names;

import com.example.javagoat.Model.Biology;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class NameGenerator {

    public File file;

    public NameGenerator(Biology biology) {
        if (biology.getBsex() == Biology.sex.FEMALE) {
            this.file = new File("src\\main\\java\\com\\example\\javagoat\\Model\\names\\female.csv");
        } else {
            this.file = new File("src\\main\\java\\com\\example\\javagoat\\Model\\names\\male.csv");
        }
    }

    public String[] getRandomName() {

        try {
            RandomAccessFile raf = new RandomAccessFile(this.file, "r");
            Random rand = new Random();

            raf.seek(rand.nextInt(1000));
            raf.readLine();
            return raf.readLine().split(" ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
