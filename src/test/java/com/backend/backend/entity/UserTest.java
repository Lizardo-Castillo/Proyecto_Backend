package com.backend.backend.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testUserConstructor() {
        User user = new User("John", "Doe", "john.doe@example.com");
        assertNotNull(user, "User should not be null");
        assertEquals("John", user.getFirstName(), "First name should be John");
        assertEquals("Doe", user.getLastName(), "Last name should be Doe");
        assertEquals("john.doe@example.com", user.getEmailId(), "Email should be john.doe@example.com");
    }

    @Test
    public void testSettersAndGetters() {
        User user = new User();
        user.setFirstName("Jane");
        user.setLastName("Smith");
        user.setEmailId("jane.smith@example.com");
        assertEquals("Jane", user.getFirstName(), "First name should be Jane");
        assertEquals("Smith", user.getLastName(), "Last name should be Smith");
        assertEquals("jane.smith@example.com", user.getEmailId(), "Email should be jane.smith@example.com");
    }

    @Test
    public void testUserId() {
        User user = new User("Alice", "Brown", "alice.brown@example.com");
        assertEquals(0, user.getId(), "The default ID should be 0 before persisting.");
    }

    @Test
    public void testSetId() {
        User user = new User("Bob", "White", "bob.white@example.com");
    
        user.setId(10);
        assertEquals(10, user.getId(), "The ID should be set to 10");
    }
}