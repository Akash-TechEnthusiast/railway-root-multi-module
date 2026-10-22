package com.india.railway.java.version.java7;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResourcesDemo {

    //Purpose: Automatically close resources such as files, streams, and database connections after use.
    public static void main(String[] args) {

        try (BufferedReader reader =
                     new BufferedReader(new FileReader("/home/akash/Documents/railway-root/railway-web/src/main/resources/data.txt"))) {

            String line;

            System.out.println("Printing the content of the file:");

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Program completed");
    }
}
