package ru.nsu.netesovv.view;


import ru.nsu.netesovv.Constants;
import ru.nsu.netesovv.model.Storage;
import ru.nsu.netesovv.utils.Observer;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Supplier extends JPanel implements Observer {
    private final String suppliersProduct;
    private final JLabel label;
    private final JProgressBar storageLoad;
    private final JSlider delaySlider;
    private final Storage<?> typeOfStorage;

    public Supplier(String suppliersProduct, ChangeListener changeListener, Storage<?> typeOfStorage) {
        super(new GridLayout(2, 1, 0, 3));
        this.typeOfStorage = typeOfStorage;
        typeOfStorage.addObserver(this);
        this.suppliersProduct = suppliersProduct;
        this.label = new JLabel(suppliersProduct);
        this.storageLoad = new JProgressBar(0, 100);
        this.delaySlider = new JSlider(Constants.MIN_DELAY, Constants.MAX_DELAY);
        this.delaySlider.addChangeListener(changeListener);
        addPanelComponents();
        setTheStorageCapacity(0, 0);
    }

    private void addPanelComponents() {
        configureComponents();
        this.add(label);
        this.add(storageLoad);
        this.add(delaySlider);
    }

    private void configureComponents() {
        storageLoad.setStringPainted(true);
        delaySlider.setPaintLabels(true);
        delaySlider.setMajorTickSpacing(1);
        delaySlider.setValue(5);
    }

    public void setTheStorageCapacity(int fullness, int capacity) {
        storageLoad.setValue((int) ((float) fullness / capacity * 100));
        String storageStatus = String.format(suppliersProduct + ": %d / %d", fullness, capacity);
        label.setText(storageStatus);
    }

    @Override
    public void handleEvent() {
        synchronized (typeOfStorage) { setTheStorageCapacity(typeOfStorage.getFullness(), typeOfStorage.getVolume()); }
    }
}


