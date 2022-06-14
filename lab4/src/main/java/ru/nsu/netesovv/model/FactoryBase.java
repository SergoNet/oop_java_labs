package ru.nsu.netesovv.model;


import ru.nsu.netesovv.model.products.car.Car;
import ru.nsu.netesovv.model.products.car.Accessory;
import ru.nsu.netesovv.model.products.car.Body;
import ru.nsu.netesovv.model.products.car.Motor;
import ru.nsu.netesovv.model.suppliers.AccessorySupplier;
import ru.nsu.netesovv.model.suppliers.BodySupplier;
import ru.nsu.netesovv.model.suppliers.MotorSupplier;
import ru.nsu.netesovv.ThreadPool;

import java.io.IOException;
import java.util.Properties;

public class FactoryBase implements Runnable {
    private final Properties properties = new Properties();

    private Storage<Motor> motorStorage;
    private Storage<Body> bodyStorage;
    private Storage<Accessory> accessoryStorage;
    private Storage<Car> carStorage;

    private MotorSupplier motorSupplier;
    private BodySupplier bodySupplier;
    private AccessorySupplier accessorySupplier;

    private CarStorageController carStorageController;
    private Dealer dealer;

    private ThreadPool workersPool;

    private final Delay motorSupplierDelay;
    private final Delay bodySupplierDelay;
    private final Delay accessorySupplierDelay;
    private final Delay workerDelay;
    private final Delay dealerDelay;

    public FactoryBase() {
        try {
            properties.load(FactoryBase.class.getClassLoader() .getResourceAsStream("factory.properties"));
        } catch (IOException e) {
            System.err.println("error");
            e.printStackTrace();
        }
        motorSupplierDelay = new Delay(Integer.parseInt(properties.getProperty("WorkersCount")));
        bodySupplierDelay = new Delay(Integer.parseInt(properties.getProperty("WorkersCount")));
        accessorySupplierDelay = new Delay(Integer.parseInt(properties.getProperty("WorkersCount")));
        workerDelay = new Delay(Integer.parseInt(properties.getProperty("WorkersCount")));
        dealerDelay = new Delay(Integer.parseInt(properties.getProperty("WorkersCount")));
        createStorages();
        createThreads();
    }

    private void createStorages() {
        try {
            motorStorage = new Storage<>(Integer.parseInt(properties.getProperty("MotorStorageSize")));
            bodyStorage = new Storage<>(Integer.parseInt(properties.getProperty("BodyStorageSize")));
            accessoryStorage = new Storage<>(Integer.parseInt(properties.getProperty("AccessoryStorageSize")));
            carStorage = new Storage<>(Integer.parseInt(properties.getProperty("CarStorageSize")));
        } catch (NumberFormatException e) {
            System.err.println("error");
            e.printStackTrace();
        }
    }

    private void createThreads() throws NumberFormatException {
        motorSupplier = new MotorSupplier(motorStorage, motorSupplierDelay);
        bodySupplier = new BodySupplier(bodyStorage, bodySupplierDelay);
        accessorySupplier = new AccessorySupplier(accessoryStorage, accessorySupplierDelay);
        workersPool = new ThreadPool(Integer.parseInt(properties.getProperty("WorkersCount")));
        dealer = new Dealer(carStorage , dealerDelay);
        carStorageController = new CarStorageController(carStorage, workersPool, workerDelay, motorStorage, bodyStorage, accessoryStorage);
    }

    @Override
    public void run() {
        motorSupplier.start();
        bodySupplier.start();
        accessorySupplier.start();
        dealer.start();
        carStorageController.start();
    }

    public Delay getDealerDelay() { return dealerDelay; }
    public Storage<Motor> getMotorStorage() { return motorStorage; }
    public Storage<Body> getBodyStorage() { return bodyStorage; }
    public Storage<Accessory> getAccessoryStorage() { return accessoryStorage; }
    public Storage<Car> getCarStorage() { return carStorage ; }
    public synchronized Delay getMotorDelay() { return motorSupplierDelay; }
    public synchronized Delay getBodyDelay() { return bodySupplierDelay; }
    public synchronized Delay getAccessoryDelay() { return accessorySupplierDelay; }
    public synchronized Delay getWorkerDelay() { return workerDelay; }
}

