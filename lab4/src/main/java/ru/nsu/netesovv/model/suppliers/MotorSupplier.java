package ru.nsu.netesovv.model.suppliers;

import ru.nsu.netesovv.model.Delay;
import ru.nsu.netesovv.model.Storage;
import ru.nsu.netesovv.model.products.car.Motor;

public class MotorSupplier extends SupplierThread<Motor> {
    public MotorSupplier(Storage<Motor> storage, Delay motorSupplierDelay) {
        super(storage, motorSupplierDelay, "MotorSupplierThread");
    }

    @Override
    public Motor createProduct() {
        return new Motor();
    }
}
