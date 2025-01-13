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
                    authorizeRequests.requestMatchers("tasks").hasAnyAuthority("ADMIN", "USER");
                    authorizeRequests.requestMatchers("/registration", "/users/create","/login").permitAll();
                    authorizeRequests.anyRequest().authenticated();
                })
                .formLogin(login->{
                    login.loginPage("/login");
                    login.defaultSuccessUrl("/tasks");
                })
                .build();
    }
}
