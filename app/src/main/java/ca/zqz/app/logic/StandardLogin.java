package ca.zqz.app.logic;

import com.google.gson.annotations.Expose;

public class StandardLogin extends Login {
    private String endpoint = "/sessions/login";

    @Expose
    private String login;
    @Expose
    private String password;

    public StandardLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }
}

