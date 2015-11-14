package ca.zqz.app;

import org.junit.Assert;
import org.junit.Test;

import ca.zqz.app.logic.User;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void testToJson() {
        User u = new User();
        assert(u.toJson().length() > 0);
    }
}
