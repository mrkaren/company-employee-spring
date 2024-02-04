package org.example.companyemployee.config;

import lombok.RequiredArgsConstructor;
import org.example.companyemployee.entity.UserRole;
import org.example.companyemployee.security.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;

    private final UserDetailService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers("/user/register").permitAll()
                .requestMatchers("/loginPage").permitAll()
                .requestMatchers("/companies/add").hasAuthority(UserRole.ADMIN.name())
                .requestMatchers("/companies").hasAnyAuthority(UserRole.ADMIN.name(), UserRole.USER.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/login")
//                .successForwardUrl("/loginSuccess")
                .defaultSuccessUrl("/loginSuccess")
                .and()
                .logout()
                .logoutSuccessUrl("/");

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

}
