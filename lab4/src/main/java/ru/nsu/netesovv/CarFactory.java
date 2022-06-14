package ru.nsu.netesovv;

import ru.nsu.netesovv.model.FactoryBase;
import ru.nsu.netesovv.view.View;

import ru.nsu.netesovv.controller.Controller;

public class CarFactory implements Runnable {

    private final FactoryBase FactoryBase = new FactoryBase();
    private final View view;

    CarFactory() {
        Controller controller = new Controller(FactoryBase);
        view = new View(controller, FactoryBase);
    }

    @Override
    public void run() {
        FactoryBase.run();
        view.run();
    }
}
