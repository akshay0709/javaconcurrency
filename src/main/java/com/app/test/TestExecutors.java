package com.app.test;

import com.app.dao.UserDao;
import com.app.runnables.UserProcessor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestExecutors {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        List<String> users = getUsersFromFile("C:\\Users\\Akshay Pawar\\Downloads\\Ex_Files_Java_EE_Concurrency\\Ex_Files_Java_EE_Concurrency\\Exercise Files\\Chapter3\\03_04\\end\\new_users.txt");
        UserDao dao = new UserDao();
        for (String user : users) {
            Future<Integer> future = service.submit(new UserProcessor(user, dao));
            try {
                //Main method is block until future is returned
                System.out.println("Result of the operation is: " + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        service.shutdown();
        System.out.println("Main execution is over!");
    }

    public static List<String> getUsersFromFile(String filename) {
        List<String> users = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader
                (new File(filename)))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                users.add(line);
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(TestExecutors.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(TestExecutors.class.getName()).log(Level.SEVERE, null, e);
        }
        return users;
    }
}
