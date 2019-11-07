package com.app.runnables;

import java.io.*;

public class AppThread extends Thread {
    @Override
    public void run() {
        try(BufferedReader reader = new BufferedReader(new FileReader
                (new File("C:\\Users\\Akshay Pawar\\Desktop\\SolrBigQuery.txt")))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(Thread.currentThread().getName() + " reading the line: " + line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
