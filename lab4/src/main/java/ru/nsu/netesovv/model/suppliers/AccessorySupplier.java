package ru.nsu.netesovv.model.suppliers;

import ru.nsu.netesovv.model.Delay;
import ru.nsu.netesovv.model.Storage;
import ru.nsu.netesovv.model.products.car.Accessory;

public class AccessorySupplier extends SupplierThread<Accessory> {
    public AccessorySupplier(Storage<Accessory> storage, Delay accessorySupplierDelay) {
        super(storage, accessorySupplierDelay, "AccessorySupplierThread");
    }

    @Override
    public Accessory createProduct() {
        return new Accessory();
    }
}
