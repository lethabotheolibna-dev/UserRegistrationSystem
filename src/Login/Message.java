/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;


/**
 *
 * @author Student
 */
public class Message {
 
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    public Message(int messageNumber,
                   String recipient,
                   String messageText) {

        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {
        return String.format(
            "%010d",
        new java.util.Random().nextInt(1_000_000_000)
        );
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() == 10;
    }

    public String checkRecipientCell() {
    if (recipient != null 
        && recipient.matches("\\+27[0-9]{9}")) {
        return "Cell phone number successfully captured.";
    }
    return "Cell phone number is incorrectly formatted or "
         + "does not contain an international code. "
         + "Please correct the number and try again.";
    }

    public String createMessageHash() {

        String[] words = messageText.trim().split("\\s+");

        String firstWord = words[0]
                .replaceAll("[^a-zA-Z0-9]", "");

        String lastWord = words[words.length - 1]
                .replaceAll("[^a-zA-Z0-9]", "");

        return (messageID.substring(0, 2) + ":"
                + messageNumber + ":"
                + firstWord + lastWord).toUpperCase();
    }

    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        }

        int excess = messageText.length() - 250;

        return "Message exceeds 250 characters by "
                + excess + "; please reduce the size.";
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public int getMessageNumber() {
        return messageNumber;
    }
}
    

