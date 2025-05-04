package org.birthdayreminder.security.service;import org.birthdayreminder.domain.model.User;import org.birthdayreminder.domain.repository.UserRepository;import org.springframework.security.core.GrantedAuthority;import org.springframework.security.core.authority.SimpleGrantedAuthority;import org.springframework.security.core.userdetails.UserDetails;import org.springframework.security.core.userdetails.UserDetailsService;import org.springframework.security.core.userdetails.UsernameNotFoundException;import org.springframework.stereotype.Service;import java.util.List;import java.util.stream.Collectors; /**
 * @author a.mishkin
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String foreignId) throws UsernameNotFoundException {
        User user = userRepository.getUserByForeignId(Long.valueOf(foreignId))
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + foreignId));

        // TODO Преобразуем роли в GrantedAuthority
        //TODO User or User Entity????   --------------- ОБА
        List<GrantedAuthority> authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role))
            .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            authorities
        );
    }
}