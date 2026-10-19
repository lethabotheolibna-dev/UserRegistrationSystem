package Login;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Login user = new Login(
                "Kyle",
                "Theo",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        System.out.print("Username: ");
        String username = input.nextLine();

        System.out.print("Password: ");
        String password = input.nextLine();

        boolean loggedIn = user.loginUser(username, password);

        if (!loggedIn) {
            System.out.println(
                    "Username or password incorrect, please try again."
            );
            input.close();
            return;
        }

        System.out.println(user.returnLoginStatus());
        System.out.println("Welcome to QuickChat.");

        List<Message> sentMessages = new ArrayList<>();
        List<Message> storedMessages = new ArrayList<>();

        int messageLimit = readPositiveNumber(
                input, "How many messages would you like to enter?"
        );

        int choice = 0;
        int messageNumber = 0;
        int messagesEntered = 0;

        while (choice != 3) {

            System.out.println("\n1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");

            try {
                choice = Integer.parseInt(input.nextLine());

                switch (choice) {

                    case 1:
                        if (messagesEntered >= messageLimit) {
                            System.out.println(
                                    "You have reached your message limit."
                            );
                            break;
                        }

                        System.out.print(
                                "Enter recipient (+27XXXXXXXXX): "
                        );
                        String recipient = input.nextLine();

                        System.out.print("Enter your message: ");
                        String text = input.nextLine();

                        Message message = new Message(
                                messageNumber,
                                recipient,
                                text
                        );

                        String recipientResult =
                                message.checkRecipientCell();

                        if (!recipientResult.equals(
                                "Cell phone number successfully captured.")) {
                            System.out.println(recipientResult);
                            break;
                        }

                        String lengthResult =
                                message.checkMessageLength();

                        if (!lengthResult.equals(
                                "Message ready to send.")) {
                            System.out.println(lengthResult);
                            break;
                        }

                        System.out.println(
                                "Message ID generated: "
                                + message.getMessageID()
                        );

                        System.out.println(
                                "Message Hash: "
                                + message.getMessageHash()
                        );

                        System.out.println("\n1. Send Message");
                        System.out.println("2. Disregard Message");
                        System.out.println("3. Store Message");
                        System.out.print("Choose an action: ");

                        String action = input.nextLine();

                        if (action.equals("1")) {

                            sentMessages.add(message);
                            messagesEntered++;
                            messageNumber++;

                            System.out.println(
                                    "Message successfully sent."
                            );
                            printMessage(message);

                        } else if (action.equals("2")) {

                            messagesEntered++;
                            messageNumber++;

                            System.out.println(
                                    "Message successfully disregarded."
                            );

                        } else if (action.equals("3")) {

                            storedMessages.add(message);

                            if (storeMessagesToJson(storedMessages)) {
                                messagesEntered++;
                                messageNumber++;

                                System.out.println(
                                        "Message successfully stored."
                                );
                            } else {
                                storedMessages.remove(
                                        storedMessages.size() - 1
                                );

                                System.out.println(
                                        "Unable to store the message."
                                );
                            }

                        } else {
                            System.out.println(
                                    "Invalid message option. "
                                    + "Choose 1, 2 or 3."
                            );
                        }

                        if (messagesEntered <= messageLimit) {
                            System.out.println(
                                    "Messages remaining: "
                                    + (messageLimit - messagesEntered)
                            );
                        }

                        break;

                    case 2:
                        if (sentMessages.isEmpty()) {
                            System.out.println(
                                    "No messages have been sent yet."
                            );
                        } else {
                            System.out.println(
                                    "\n--- Recently Sent Messages ---"
                            );

                            for (Message sent : sentMessages) {
                                printMessage(sent);
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println(
                                "Invalid option. Choose 1, 2 or 3."
                        );
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        "Please enter a valid number."
                );
            }
        }

        System.out.println(
                "Total messages sent: " + sentMessages.size()
        );

        input.close();
    }

    private static int readPositiveNumber(
            Scanner input, String prompt) {

        while (true) {
            System.out.println(prompt);

            try {
                int number = Integer.parseInt(input.nextLine());

                if (number > 0) {
                    return number;
                }

                System.out.println(
                        "Please enter a number greater than zero."
                );

            } catch (NumberFormatException e) {
                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }

    private static void printMessage(Message message) {
        System.out.println("\n--- Message Details ---");

        System.out.println(
                "Message ID: " + message.getMessageID()
        );

        System.out.println(
                "Message Hash: " + message.getMessageHash()
        );

        System.out.println(
                "Recipient: " + message.getRecipient()
        );

        System.out.println(
                "Message: " + message.getMessageText()
        );
    }

    private static boolean storeMessagesToJson(
            List<Message> messages) {

        StringBuilder json = new StringBuilder("[\n");

        for (int i = 0; i < messages.size(); i++) {
            Message m = messages.get(i);

            json.append("  {\n");

            json.append("    \"messageID\": \"")
                    .append(escapeJson(m.getMessageID()))
                    .append("\",\n");

            json.append("    \"messageNumber\": ")
                    .append(m.getMessageNumber())
                    .append(",\n");

            json.append("    \"messageHash\": \"")
                    .append(escapeJson(m.getMessageHash()))
                    .append("\",\n");

            json.append("    \"recipient\": \"")
                    .append(escapeJson(m.getRecipient()))
                    .append("\",\n");

            json.append("    \"messageText\": \"")
                    .append(escapeJson(m.getMessageText()))
                    .append("\"\n");

            json.append("  }");

            if (i < messages.size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("]");

        try {
            Files.writeString(
                    Path.of("messages.json"),
                    json.toString(),
                    StandardCharsets.UTF_8
            );

            return true;

        } catch (IOException e) {
            System.out.println(
                    "File error: " + e.getMessage()
            );
            return false;
        }
    }

    private static String escapeJson(String value) {
        return value.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
    }
}