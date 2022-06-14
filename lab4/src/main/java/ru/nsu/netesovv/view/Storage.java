package ru.nsu.netesovv.view;


import ru.nsu.netesovv.utils.Observer;

import javax.swing.*;
import java.awt.*;

public class Storage extends JPanel implements Observer  {

    private final String storageName;
    private final JLabel storageLabel;
    private final JProgressBar storageFullnessBar;
    private final ru.nsu.netesovv.model.Storage<?> observedStorage;

    public Storage(String storageName, ru.nsu.netesovv.model.Storage<?> observedStorage) {
        super(new GridLayout(2, 1, 0, 2));
        this.observedStorage = observedStorage;
        observedStorage.addObserver(this);
        this.storageName = storageName;
        this.storageLabel = new JLabel(storageName);
        this.storageFullnessBar = new JProgressBar(0, 100);
        addPanelComponents();
        setStorageFullness(0, 0);
    }

    private void addPanelComponents() {
        storageFullnessBar.setStringPainted(true);
        this.add(storageLabel);
        this.add(storageFullnessBar);
    }

    public void setStorageFullness(int fullness, int capacity) {
        storageFullnessBar.setValue((int) ((float) fullness / capacity * 100));
        String storageStatus = String.format(storageName + ": %d / %d", fullness, capacity);
        storageLabel.setText(storageStatus);
    }

    @Override
    public void handleEvent() {
        synchronized (observedStorage) {
            setStorageFullness(observedStorage.getFullness(), observedStorage.getVolume());
        }
    }
}


