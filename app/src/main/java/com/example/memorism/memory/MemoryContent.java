package com.example.memorism.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
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

    public static void removeItem(MemoryItem item) {
        ITEMS.remove(item);
        ITEM_MAP.remove(item.date,item);
        //ITEM_MAP.put(item.date, item);
    }

    private static MemoryItem createDummyItem(int position) {
        return new MemoryItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class MemoryItem {
        public final String date;
        public final String content;
        public final String details;

        public MemoryItem(String date, String content, String details) {
            this.date = date;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
