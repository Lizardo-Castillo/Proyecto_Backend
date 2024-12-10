package com.backend.backend.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.backend.backend.entity.User;
import com.backend.backend.repository.UserRepository;
import com.backend.backend.exception.ResourceNotFoundException;


public class UserControllerTest {
 
    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    public UserControllerTest() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testGetAllUsers() {
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(new User("John", "Doe", "john.doe@example.com"));
        mockUsers.add(new User("Jane", "Smith", "jane.smith@example.com"));
        when(userRepository.findAll()).thenReturn(mockUsers);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody().size()).isEqualTo(2);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testCreateUser() {
        User mockUser = new User("John", "Doe", "john.doe@example.com");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        ResponseEntity<User> response = userController.createUser(mockUser);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getFirstName()).isEqualTo("John");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserById_UserExists() {
        User mockUser = new User("John", "Doe", "john.doe@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        ResponseEntity<User> response = userController.getUserById(1L);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getFirstName()).isEqualTo("John");
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetUserById_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userController.getUserById(1L));
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateUser() {
        User existingUser = new User("John", "Doe", "john.doe@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));

        User updatedUserDetails = new User("Jane", "Doe", "jane.doe@example.com");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(1L); // Simula la persistencia asignando el mismo ID
            return user;
        });

        ResponseEntity<User> response = userController.updateUser(1L, updatedUserDetails);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getFirstName()).isEqualTo("Jane");
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteUser() {
        User mockUser = new User("John", "Doe", "john.doe@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        ResponseEntity<Map<String, Boolean>> response = userController.deleteUser(1L);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("deleted")).isTrue();
        verify(userRepository, times(1)).delete(mockUser);
    }
}
