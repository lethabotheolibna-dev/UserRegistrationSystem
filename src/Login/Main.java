/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

/**
 *
 * @author Student
 */
public class Main {
  
    public static void main(String[] args) {

        // Create a user
        Login user = new Login(
                "Lethabo",
                "Theo",
                "lee_t",
                "Ch&&sec@ke99!",
                "+27828404613"
        );

        // Registration
        System.out.println(user.registerUser());

        // Login
        boolean result = user.loginUser(
                "lee_t",
                "Ch&&sec@ke99!"
        );

        System.out.println("Login successful: " + result);

        // Display login message
        System.out.println(user.returnLoginStatus());
    }
}

