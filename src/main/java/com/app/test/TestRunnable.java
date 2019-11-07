package com.app.test;

import java.io.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TestRunnable {
    public static void main(String[] args) {
        Runnable runnable =()->{
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
        };

        //Thread thread = new Thread(runnable);
        //thread.start();
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(runnable);
    }
}
