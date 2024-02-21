package com.example.restaurant.security;

import com.example.restaurant.model.UserDao;
import com.example.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@SuppressWarnings("ALL")
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;

    @Autowired
    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(
                        requests -> {
                            try {
                                requests
                                        .requestMatchers("/api/loginPage", "/api/register/**",
                                                "/api/loadAdminRegisterPage", "/api/loadUserRegisterPage").permitAll()
                                        .requestMatchers("api/admin/**").hasAuthority(UserDao.Role.ADMIN.name())
                                        .requestMatchers("/api/user/**").hasAuthority(UserDao.Role.USER.name())
                                        .anyRequest().permitAll()
                                .and()
                                        .formLogin()
                                        .loginPage("/api/loginPage").permitAll()
                                        .successHandler((request, response, authentication) -> {
                                            for (GrantedAuthority authority : authentication.getAuthorities()) {
                                                if (authority.getAuthority().equals(UserDao.Role.USER.name())) {
                                                    response.sendRedirect("/api/user/loadUserDashboard");
                                                    return;
                                                } else if (authority.getAuthority().equals(UserDao.Role.ADMIN.name())) {
                                                    System.out.println("redirecting admin");
                                                    response.sendRedirect("/api/admin/loadAdminDashboard");
                                                    return;
                                                }
                                            }
                                        })
                                        .and()
                                        .logout(l -> l
                                                .deleteCookies("JSESSIONID")
                                                .invalidateHttpSession(true)
                                                .clearAuthentication(true)
//                                                .logoutSuccessUrl("/abc"));
                                                .logoutSuccessUrl("/api/loginPage"))
                                ;
                            }
                            catch (Exception e) {
                                throw new RuntimeException(e.getMessage());
                            }
                        }
                );
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService customUserDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                Optional<UserDao> user = userRepository.findUserDaoByEmail(email);
                return user.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("User Does Not Exist"));
            }
        };
    }
}
