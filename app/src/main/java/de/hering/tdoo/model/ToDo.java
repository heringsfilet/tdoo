package de.hering.tdoo.model;

import com.orm.SugarRecord;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hering.tdoo.utils.DateParsingUtility;


public class Todo extends SugarRecord implements Serializable {
    public String name = "Todo";
    public String description = "Beschreibung";
    public Boolean isDone = false;
    public Boolean isFavourite = false;
    public Date dueDate;

    // empty constructor as needed from SugarORM
    public Todo() {
    }

    public void fillTodo(JSONObject jsonTodo){
        try{
            this.name = jsonTodo.getString("name");
            this.description = jsonTodo.getString("description");
            try{this.dueDate = DateParsingUtility.parse(jsonTodo.getString("expiry"));}catch (Exception e){};
            this.isDone = jsonTodo.getBoolean("done");
            this.isFavourite = jsonTodo.getBoolean("favourite");
            // ToDo set id?
        }catch(JSONException e){

        }
    }

    public String getDueDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy HH:mm");
        if(this.dueDate != null){
            return sdf.format(this.dueDate);
        }else{
            return sdf.format(new Date());
        }
    }

    public String getDueDateDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
        if(this.dueDate != null){
            return sdf.format(this.dueDate);
        }else{
            return sdf.format(new Date());
        }
    }

    public String getDueTimeString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        if(this.dueDate != null){
            return sdf.format(this.dueDate);
        }else{
            return sdf.format(new Date());
        }
    }

    @Override
    public String toString() {
        return name;
    }


    public ArrayList<Todo> readAllItems() {
        return new ArrayList<Todo>(Todo.findWithQuery(Todo.class, "SELECT * from Todo"));
    }

    public boolean deleteItem(long dataItemId) {
        return super.delete();
    }

    /* ToDo schreiboperation auf webanwendung
        Wurde eine Schreiboperation auf der lokalen SQLite Datenbank erfolgreich ausgeführt, soll die betreffende Operation auf der Webanwendung aufgerufen werden.
        Die durch die SQLite Datenbank zugewiesenen IDs können durch die Webanwendung übernommen werden
    */
    @Override
    public long save() {
        return super.save();
        // ToDo: Auch im Web speichern
    }

    @Override
    public boolean delete() {
        return super.delete();
        // ToDo: Auch im Web löschen
    }
}
