/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testValidMessageLength() {
        Message message = new Message(
            0, "+27718693002", "Hello"
        );

        assertEquals(
            "Message ready to send.",
            message.checkMessageLength()
        );
    }

    @Test
    public void testMessageOver250Characters() {
        String longText = "A".repeat(251);

        Message message = new Message(
            0, "+27718693002", longText
        );

        assertEquals(
            "Message exceeds 250 characters by 1; "
            + "please reduce the size.",
            message.checkMessageLength()
        );
    }

    @Test
    public void testValidRecipient() {
        Message message = new Message(
            0, "+27718693002", "Hello"
        );

        assertEquals(
            "Cell phone number successfully captured.",
            message.checkRecipientCell()
        );
    }

    @Test
    public void testInvalidRecipient() {
        Message message = new Message(
            1, "12345", "Hello"
        );

        assertEquals(
            "Cell phone number is incorrectly formatted or "
            + "does not contain an international code. "
            + "Please correct the number and try again.",
            message.checkRecipientCell()
        );
    }

    @Test
    public void testMessageIDHasTenCharacters() {
        Message message = new Message(
            0, "+27718693002", "Hello"
        );

        assertEquals(10, message.getMessageID().length());
    }

    @Test
    public void testMessageHashEndsWithFirstAndLastWords() {
        Message message = new Message(
            0,
            "+27718693002",
            "Hi Mike, can you join us for dinner tonight?"
        );

        assertTrue(
            message.getMessageHash().endsWith("HITONIGHT")
        );
    }
}