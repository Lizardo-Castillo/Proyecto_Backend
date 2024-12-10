package com.backend.backend.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class CustomErrorResponseTest {
    @Test
    void testDefaultConstructor() {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        assertNotNull(errorResponse.getTimestamp());
    }

    @Test
    void testParameterizedConstructor() {
        LocalDateTime now = LocalDateTime.now();
        CustomErrorResponse errorResponse = new CustomErrorResponse(now, 404, "Not Found");
        assertEquals(now, errorResponse.getTimestamp());
        assertEquals(404, errorResponse.getStatus());
        assertEquals("Not Found", errorResponse.getError());
    }

    @Test
    void testSettersAndGetters() {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        LocalDateTime now = LocalDateTime.now();

        errorResponse.setTimestamp(now);
        errorResponse.setStatus(500);
        errorResponse.setError("Internal Server Error");

        assertEquals(now, errorResponse.getTimestamp());
        assertEquals(500, errorResponse.getStatus());
        assertEquals("Internal Server Error", errorResponse.getError());
    }

    @Test
    void testToString() {
        LocalDateTime now = LocalDateTime.now();
        CustomErrorResponse errorResponse = new CustomErrorResponse(now, 400, "Bad Request");
        String expected = "CustomErrorResponse{timestamp=" + now +
                          ", status=400, error='Bad Request'}";
        assertEquals(expected, errorResponse.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDateTime now = LocalDateTime.now();
        CustomErrorResponse error1 = new CustomErrorResponse(now, 400, "Bad Request");
        CustomErrorResponse error2 = new CustomErrorResponse(now, 400, "Bad Request");

        assertEquals(error1, error2);
        assertEquals(error1.hashCode(), error2.hashCode());
    }
}
