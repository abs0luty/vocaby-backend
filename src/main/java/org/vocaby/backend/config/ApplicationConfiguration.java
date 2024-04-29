package org.vocaby.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.vocaby.backend.dao.UserRepository;
import org.vocaby.backend.exception.FilterChainExceptionHandler;

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private final UserRepository userRepository;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Bean
    public FilterChainExceptionHandler filterChainExceptionHandler() {
        return new FilterChainExceptionHandler(handlerExceptionResolver);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
