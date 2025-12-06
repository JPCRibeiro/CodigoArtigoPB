package com.example.usercrud;

import com.example.usercrud.model.User;
import com.example.usercrud.repository.InMemoryUserRepository;
import com.example.usercrud.service.UserService;

public class App {

    public static void main(String[] args) {
        InMemoryUserRepository repo = new InMemoryUserRepository();
        UserService service = new UserService(repo);

        User u1 = service.createUser("Alice", "alice@example.com");
        User u2 = service.createUser("Bob", "bob@example.com");

        System.out.println("Usuários cadastrados:");
        service.listUsers().forEach(u ->
                System.out.printf("- %s (%s)%n", u.getName(), u.getEmail())
        );

        service.updateUser(u1.getId(), "Alice Silva", null, null);

        System.out.println("Após atualização:");
        service.listUsers().forEach(u ->
                System.out.printf("- %s (%s)%n", u.getName(), u.getEmail())
        );
    }
}
