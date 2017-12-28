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
   // public static final Map<String, MemoryItem> ITEM_MAP = new HashMap<String, MemoryItem>();




    public static void addItem(MemoryItem item) {
        ITEMS.add(item);
        //ITEM_MAP.put(item.date, item);
    }

    public static void addItem(String date, String title, String details,double positionX, double positionY) {
        MemoryItem tmp = new MemoryItem(date,title,details,positionX,positionY);
        ITEMS.add(tmp);
        //ITEM_MAP.put(tmp.date, tmp);
    }

    public static void removeItem(MemoryItem item) {
        ITEMS.remove(item);
        //ITEM_MAP.remove(item.date,item);
    }

    public static String makeDetails(MemoryItem item) {
        StringBuilder builder = new StringBuilder();
        builder.append("The name of your memory is\"")
                .append(item.title)
                .append("\". \n\nThe date (yyyyMMdd_HHMMSS) is \"")
                .append(item.date)
                .append("\". \n\nThe position GPS is (")
                .append(item.latitude)
                .append(",")
                .append(item.longitude)
                .append("). \n\nAnd here are the detail : ")
                .append(item.details);
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of title.
     */
    public static class MemoryItem {
        public String date;
        public String title;
        public String details;
        public double latitude;
        public double longitude;

        public MemoryItem(String date, String title, String details, double latitude, double longitude) {
            this.date = date;
            this.title = title;
            this.details = details;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public MemoryItem() {
            this.date = "today";
            this.title = "title";
            this.details = "details";
            this.latitude = 0.0;
            this.longitude = 0.0;
        }

        @Override
        public String toString() {
            return title;
        }

        public void setDate(String date)
        {
            this.date = date;
        }
        public void setTitle(String title) { this.title = title;}
        public void setDetails(String details) { this.details = details;}
        public void setPosition(double latitude,double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getDate()
        {
            return this.date;
        }
        public String getTitle() {return this.title;}
        public String getDetails() {return this.details;}
        public float getLatitude() {
            return (float) this.latitude;
        }
        public float getLongitude() {
            return (float) this.longitude;
        }
    }
}
