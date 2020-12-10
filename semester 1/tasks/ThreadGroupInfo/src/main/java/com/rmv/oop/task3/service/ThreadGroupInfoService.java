package com.rmv.oop.task3.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ThreadGroupInfoService {

    @Value("${millisInterval}")
    private int millisInterval;

    private void printlnWithSublevel(String string, int subLevel) {
        for (int i = 0; i < subLevel; i++) {
            System.out.print("-");
        }
        System.out.print(string + System.lineSeparator());
    }

    private synchronized void printThreadGroupInfo(ThreadGroup threadGroup, int subLevel) {
        printlnWithSublevel(threadGroup.getName(), subLevel);

        Thread[] threadsInGroup = new Thread[threadGroup.activeCount()];
        int threadsCount = threadGroup.enumerate(threadsInGroup, false);
        if (threadsCount > 0) {
            printlnWithSublevel("Threads in " + threadGroup.getName() + ":", subLevel);
            for (int i = 0; i < threadsCount; i++) {
                printlnWithSublevel(threadsInGroup[i].getName(), subLevel + 1);
            }
        }
        ThreadGroup[] threadGroups = new ThreadGroup[threadGroup.activeGroupCount()];
        int threadGroupsCount = threadGroup.enumerate(threadGroups, false);
        if (threadGroupsCount > 0) {
            printlnWithSublevel("ThreadGroups in " + threadGroup.getName() + ":", subLevel);
            for (int i = 0; i < threadGroupsCount; i++) {
                printThreadGroupInfo(threadGroups[i], subLevel + 1);
            }
        }
        System.out.println();
    }

    public void printThreadGroupInfoContinuously(ThreadGroup threadGroup) {
        while (threadGroup.activeCount() > 0) {
            printThreadGroupInfo(threadGroup, 0);
            try {
                Thread.sleep(millisInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void threadsInfo(ThreadGroup threadGroup) {
        new Thread(() -> {
            printThreadGroupInfoContinuously(threadGroup);
        }).start();
    }
}
