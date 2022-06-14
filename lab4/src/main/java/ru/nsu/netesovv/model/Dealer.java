package ru.nsu.netesovv.model;


import ru.nsu.netesovv.model.products.car.Car;

public class Dealer extends Thread {
    private final Storage<Car> carStorage;
    private final Delay dealerDelay;

    public Dealer(Storage<Car> carStorage, Delay dealerDelay) {
        this.carStorage = carStorage;
        this.dealerDelay = dealerDelay;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            Car car = carStorage.getProduct();
            synchronized (carStorage) {
                carStorage.notifyAll();
            }
            try {
                Thread.sleep(1000L*dealerDelay.getDelay());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

