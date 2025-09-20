package org.birthdayreminder.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SignupController {

    private final InMemoryUserDetailsManager userDetailsManager;
    private final BCryptPasswordEncoder passwordEncoder;

    public SignupController(InMemoryUserDetailsManager userDetailsManager, BCryptPasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody SignupRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
			//TODO
            try {
                userDetailsManager.loadUserByUsername(request.getUsername());
                response.put("success", false);
                response.put("message", "Имя пользователя уже занято");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            } catch (UsernameNotFoundException e) {
                //TODO
                userDetailsManager.createUser(
                        User.withUsername(request.getUsername())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .roles("USER")
                                .build()
                );
                response.put("success", true);
                response.put("message", "Регистрация успешна");
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Ошибка регистрации: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static class SignupRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}