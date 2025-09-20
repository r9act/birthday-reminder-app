package org.birthdayreminder.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {
	  @Bean
        public InMemoryUserDetailsManager userDetailsService() {
            return new InMemoryUserDetailsManager(
                User.withUsername("user")
                    .password("$2y$10$Y0qBFOydUTV2Yz4f2liNNOGoykjWWBA7mVstxxbx0e63nMRrD01r.")
                    .roles("USER")
                    .passwordEncoder(p -> p)
                    .build(),

                User.withUsername("developer")
                    .password("$2y$10$kJsQ843mdCfR6Pgmcg0HdeHygOCNtyM9.mPmqA2PLyJO93pQjxNiC")
                    .roles("DEVELOPER")
                    .passwordEncoder(p -> p)
                    .build()
            );
        }
}
