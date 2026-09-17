/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Login;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    
    @Test
    public void testUsernameCorrectlyFormatted() {

        Login user = new Login(
                "Lethabo",
                "Theo",
                "lee_t",
                "Ch&&sec@ke99!",
                "+27828404613"
        );

        assertTrue(user.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {

        Login user = new Login(
                "Lethabo",
                "Theo",
                "lee!!!!!!!",
                "Ch&&sec@ke99!",
                "+27828404613"
        );

        assertFalse(user.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexity() {

        Login user = new Login(
                "Lethabo",
                "Theo",
                "lee_t",
                "Ch&&sec@ke99!",
                "+27828404613"
        );

        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {

        Login user = new Login(
                "Lethabo",
                "Theo",
                "lee_t",
                "password",
                "+27828404613"
        );

        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {

        Login user = new Login(
                "Lethabo",
                "Theo",
                "lee_t",
                "Ch&&sec@ke99!",
                "+27828404613"
        );

        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {

        Login user = new Login(
                "Lethabo",
                "Theo",
                "lee_t",
                "Ch&&sec@ke99!",
                "08966553"
        );

        assertFalse(user.checkCellPhoneNumber());
    }

    @Test
    public void testLoginSuccessful() {

        Login user = new Login(
                "Lethabo",
                "Theo",
                "lee_t",
                "Ch&&sec@ke99!",
                "+27828404613"
        );

        assertTrue(
                user.loginUser("lee_t", "Ch&&sec@ke99!")
        );
    }

    @Test
    public void testLoginFailed() {

        Login user = new Login(
                "Lethabo",
                "Theo",
                "lee_t",
                "Ch&&sec@ke99!",
                "+27828404613"
        );

        assertFalse(
                user.loginUser("wrong", "wrong")
        );
    }
}
