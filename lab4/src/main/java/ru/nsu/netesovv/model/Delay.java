package ru.nsu.netesovv.model;

public class Delay {
    private volatile int delay;

    public Delay(int delay) {
        this.delay = delay;
    }
    public int getDelay() {
        return delay;
    }
    public void setDelay(int delay) {
        this.delay = delay;
    }
}

