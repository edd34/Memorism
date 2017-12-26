package com.example.memorism.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample title for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class MemoryContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<MemoryItem> ITEMS = new ArrayList<MemoryItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, MemoryItem> ITEM_MAP = new HashMap<String, MemoryItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        /*for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }*/
    }

    public static void addItem(MemoryItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.date, item);
    }

    public static void addItem(String date, String title, String details,float positionX, float positionY) {
        MemoryItem tmp = new MemoryItem(date,title,details,positionX,positionY);
        ITEMS.add(tmp);
        ITEM_MAP.put(tmp.date, tmp);
    }

    public static void removeItem(MemoryItem item) {
        ITEMS.remove(item);
        ITEM_MAP.remove(item.date,item);
    }

    public static String makeDetails(MemoryItem item) {
        StringBuilder builder = new StringBuilder();
        builder.append("The name of your memory is\"")
                .append(item.title)
                .append("\". \n\nThe date (yyyyMMdd_HHMMSS) is \"")
                .append(item.date)
                .append("\". \n\nThe position GPS is (")
                .append(item.positionX)
                .append(",")
                .append(item.positionY)
                .append("). \n\nAnd here are the detail : ")
                .append(item.details);
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of title.
     */
    public static class MemoryItem {
        public final String date;
        public final String title;
        public final String details;
        public final float positionX;
        public final float positionY;

        public MemoryItem(String date, String title, String details,float positionX, float positionY) {
            this.date = date;
            this.title = title;
            this.details = details;
            this.positionX = positionX;
            this.positionY = positionY;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
