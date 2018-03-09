package ru.otus.listener;

import com.sun.management.GarbageCollectionNotificationInfo;
import ru.otus.model.GCStatistic;

import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.time.LocalDateTime;

public class GCNotificationListener implements NotificationListener {
    private GCStatistic statistic = new GCStatistic();

    public GCStatistic getStatistic() {
        return this.statistic;
    }

    public static GCNotificationListener listener = new GCNotificationListener();

    private GCNotificationListener() {

    }

    public void handleNotification(Notification notification, Object handback) {
        if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
            GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
            long duration = info.getGcInfo().getDuration();
            String gctype = info.getGcAction();
            if ("end of minor GC".equals(gctype)) {
                gctype = "Young Gen GC";
                this.statistic.setYoungGCQuantity(this.statistic.getYoungGCQuantity() + 1);
                this.statistic.setTotalYoungGCDuration(this.statistic.getTotalYoungGCDuration() + info.getGcInfo().getDuration());
            } else if ("end of major GC".equals(gctype)) {
                gctype = "Old Gen GC";
                this.statistic.setOldGCQuantity(this.statistic.getOldGCQuantity() + 1);
                this.statistic.setTotalOldGCDuration(this.statistic.getTotalOldGCDuration() + info.getGcInfo().getDuration());
            }
            System.out.println();
            System.out.println("[" + LocalDateTime.now() + "] " +
                    gctype + ": - " +
                    info.getGcInfo().getId()+ " " +
                    info.getGcName() + " (from " +
                    info.getGcCause()+") " + duration + " milliseconds; start-end times " +
                    info.getGcInfo().getStartTime()+ "-" +
                    info.getGcInfo().getEndTime());
        }
    }
}
