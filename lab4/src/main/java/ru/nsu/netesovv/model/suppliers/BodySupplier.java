package ru.nsu.netesovv.model.suppliers;

import ru.nsu.netesovv.model.Delay;
import ru.nsu.netesovv.model.Storage;
import ru.nsu.netesovv.model.products.car.Body;

public class BodySupplier extends SupplierThread<Body> {
    public BodySupplier(Storage<Body> storage, Delay bodySupplierDelay) {
        super(storage, bodySupplierDelay, "BodySupplierThread");
    }

    @Override
    public Body createProduct() {
        return new Body();
    }
}
