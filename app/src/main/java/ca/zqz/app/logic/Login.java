package ca.zqz.app.logic;

import com.google.gson.GsonBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import com.google.gson.Gson;
/**
 * Created by dylan on 11/13/15.
 */
public class Login {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    public String endpoint;
    private User user;

    public boolean login() {
        try {
            Response authResponse = post(endpoint);

            if (authResponse.code() == 202) {
                String response = authResponse.body().string();

                Gson g = new Gson();
                user = g.fromJson(response, User.class);
                return true;
            }
        } catch (IOException e) {
            // no good!
        }

        return false;
    }

    public User getUser() {
        return user;
    }

    public String toJson() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        return gson.toJson(this);
    }

    private Response post(String url) throws IOException {
        String json = this.toJson();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://zqz.ca/" + url)
                .post(body)
                .build();
        return client.newCall(request).execute();
    }
}
