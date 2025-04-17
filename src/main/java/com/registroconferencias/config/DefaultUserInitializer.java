package com.registroconferencias.config;

import com.registroconferencias.model.Rol;
import com.registroconferencias.model.UserEntity;
import com.registroconferencias.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String defaultEmail = "admin@root.com";

        if (userRepository.findByEmail(defaultEmail).isEmpty()) {
            UserEntity defaultUser = UserEntity.builder()
                    .email(defaultEmail)
                    .password(passwordEncoder.encode("AdminRoot$4"))
                    .rol(Rol.ADMIN)
                    .active(true)
                    .build();

            userRepository.save(defaultUser);
            System.out.println("✅ Usuario predeterminado creado: " + defaultEmail);
        } else {
            System.out.println("⚠️ El usuario predeterminado ya existe, no se creó uno nuevo.");
        }
    }
}
