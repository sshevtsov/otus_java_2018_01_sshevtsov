package ru.otus;

import ru.otus.measurer.MemoryMeasurer;
import ru.otus.model.CustomObject;
import java.util.ArrayList;
import java.util.function.Supplier;

public class MemoryStand {
    private static final int SIZE = 10_000_000;
    public static void main(String[] args) {
        MemoryMeasurer memoryMeasurer = new MemoryMeasurer();
        memoryMeasurer.printObjectSize(String::new, SIZE);
        memoryMeasurer.printObjectSize(() -> new String(new char[]{}), SIZE);
        memoryMeasurer.printObjectSize(() -> new String[]{}, SIZE);
        memoryMeasurer.printObjectSize(() -> new int[0], SIZE);
        memoryMeasurer.printObjectSize(() -> new int[50], SIZE);
        memoryMeasurer.printObjectSize((Supplier<ArrayList>) ArrayList::new, SIZE);
        memoryMeasurer.printObjectSize(CustomObject::new, SIZE);
    }
}