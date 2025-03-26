package com.udemy.easybank.config;

import com.udemy.easybank.service.EasyBankUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EasyBankAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private EasyBankUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails != null && passwordEncoder.matches(pwd, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userDetails, pwd, userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Invalid username or password");
        }
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
