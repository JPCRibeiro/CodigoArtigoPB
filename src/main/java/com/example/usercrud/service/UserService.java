package com.example.usercrud.service;

import com.example.usercrud.model.User;
import com.example.usercrud.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public User createUser(String name, String email) {
        validateName(name);
        validateEmail(email);

        if (repository.existsByEmail(email)) {
            throw new IllegalStateException("Já existe usuário com esse e-mail");
        }

        User user = new User(name.trim(), email.trim());
        return repository.save(user);
    }

    public User getUserById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado: " + id));
    }

    public List<User> listUsers() {
        return repository.findAll();
    }

    public User updateUser(String id, String newName, String newEmail, Boolean newActive) {
        User existing = getUserById(id); // lança NoSuchElementException se não existir

        if (newName != null) {
            validateName(newName);
            existing.setName(newName.trim());
        }

        if (newEmail != null) {
            validateEmail(newEmail);
            // se o email for alterado, garantir unicidade
            repository.findByEmail(newEmail.trim())
                    .filter(user -> !user.getId().equals(id))
                    .ifPresent(user -> {
                        throw new IllegalStateException("Já existe outro usuário com esse e-mail");
                    });
            existing.setEmail(newEmail.trim());
        }

        if (newActive != null) {
            existing.setActive(newActive);
        }

        return repository.save(existing);
    }

    public void deleteUser(String id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Usuário não encontrado: " + id);
        }
        repository.deleteById(id);
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("E-mail não pode ser vazio");
        }
        // BUG PROPOSITAL: verificação de formato removida
        // Antes:
        // if (!email.contains("@")) {
        // throw new IllegalArgumentException("E-mail inválido");
        // }
    }
}
