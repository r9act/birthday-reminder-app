package org.birthdayreminder.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;import io.jsonwebtoken.JwtException;import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;import org.springframework.security.core.authority.SimpleGrantedAuthority;import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;import java.util.List;import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
	private final String jwtSecret;

    public JwtAuthenticationFilter(UserDetailsService userDetailsService,@Value("${spring.security.jwt-secret}") String jwtSecret) {
        this.userDetailsService = userDetailsService;
		this.jwtSecret = jwtSecret;
	}

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
           throws ServletException, IOException {
       String authHeader = request.getHeader("Authorization");
       if (authHeader == null || !authHeader.startsWith("Bearer ")) {
           filterChain.doFilter(request, response);
           return;
       }

       String jwt = authHeader.substring(7);
       try {
           Claims claims = Jwts.parser()
                   .verifyWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                   .build()
                   .parseSignedClaims(jwt)
                   .getPayload();

           String username = claims.getSubject();
		   List<String> roles = claims.get("roles", List.class); // Извлекаем роли

           if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
               UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			   // Проверяем, что роли в токене соответствуют ролям пользователя
				List<GrantedAuthority> authorities = roles.stream()
                                   .map(SimpleGrantedAuthority::new)
                                   .collect(Collectors.toList());

               UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                       userDetails, null, userDetails.getAuthorities());
               authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authToken);
           }

           filterChain.doFilter(request, response);
       } catch (ExpiredJwtException e) {
		   response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		   response.setContentType("application/json");
		   response.getWriter().write("{\"status\": 401, \"error\": \"Unauthorized\", \"message\": \"Token expired\"}");
	   } catch (JwtException e) {
		   response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		   response.setContentType("application/json");
		   response.getWriter().write("{\"status\": 401, \"error\": \"Unauthorized\", \"message\": \"Invalid token\"}");
	   } catch (Exception e) {
		   response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		   response.setContentType("application/json");
		   response.getWriter().write("{\"status\": 401, \"error\": \"Unauthorized\", \"message\": \"Error processing token\"}");
	   }
   }
}