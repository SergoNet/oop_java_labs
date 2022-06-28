package ru.nsu.netesovv.model.products.car;

public class Car implements Part {
    private final Motor motor;
    private final Body body;
    private final Accessory accessory;

    public Car(Motor engine, Body body, Accessory accessory) {
        super();
        this.motor = engine;
        this.body = body;
        this.accessory = accessory;
    }

    public Motor getMotor() {return motor;}

    public Body getBody() {return body;}

    public Accessory getAccessory() {return accessory;}
}
