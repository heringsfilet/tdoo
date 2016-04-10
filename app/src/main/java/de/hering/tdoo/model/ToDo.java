package de.hering.tdoo.model;

import com.orm.SugarRecord;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Todo extends SugarRecord {
    public String name = "Todo";
    public String description = "Beschreibung";
    public Boolean isDone = false;
    public Boolean isFavourite = false;
    public Date dueDate;

    // empty constructor as needed from SugarORM
    public Todo() { }

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

    @Override
    public long save(){
        return super.save();
        // ToDo: Auch im Web speichern
    }
}
