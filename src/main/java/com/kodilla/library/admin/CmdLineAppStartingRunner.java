package com.kodilla.library.admin;

import com.kodilla.library.domain.User;
import com.kodilla.library.enums.AppUserRole;
import com.kodilla.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CmdLineAppStartingRunner implements CommandLineRunner {

    private UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CmdLineAppStartingRunner(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<User> user = repository.findByUsername("admin");
        if(user.isEmpty()) {
            User admin = new User("admin", "admin", "admin",
                    passwordEncoder.encode("admin"), AppUserRole.SUPER_ADMIN);
            repository.save(admin);
        }

    }
}
