package com.github.arkoski.controller;

import com.github.arkoski.Application;

public class TestThread extends Thread {
    public static long start;

    @Override
    public void run() {
        //long start;
        long end = System.currentTimeMillis();
        while (end - start < 5000) {
            end = System.currentTimeMillis();
        }
        System.out.println("WON !");
        Application.isLoggedIn = false;

        TestThread testThread = new TestThread();
        Thread thread = new Thread(testThread);
        TestThread.start = System.currentTimeMillis();

        thread.start();
    }
}