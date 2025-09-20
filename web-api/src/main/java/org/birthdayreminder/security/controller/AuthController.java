package org.birthdayreminder.security.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;import java.util.HashMap;
import java.util.List;import java.util.Map;import java.util.stream.Collectors;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
        private final String jwtSecret;

        public AuthController(AuthenticationManager authenticationManager, @Value("${spring.security.jwt-secret}") String jwtSecret) {
            this.authenticationManager = authenticationManager;
            this.jwtSecret = jwtSecret;
        }

        @PostMapping("/login")
        public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

			SecurityContextHolder.getContext().setAuthentication(authentication);
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			// Извлекаем роли
			List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String jwt = Jwts.builder()
					.subject(userDetails.getUsername())
					.claim("roles", roles) // Добавляем роли в токен
					.issuedAt(new Date())
					.expiration(new Date(System.currentTimeMillis() + 86400000)) // 24 часа
                    .signWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                    .compact();

            Map<String, Object> response = new HashMap<>();
            response.put("token", jwt);
            return ResponseEntity.ok(response);
        }

    public static class LoginRequest {
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