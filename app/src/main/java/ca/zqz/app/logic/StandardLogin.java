package ca.zqz.app.logic;

import com.google.gson.annotations.Expose;

public class StandardLogin extends Login {
    public String endpoint = "/sessions/login";

    @Expose
    private String login;
    @Expose
    private String password;

    public StandardLogin(String login, String password) {
        super();

        this.login = login;
        this.password = password;
    }
}

