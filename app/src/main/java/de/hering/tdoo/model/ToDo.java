package de.hering.tdoo.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToDo {

    public static final List<ToDoItem> ITEMS = new ArrayList<ToDoItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, ToDoItem> ITEM_MAP = new HashMap<String, ToDoItem>();

    private static final int COUNT = 50;

    static {
        // ToDo remove bootstrap
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(ToDoItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.name, item);
    }

    private static ToDoItem createDummyItem(int position) {
        ToDoItem tmp = new ToDoItem("ToDo " + position);
        long offset = Timestamp.valueOf("2016-03-10 00:00:00").getTime();
        long end = Timestamp.valueOf("2016-10-30 00:00:00").getTime();
        long diff = end - offset + 1;
        tmp.dueDate = new Date(offset + (long)(Math.random() * diff));
        tmp.isFavourite = Math.random() < 0.3;
        tmp.isDone = Math.random() < 0.5;
        tmp.description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. ";
        return tmp;
    }

    public static class ToDoItem {
        public String name;
        public String description;
        public Boolean isDone = false;
        public Boolean isFavourite = false;
        public Date dueDate;

        public ToDoItem(String name) {
            this.name = name;
        }

        public String getDueDateString(){
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy HH:mm");
            return sdf.format(this.dueDate);
        }

        public String getDueDateDateString(){
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
            return sdf.format(this.dueDate);
        }

        public String getDueTimeString(){
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(this.dueDate);
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
