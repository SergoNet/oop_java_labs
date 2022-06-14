package ru.nsu.netesovv.view;


import ru.nsu.netesovv.Constants;
import ru.nsu.netesovv.controller.Controller;
import ru.nsu.netesovv.model.FactoryBase;

import javax.swing.*;
import java.awt.*;

public class View implements Runnable {
    private final JFrame window;
    private final JPanel panel;

    @Override
    public void run() {
        addBordersAtWindow();
        window.add(panel);
        window.setFocusable(true);
        window.setVisible(true);
    }

    public View(Controller controller, FactoryBase factoryBase) {
        window = new JFrame("CarFactory");
        window.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(new BorderLayout());
        panel = new JPanel(new GridLayout(6, 1, 0, 5));

        Supplier accessoryPanel = new Supplier("Accessory Storage",
                controller.getAccessoryDelayController(), factoryBase.getAccessoryStorage());

        Supplier motorPanel = new Supplier("Motor Storage",
                controller.getMotorDelayController(), factoryBase.getMotorStorage());

        Supplier bodyPanel = new Supplier("Body Storage",
                controller.getBodyDelayController(), factoryBase.getBodyStorage());

        Storage carStoragePanel = new Storage("Car storage", factoryBase.getCarStorage());

        Management dealerDelayPanel = new Management("Dealers delay:",
                controller.getDealersDelayController());

        Management workerDelayPanel = new Management("Workers delay:",
               controller.getWorkersDelayController());

        panel.add(accessoryPanel);
        panel.add(motorPanel);
        panel.add(bodyPanel);

        panel.add(carStoragePanel);
        panel.add(dealerDelayPanel);
        panel.add(workerDelayPanel);
    }

    private void addBordersAtWindow() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(10, 0));
        window.add(emptyPanel, BorderLayout.WEST);

        JPanel emptyPanel1 = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(10, 0));
        window.add(emptyPanel1, BorderLayout.EAST);
    }
}

