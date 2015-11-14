package ca.zqz.app.logic;

import com.google.gson.GsonBuilder;
import com.squareup.okhttp.HttpUrl;
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
    public HttpUrl endpointUrl;
    private User user;

    public Login() {
        endpointUrl = HttpUrl.parse("http://zqz.ca/" + endpoint);
    }

    public boolean login() {
        try {
            Response authResponse = post();
            if (authResponse.code() == 202) {
                String response = authResponse.body().string();

                System.out.println(response);

                Gson g = new Gson();
                user = g.fromJson(response, User.class);
                return true;
            }
        } catch (IOException e) {
            System.out.println("Failed to login: " + e.getMessage());
        }

        return false;
    }

    public void setEndpointUrl(HttpUrl endpointUrl) {
        this.endpointUrl = endpointUrl;
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

    private Response post() throws IOException {
        String json = this.toJson();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(endpointUrl)
                .post(body)
                .build();
        return client.newCall(request).execute();
    }
}
