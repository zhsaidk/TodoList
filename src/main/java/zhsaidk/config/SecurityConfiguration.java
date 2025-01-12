package zhsaidk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeRequests ->{
                    authorizeRequests.requestMatchers("/users").hasAuthority("ADMIN");
                    authorizeRequests.requestMatchers("/users/registration", "/login").permitAll();
                    authorizeRequests.anyRequest().authenticated();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(login->{
                    login.loginPage("/login");
                    login.defaultSuccessUrl("/users");
                })
                .build();
    }
}
