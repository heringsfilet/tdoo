package de.hering.tdoo.model;

import com.loopj.android.http.*;

public class RestClient {
    // ToDo enter ip of computer for genymotion access to webapp
    private static final String BASE_URL = "http://192.168.1.2:8080/api";
    private static final String CONTENT_TYPE = "application/json";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
//        JSONObject jsonParams = new JSONObject();
//        jsonParams.put("notes", "Test api support");
//        StringEntity entity = new StringEntity(jsonParams.toString());
//        client.post(context, restApiUrl, entity, "application/json",
//                responseHandler);
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void delete(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.delete(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void put(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.put(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }


}