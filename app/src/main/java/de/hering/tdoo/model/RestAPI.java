package de.hering.tdoo.model;

import java.util.ArrayList;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestAPI {
    @Headers({"Content-Type: application/json"})
    @POST("/todos")
    public Todo createItem(@Body Todo item);

    @GET("/todos")
    public ArrayList<Todo> readAllItems();

    @GET("/todos/{id}")
    public Todo readItem(@Path("id") long dataItemId);

    @Headers({"Content-Type: application/json"})
    @PUT("/todos/{id}")
    public Todo updateItem(@Path("id") long dataItemId, @Body Todo item);

    @Headers({"Content-Type: application/json"})
    @DELETE("/todos/{id}")
    public boolean deleteItem(@Path("id") long dataItemId);
}
