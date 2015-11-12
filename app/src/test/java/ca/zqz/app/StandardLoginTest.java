package ca.zqz.app;

import org.junit.Test;

import ca.zqz.app.logic.StandardLogin;
import ca.zqz.app.logic.User;

import static org.junit.Assert.assertEquals;

public class StandardLoginTest {
    @Test
    public void testToJson() {
        StandardLogin login = new StandardLogin("foo", "bar");
        assertEquals(login.toJson(), "{\"login\":\"foo\",\"password\":\"bar\"}");
    }
}
