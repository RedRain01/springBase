package com.example.mysqlservers.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class threadPoolUtil {
    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("why-pool-%d")
                .build();
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(10, 12, 0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());


        for (int i = 0; i < 1000; i++) {
            threadPoolExecutor.execute(() ->
                    System.out.println(Thread.currentThread().getName()));
        }
        threadPoolExecutor.shutdown();
    }




}