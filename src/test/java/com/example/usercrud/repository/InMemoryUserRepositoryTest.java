package com.example.usercrud.repository;

import com.example.usercrud.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserRepositoryTest {

    private InMemoryUserRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryUserRepository();
    }

    @Test
    void save_ShouldStoreUser() {
        User user = new User("Teste", "teste@example.com");

        repository.save(user);

        assertTrue(repository.existsById(user.getId()));
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenNotFound() {
        Optional<User> result = repository.findById("id-inexistente");

        assertTrue(result.isEmpty());
    }

    @Test
    void findByEmail_ShouldFindUserIgnoringCase() {
        User user = new User("User", "user@example.com");
        repository.save(user);

        Optional<User> result = repository.findByEmail("USER@example.com");

        assertTrue(result.isPresent());
        assertEquals(user.getId(), result.get().getId());
    }

    @Test
    void deleteById_ShouldRemoveUser() {
        User user = new User("User", "user@example.com");
        repository.save(user);

        repository.deleteById(user.getId());

        assertFalse(repository.existsById(user.getId()));
        assertEquals(0, repository.findAll().size());
    }

    @Test
    void findAll_ShouldReturnAllUsers() {
        repository.save(new User("U1", "u1@example.com"));
        repository.save(new User("U2", "u2@example.com"));

        List<User> all = repository.findAll();

        assertEquals(2, all.size());
    }
}
