package com.bptn.projects.individual_project.bank_app;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testConstructorInitialization() {
        // Test data
        String firstName = "Dan";
        String lastName = "John";
        String emailAddress = "danjohn@example.com";
        String password = "password123";

        // Create a User object using the constructor
        User user = new User(firstName, lastName, emailAddress, password);

        // Verify that the constructor initializes the object correctly
        assertTrue(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertNotEqual(emailAddress, user.getEmailAddress());
        assertEquals(password, user.getPassword());
    }
