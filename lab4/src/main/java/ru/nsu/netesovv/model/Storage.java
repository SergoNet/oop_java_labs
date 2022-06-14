package ru.nsu.netesovv.model;


import ru.nsu.netesovv.utils.Observable;
import ru.nsu.netesovv.utils.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Storage <ProductType> implements Observable {
    private final List<Observer> observers;
    private final BlockingQueue<ProductType> blockingQueue;
    private final int volume;

    public Storage(int volume) {
        observers = new ArrayList<>(1);
        this.volume = volume;
        blockingQueue = new LinkedBlockingQueue<>(volume);
    }

    public synchronized void storeProduct(ProductType product) {
        while (isFull()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        blockingQueue.add(product);
        this.notifyAll();
        notifyObservers();
    }

    public synchronized ProductType getProduct() {
        while (isEmpty()) {
            try {
                notifyObservers();
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.notifyAll();
        notifyObservers();
        return blockingQueue.poll();
    }

    public synchronized boolean isFull() { return volume == blockingQueue.size(); }
    public synchronized boolean isEmpty() { return blockingQueue.size() == 0; }
    public synchronized int calculatingFreeSpace() { return volume - blockingQueue.size(); }
    public synchronized int getVolume() { return volume; }
    public synchronized int getFullness() { return blockingQueue.size(); }

    @Override
    public void addObserver(Observer observer) { observers.add(observer); }

    @Override
    public void removeObserver(Observer observer) { observers.remove(observer); }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.handleEvent();
        }
    }
}


