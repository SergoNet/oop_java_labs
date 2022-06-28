package ru.nsu.netesovv.view;


import ru.nsu.netesovv.Constants;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Management extends JPanel {
    private final JLabel label;
    private final JSlider delaySlider;

    public Management(String componentName, ChangeListener changeListener) {
        super(new GridLayout(2, 1, 0, 2));
        this.label = new JLabel(componentName);
        this.delaySlider = new JSlider(Constants.MIN_DELAY, Constants.MAX_DELAY);
        this.delaySlider.addChangeListener(changeListener);
        addPanelComponents();
    }

    private void addPanelComponents() {
        configureComponents();
        this.add(label);
        this.add(delaySlider);
    }

    private void configureComponents() {
        delaySlider.setPaintLabels(true);
        delaySlider.setMajorTickSpacing(1);
        delaySlider.setValue(5);
    }
}

