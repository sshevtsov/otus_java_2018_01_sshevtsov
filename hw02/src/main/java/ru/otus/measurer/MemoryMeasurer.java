package ru.otus.measurer;

import java.util.function.Supplier;

public class MemoryMeasurer {
    public <T> Object printObjectSize(final Supplier<T> supplier, final int size)
    {
        final Object[] objects = new Object[size];
        System.gc();
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore= runtime.totalMemory() - runtime.freeMemory();

        for (int i = 0; i < size; i++) {
            objects[i] = supplier.get();
        }

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.gc();
        System.out.print(supplier.get().getClass().getSimpleName() + " = " + (memoryAfter - memoryBefore) / size + "\n");

        return objects;
    }
}
