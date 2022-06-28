package ru.nsu.netesovv;


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

public class ThreadPool implements Executor {
    private final Queue<Runnable> queue = new ConcurrentLinkedQueue<>();
    private final int poolSize;

    public ThreadPool(int numThreads) {
        poolSize = numThreads;
        for (int i = 0; i < numThreads; ++i) {
            new Thread(new ThreadInPoolWork(), "ThreadInPool").start();
        }
    }

    public synchronized int countTasksInQueue() { // использовать осторожно
        return queue.size();
    }

    @Override
    public synchronized void execute(Runnable task) { queue.offer(task); }

    public int getPoolSize() {
        return poolSize;
    }

    private class ThreadInPoolWork implements Runnable {
        @Override
        public void run() {
            while (true) {
                Runnable nextThreadTask = queue.poll();
                if (nextThreadTask != null) {
                    nextThreadTask.run();
                }
            }
        }
    }
}

