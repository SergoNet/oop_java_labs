package ru.nsu.netesovv.model;


import ru.nsu.netesovv.model.products.car.Car;
import ru.nsu.netesovv.model.products.car.Accessory;
import ru.nsu.netesovv.model.products.car.Body;
import ru.nsu.netesovv.model.products.car.Motor;
import ru.nsu.netesovv.ThreadPool;

public class CarStorageController extends Thread {
    private final ThreadPool workersPool;
    private final Delay workerDelay;

    private final Storage<Car> carStorage;
    private final Storage<Motor> motorStorage;
    private final Storage<Body> bodyStorage;
    private final Storage<Accessory> accessoryStorage;

    public CarStorageController(Storage<Car> carStorage, ThreadPool workersPool, Delay workerDelay,
                                Storage<Motor> motorStorage, Storage<Body> bodyStorage, Storage<Accessory> accessoryStorage) {
        super("CarStorageController");
        this.workersPool = workersPool;
        this.workerDelay = workerDelay;

        this.carStorage = carStorage;
        this.motorStorage = motorStorage;
        this.bodyStorage = bodyStorage;
        this.accessoryStorage = accessoryStorage;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (carStorage) {
                while (workersPool.countTasksInQueue() >= carStorage.calculatingFreeSpace()) {
                    try {
                        carStorage.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < carStorage.calculatingFreeSpace() -
                        workersPool.countTasksInQueue() + workersPool.getPoolSize(); ++i) {
                    workersPool.execute(new BuildCar(motorStorage, bodyStorage, accessoryStorage,
                            carStorage, workerDelay));
                }
            }
        }
    }
}

