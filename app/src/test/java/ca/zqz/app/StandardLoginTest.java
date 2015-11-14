package ca.zqz.app;

import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.MockResponse;

import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;

import ca.zqz.app.logic.StandardLogin;
import ca.zqz.app.logic.User;

import static org.junit.Assert.assertEquals;

public class StandardLoginTest {
    MockWebServer mockServer;
    StandardLogin testLogin;
    User testUser = new User();

    @Test
    public void testToJson() {
        testLogin = new StandardLogin("foo", "bar");
        assertEquals(testLogin.toJson(), "{\"login\":\"foo\",\"password\":\"bar\"}");
    }

    @Test
    public void testSuccessfulLogin() throws IOException {
        MockResponse successResponse = new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setBody(testUser.toJson())
                .setStatus("HTTP/1.1 202 Ok");

        mockServer = new MockWebServer();
        mockServer.enqueue(successResponse);
        mockServer.start();

        testLogin = new StandardLogin("foo", "bar");
        testLogin.setEndpointUrl(mockServer.url(testLogin.endpoint));
        assertEquals(testLogin.login(), true);

        User u = testLogin.getUser();
        Assert.assertNotNull(u);

        mockServer.shutdown();
    }

    @Test
    public void testFailedLogin() throws IOException {
        MockResponse failureResponse = new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setBody("{\"error\":\"invalid credentials\"}")
                .setStatus("HTTP/1.1 401 Invalid Credentials");

        mockServer = new MockWebServer();
        mockServer.enqueue(failureResponse);
        mockServer.start();

        testLogin = new StandardLogin("foo", "bar");
        assertEquals(testLogin.login(), false);

        User u = testLogin.getUser();
        Assert.assertNull(u);

        mockServer.shutdown();
    }
}
