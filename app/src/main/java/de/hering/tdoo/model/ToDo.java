package de.hering.tdoo.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


@Table(name = "Todos")
public class Todo extends Model {
    @Column(name = "name")
    public String name;

    @Column(name = "description")
    public String description;

    @Column(name = "done")
    public Boolean isDone = false;

    @Column(name = "favourite")
    public Boolean isFavourite = false;

    @Column(name = "duedate")
    public Date dueDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public Boolean getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(Boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Todo(String name) {
        super();
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

    public static ArrayList<Todo> findAll() {
        return new Select().from(Todo.class).execute();
    }

    public static Todo findOne(int id){
        return new Select().from(Todo.class).where("id = ?", id).executeSingle();
    }
}
