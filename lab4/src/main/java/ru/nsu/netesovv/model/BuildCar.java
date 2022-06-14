package ru.nsu.netesovv.model;


import ru.nsu.netesovv.model.products.car.Car;
import ru.nsu.netesovv.model.products.car.Accessory;
import ru.nsu.netesovv.model.products.car.Body;
import ru.nsu.netesovv.model.products.car.Motor;


public class BuildCar implements Runnable {
    private final Storage<Motor> motorStorage;
    private final Storage<Body> bodyStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Car> carStorage;
    private final Delay workerDelay;

    public BuildCar(Storage<Motor> motorStorage, Storage<Body> bodyStorage,
                    Storage<Accessory> accessoryStorage, Storage<Car> carStorage, Delay workerDelay) {
        this.motorStorage = motorStorage;
        this.bodyStorage = bodyStorage;
        this.accessoryStorage = accessoryStorage;
        this.carStorage = carStorage;
        this.workerDelay = workerDelay;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000L * workerDelay.getDelay());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Car newCar = new Car(motorStorage.getProduct(), bodyStorage.getProduct(), accessoryStorage.getProduct());
        carStorage.storeProduct(newCar);
    }
}

