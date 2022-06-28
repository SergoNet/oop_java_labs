package ru.nsu.netesovv.controller;


import ru.nsu.netesovv.model.Delay;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DelayController implements ChangeListener {
    private final Delay variableDelay;

    public DelayController(Delay managedDelay) { this.variableDelay = managedDelay; }

    @Override
    public void stateChanged(ChangeEvent e) {variableDelay.setDelay(((JSlider) e.getSource()).getValue());}
}

