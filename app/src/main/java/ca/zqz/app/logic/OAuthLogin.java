package ca.zqz.app.logic;

import com.google.gson.annotations.Expose;

public class OAuthLogin extends Login {
    private transient String endpoint = "/sessions/oauth";

    @Expose
    private String token;

    public OAuthLogin(String token) {
        this.token = token;
    }
}

