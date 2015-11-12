package ca.zqz.app;

import org.junit.Test;

import ca.zqz.app.logic.User;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void testToJson() {
        User u = new User();
        assertEquals(u.toJson(), "d");
    }
}
