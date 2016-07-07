package de.hering.tdoo.model;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orm.SugarRecord;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;
import de.hering.tdoo.GlobalClass;


public class Todo extends SugarRecord implements Serializable {
    public String name = "Todo";
    public String description = "Beschreibung";
    public Boolean isDone = false;
    public Boolean isFavourite = false;
    public Date dueDate;

    // empty constructor as needed from SugarORM
    public Todo() {}

    public void fillTodo(JSONObject jsonTodo) {
        try {
            this.name = jsonTodo.getString("name");
            this.description = jsonTodo.getString("description");
            try {
                this.dueDate = new Date(jsonTodo.getLong("expiry"));
            } catch (Exception e) {
            }
            this.isDone = jsonTodo.getBoolean("done");
            this.isFavourite = jsonTodo.getBoolean("favourite");
            this.setId(jsonTodo.getLong("id"));
        } catch (JSONException e) {}
    }

    public String getDueDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy HH:mm");
        return sdf.format(this.dueDate);
    }

    public String getDueDateDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
        return sdf.format(this.dueDate);
    }

    public String getDueTimeString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(this.dueDate);
    }

    @Override
    public String toString() {
        return name;
    }

    public ArrayList<Todo> readAllItems() {
        return new ArrayList<Todo>(Todo.findWithQuery(Todo.class, "SELECT * from Todo"));
    }

    private RequestParams fillRequestParams(){
        RequestParams params = new RequestParams();
        params.put("id",this.getId());
        params.put("name",this.name);
        params.put("description",this.description);
        params.put("expiry",this.dueDate.getTime());
        params.put("done",this.isDone.toString());
        params.put("favourite",this.isFavourite.toString());
        return params;
    }

    @Override
    public long save() {
        Long id = super.save();
        Boolean isOnline = GlobalClass.getIsOn();
        if(isOnline){
            RequestParams params = fillRequestParams();
            params.setUseJsonStreamer(true);
            params.setElapsedFieldInJsonStreamer(null);
            RestClient.put("/todos/" + id, params, new JsonHttpResponseHandler() {});
        }
        return id;
    }

    @Override
    public boolean delete() {
        Boolean isOnline = GlobalClass.getIsOn();
        if(isOnline){
            RestClient.delete("/todos/" + this.getId(), null, new JsonHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                }
            });
        }
        return super.delete();
    }
}
