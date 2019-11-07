package com.app.test;

import com.app.runnables.LoggingProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class TestOtherAPIs {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        List<Callable<Boolean>> callables = new ArrayList<>();
        callables.add(new LoggingProcessor());
        callables.add(new LoggingProcessor());
        callables.add(new LoggingProcessor());
        callables.add(new LoggingProcessor());
        callables.add(new LoggingProcessor());
        callables.add(new LoggingProcessor());

        List<Future<Boolean>> futures = null;
        try {
            //Returns result of all the operations
            futures = service.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Future<Boolean> future : futures) {
            try {
                System.out.println("Operation result: " + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        //Returns result of one operation
        try {
            System.out.println(service.invokeAny(callables));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //Normal shutdown
        service.shutdown();
        try {
            System.out.println("Service shutdown? " + service.awaitTermination(30, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            service.shutdownNow(); //Shutdown immediately
            e.printStackTrace();
        }
    }
}
