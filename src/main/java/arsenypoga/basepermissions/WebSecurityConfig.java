package arsenypoga.basepermissions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests((requests) -> requests

        .requestMatchers("/protected", "/admin")
        .hasRole("PROTECTED")
        .anyRequest()
        .permitAll())
        .httpBasic(Customizer.withDefaults())
        .build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails loggedInUser = User.withDefaultPasswordEncoder()
        .username("user")
        .password("password")
        .roles("PROTECTED")
        .build();

    UserDetails protectedUser = User.withDefaultPasswordEncoder()
        .username("user1")
        .password("password1")
        .roles("ALL")
        .build();

    UserDetails adminUser = User.withDefaultPasswordEncoder()
        .username("admin")
        .password("password")
        .roles("ADMIN", "PROTECTED")
        .build();

    return new InMemoryUserDetailsManager(loggedInUser, protectedUser, adminUser);
  }
}
