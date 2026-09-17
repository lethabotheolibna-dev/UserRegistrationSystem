/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

/**
 *
 * @author Student
 */
public class Login {

    // Variables to store the user's information
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellPhone;

    // Variable to store whether login was successful
    private boolean loginStatus;

    // Constructor
    public Login(String firstName, String lastName, String username,
                 String password, String cellPhone) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
    }

    // Checks if the username is correctly formatted
    public boolean checkUserName() {

        return username.contains("_") && username.length() <= 5;
    }

    // Checks if the password meets the requirements
    public boolean checkPasswordComplexity() {

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        // Check every character in the password
        for (int i = 0; i < password.length(); i++) {

            char character = password.charAt(i);

            // Check for a capital letter
            if (Character.isUpperCase(character)) {
                hasCapital = true;
            }

            // Check for a number
            if (Character.isDigit(character)) {
                hasNumber = true;
            }

            // Check for a special character
            if (!Character.isLetterOrDigit(character)) {
                hasSpecial = true;
            }
        }

        // Password must meet all four requirements
        return password.length() >= 8
                && hasCapital
                && hasNumber
                && hasSpecial;
    }

    // Checks the South African cell phone number
    public boolean checkCellPhoneNumber() {

        /*
         * +27 = South African international country code
         * [0-9]{9} = exactly 9 digits after +27
         */
        String cellPhonePattern = "^\\+27[0-9]{9}$";

        return cellPhone.matches(cellPhonePattern);
    }

    // Registers the user
    public String registerUser() {

        if (!checkUserName()) {

            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";

        }

        if (!checkPasswordComplexity()) {

            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";

        }

        if (!checkCellPhoneNumber()) {

            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";

        }

        return "User successfully registered.";
    }

    // Checks whether the entered login details are correct
    public boolean loginUser(String enteredUsername, String enteredPassword) {

        if (enteredUsername.equals(username)
                && enteredPassword.equals(password)) {

            loginStatus = true;

        } else {

            loginStatus = false;
        }

        return loginStatus;
    }

    // Returns the login message
    public String returnLoginStatus() {

        if (loginStatus) {

            return "Welcome " + firstName + ", " + lastName
                    + " it is great to see you again.";

        } else {

            return "Username or password incorrect, please try again.";
        }
    }
}
