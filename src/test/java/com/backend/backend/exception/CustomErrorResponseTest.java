package com.backend.backend.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class CustomErrorResponseTest {
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

    @Test
    void testEquals_SameObject() {
        CustomErrorResponse response = new CustomErrorResponse();
        assertThat(response.equals(response)).isTrue();
    }

    @Test
    void testEquals_NullObject() {
        CustomErrorResponse response = new CustomErrorResponse();
        assertThat(response.equals(null)).isFalse();
    }

    @Test
    void testEquals_DifferentClass() {
        CustomErrorResponse response = new CustomErrorResponse();
        assertThat(response.equals("Some String")).isFalse();
    }

    @Test
    void testEquals_DifferentValues() {
        CustomErrorResponse response1 = new CustomErrorResponse(LocalDateTime.now(), 404, "Not Found");
        CustomErrorResponse response2 = new CustomErrorResponse(LocalDateTime.now(), 500, "Internal Server Error");
        assertThat(response1.equals(response2)).isFalse();
    }

    @Test
    void testEquals_SameValues() {
        LocalDateTime now = LocalDateTime.now();
        CustomErrorResponse response1 = new CustomErrorResponse(now, 404, "Not Found");
        CustomErrorResponse response2 = new CustomErrorResponse(now, 404, "Not Found");
        assertThat(response1.equals(response2)).isTrue();
    }

    @Test
    void testEquals_DifferentTimestamp() {
        CustomErrorResponse response1 = new CustomErrorResponse(LocalDateTime.now(), 404, "Not Found");
        CustomErrorResponse response2 = new CustomErrorResponse(LocalDateTime.now().plusSeconds(1), 404, "Not Found");
        assertThat(response1.equals(response2)).isFalse();
    }
}