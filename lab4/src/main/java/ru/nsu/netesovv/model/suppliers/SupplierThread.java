package ru.nsu.netesovv.model.suppliers;


import ru.nsu.netesovv.model.Delay;
import ru.nsu.netesovv.model.Storage;

abstract class SupplierThread<SuppliedProductType> extends Thread {
    protected final Storage<SuppliedProductType> storage;
    protected final Delay supplierDelay;

    public SupplierThread(Storage<SuppliedProductType> storage, Delay supplierDelay, String threadName) {
        super(threadName);
        this.storage = storage;
        this.supplierDelay = supplierDelay;
    }

    public abstract SuppliedProductType createProduct();

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            storage.storeProduct(createProduct());
            try {
                Thread.sleep(1000L * supplierDelay.getDelay());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

