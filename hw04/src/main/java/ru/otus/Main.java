package ru.otus;

import ru.otus.listener.GCNotificationListener;

import javax.management.NotificationEmitter;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private final static int NUMBER_OF_STRINGS = 15000;
    private final static int PAUSE_IN_MILLIS = 1000;

    public static void main(String[] args) throws InterruptedException {
        initGCNotificationListener();

        long start = System.currentTimeMillis();
        System.out.println(ManagementFactory.getRuntimeMXBean().getVmName());
        System.out.println(ManagementFactory.getRuntimeMXBean().getInputArguments());
        System.out.println();

        ArrayList<String> list = new ArrayList<>();
        long curMin = 0;
        while (true) {
            for (int i = 0; i < NUMBER_OF_STRINGS; i++) {
                list.add(new String());
            }

            for (int i = 0; i < NUMBER_OF_STRINGS/2; i++) {
                list.remove(list.size() - 1);
            }
            Thread.sleep(PAUSE_IN_MILLIS);
            long curMinInLoop = (System.currentTimeMillis() - start) / (1000 * 60);

            if (curMinInLoop >= 1 && curMin != curMinInLoop) {
                GCNotificationListener.listener.getStatistic().print();
                curMin = curMinInLoop;
                GCNotificationListener.listener.getStatistic().clean();
            }
        }
    }

    private static void initGCNotificationListener() {
        List<GarbageCollectorMXBean> gcbeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            emitter.addNotificationListener(GCNotificationListener.listener, null, null);
        }
    }
}
