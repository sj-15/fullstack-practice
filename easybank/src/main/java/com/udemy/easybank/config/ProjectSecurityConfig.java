package com.udemy.easybank.config;

import com.udemy.easybank.exception.CustomBasicAuthenticationEntryPoint;
import com.udemy.easybank.filter.CsrfCookieFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import javax.sql.DataSource;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler ctrah = new CsrfTokenRequestAttributeHandler();
        http.securityContext(sc -> sc.requireExplicitSave(false))
                .sessionManagement(smc -> smc.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .csrf(csrfConfig -> csrfConfig.ignoringRequestMatchers("/register").csrfTokenRequestHandler(ctrah).csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterBefore(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .requiresChannel((requests) -> requests.anyRequest().requiresInsecure())
                .authorizeHttpRequests((requests) -> requests.requestMatchers("/myAccount", "/myLoans", "/user").authenticated().requestMatchers("/notices", "/register", "/error", "/invalidSession").permitAll());
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(hba -> hba.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        return http.build();
    }

//    @Bean
//    UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
