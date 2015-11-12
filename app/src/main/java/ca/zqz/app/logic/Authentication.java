package ca.zqz.app.logic;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by dylan on 11/11/15.
 */
public class Authentication {
    private static boolean logged_in = false;

    public Authentication() {
        logged_in = false;
    }

    public boolean isLoggedIn() {
        return this.logged_in;
    }

    public boolean Login(String username_or_email, String password) {
        return authenticate(username_or_email, password) != null;
    }

    public boolean LoginOauth(String token) {
        return authenticateOauth(token) != null;
    }

    private User authenticateOauth(String token) {
        OAuthLogin n = new OAuthLogin(token);

        if (n.login()) {
            return n.getUser();
        }

        return null;
    }

    private User authenticate(String login, String password) {
        StandardLogin n = new StandardLogin(login, password);

        if (n.login()) {
            return n.getUser();
        }

        return null;
    }
}
