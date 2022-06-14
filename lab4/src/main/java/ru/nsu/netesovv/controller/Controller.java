package ru.nsu.netesovv.controller;


import ru.nsu.netesovv.model.FactoryBase;

public class Controller {
    private final DelayController motorDelayController;
    private final DelayController bodyDelayController;
    private final DelayController accessoryDelayController;
    private final DelayController workersDelayController;
    private final DelayController dealersDelayController;

    public Controller(FactoryBase factoryBase) {
        motorDelayController = new DelayController(factoryBase.getMotorDelay());
        bodyDelayController = new DelayController(factoryBase.getBodyDelay());
        accessoryDelayController = new DelayController(factoryBase.getAccessoryDelay());
        workersDelayController = new DelayController(factoryBase.getWorkerDelay());
        dealersDelayController = new DelayController(factoryBase.getDealerDelay());
    }

    public DelayController getMotorDelayController() {
        return motorDelayController;
    }
    public DelayController getBodyDelayController() {
        return bodyDelayController;
    }
    public DelayController getAccessoryDelayController() {
        return accessoryDelayController;
    }
    public DelayController getWorkersDelayController() {
        return workersDelayController;
    }
    public DelayController getDealersDelayController() {
        return dealersDelayController;
    }
}

