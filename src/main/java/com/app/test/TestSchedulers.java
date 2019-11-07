package com.app.test;

import com.app.runnables.CleaningScheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestSchedulers {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        //service.schedule(new CleaningScheduler(), 5, TimeUnit.SECONDS);
        //service.scheduleAtFixedRate(new CleaningScheduler(), 5, 4, TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(new CleaningScheduler(), 5, 4, TimeUnit.SECONDS);
        //First task after init delay of 5 sec
        //2s for task to execute
        //Wait of 4 secs
    }
}
