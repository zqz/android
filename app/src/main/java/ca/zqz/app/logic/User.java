package ca.zqz.app.logic;

import com.google.gson.Gson;

/**
 * Created by dylan on 11/12/15.
 */
public class User {
    private String id;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String apikey;
    private String address;
    private String phone;

    public User() {
        this.username = "foo";
        this.first_name = "bar";
        this.last_name = "baz";
        this.email = "foo@zqz.ca";
        this.apikey = "123123123213";
        this.address="Mainzerstraße 33, Saarbrücken, Deutschland";
        this.phone = "0175123123123";
    }

    public String getFullName() {
        return first_name + " " + last_name;
    }

    public String toJson() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
}
