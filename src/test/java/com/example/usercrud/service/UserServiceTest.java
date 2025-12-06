package com.example.usercrud.service;

import com.example.usercrud.model.User;
import com.example.usercrud.repository.InMemoryUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private InMemoryUserRepository repository;
    private UserService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryUserRepository();
        service = new UserService(repository);
    }

    @Test
    void createUser_ShouldCreateUser_WhenDataIsValid() {
        User user = service.createUser("Maria", "maria@example.com");

        assertNotNull(user.getId());
        assertEquals("Maria", user.getName());
        assertEquals("maria@example.com", user.getEmail());
        assertTrue(user.isActive());
        assertEquals(1, service.listUsers().size());
    }

    @Test
    void createUser_ShouldTrimNameAndEmail_WhenTheyContainSpaces() {
        User user = service.createUser("  João  ", "  joao@example.com  ");

        assertEquals("João", user.getName());
        assertEquals("joao@example.com", user.getEmail());
    }

    @Test
    void createUser_ShouldThrowException_WhenNameIsBlank() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> service.createUser("   ", "user@example.com")
        );
        assertTrue(ex.getMessage().toLowerCase().contains("nome"));
    }

    @Test
    void createUser_ShouldThrowException_WhenEmailIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createUser("User", null));
    }

    @Test
    void createUser_ShouldThrowException_WhenEmailIsInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createUser("User", "email-sem-arroba"));
    }

    @Test
    void createUser_ShouldThrowException_WhenEmailAlreadyExists() {
        service.createUser("User1", "user@example.com");

        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> service.createUser("User2", "user@example.com")
        );
        assertTrue(ex.getMessage().toLowerCase().contains("já existe"));
    }

    @Test
    void getUserById_ShouldReturnUser_WhenIdExists() {
        User created = service.createUser("Ana", "ana@example.com");

        User found = service.getUserById(created.getId());

        assertEquals(created.getId(), found.getId());
    }

    @Test
    void getUserById_ShouldThrowException_WhenIdDoesNotExist() {
        assertThrows(NoSuchElementException.class,
                () -> service.getUserById("id-inexistente"));
    }

    @Test
    void updateUser_ShouldUpdateNameAndEmail_WhenValid() {
        User created = service.createUser("Carlos", "carlos@example.com");

        User updated = service.updateUser(
                created.getId(),
                "Carlos Souza",
                "c.souza@example.com",
                null
        );

        assertEquals("Carlos Souza", updated.getName());
        assertEquals("c.souza@example.com", updated.getEmail());
    }

    @Test
    void updateUser_ShouldThrowException_WhenUserDoesNotExist() {
        assertThrows(NoSuchElementException.class,
                () -> service.updateUser("id-inexistente", "Novo Nome", null, null));
    }

    @Test
    void updateUser_ShouldThrowException_WhenNewEmailAlreadyExists() {
        User u1 = service.createUser("User1", "user1@example.com");
        User u2 = service.createUser("User2", "user2@example.com");

        assertThrows(IllegalStateException.class,
                () -> service.updateUser(u2.getId(), null, "user1@example.com", null));
    }

    @Test
    void deleteUser_ShouldRemoveUser_WhenIdExists() {
        User created = service.createUser("Delete", "delete@example.com");

        service.deleteUser(created.getId());

        assertEquals(0, service.listUsers().size());
        assertThrows(NoSuchElementException.class,
                () -> service.getUserById(created.getId()));
    }

    @Test
    void deleteUser_ShouldThrowException_WhenIdDoesNotExist() {
        assertThrows(NoSuchElementException.class,
                () -> service.deleteUser("id-inexistente"));
    }
}
