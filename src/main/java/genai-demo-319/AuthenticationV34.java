```java
// Application.java
package com.example.loginapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    /**
     * Main method to launch the Spring Boot application.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// UserController.java
package com.example.loginapi.controller;

import com.example.loginapi.model.User;
import com.example.loginapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    /**
     * Handles user login requests.
     * @param user User object containing login details.
     * @return a response indicating success or failure of login.
     */
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        if (userService.validateUser(user)) {
            return ResponseEntity.ok("Login Successful");
        } else {
            return ResponseEntity.status(401).body("Login Failed: Invalid credentials");
        }
    }
}

// UserService.java
package com.example.loginapi.service;

import com.example.loginapi.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    /**
     * Validates user credentials.
     * @param user User object with login details.
     * @return true if valid credentials, false otherwise.
     */
    public boolean validateUser(User user) {
        // Implement user validation logic here
        // Security: Always hash passwords in production
        // For simplicity, example assumes valid credentials are username: user, password: pass
        return "user".equals(user.getUsername()) && "pass".equals(user.getPassword());
    }
}

// User.java
package com.example.loginapi.model;

public class User {
    
    private String username;
    private String password;

    /**
     * Gets the username.
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * @param username the username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * @param password the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

// application.properties
server.port=8080

// Security: Ensure sensitive data like passwords are never hardcoded or stored in plain text
```