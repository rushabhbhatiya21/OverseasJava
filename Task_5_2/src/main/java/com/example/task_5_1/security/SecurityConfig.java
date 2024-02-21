package com.example.task_5_1.security;

import com.example.task_5_1.model.UserDao;
import com.example.task_5_1.services.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@SuppressWarnings("ALL")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
//@EnableMethodSecurity
public class SecurityConfig {

//    private final JwtAuthFilter jwtAuthFilter;
    private final IUserRepository userRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeRequests
                (authorizeRequests -> {
                    try {
                        authorizeRequests
                                .requestMatchers("api/home/getUsers", "api/home/adminIndex","api/home/adminPage" ,"api/home/addUser")
                                        .hasAuthority(UserDao.Role.ADMIN.name())
                                .requestMatchers("api/auth/authenticate", "api/home/userIndex")
                                        .authenticated()
                                .and()
                                .httpBasic(withDefaults())
                                .formLogin()
                                    .successHandler((request, response, authentication) -> {
                                            for (GrantedAuthority authority : authentication.getAuthorities()) {
                                                if (authority.getAuthority().equals(UserDao.Role.USER.toString())) {
                                                    response.sendRedirect("api/home/userIndex"); // Redirect to user page for ROLE_USER
                                                    return;
                                                } else if (authority.getAuthority().equals(UserDao.Role.ADMIN.toString())) {
                                                    response.sendRedirect("api/home/adminIndex"); // Redirect to admin page for ROLE_ADMIN
                                                    return;
                                                }
                                            }
                                        })
                                .and()
//                                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                                                .invalidSessionUrl("/logout?expired")
//                                                )
                                .logout(l -> l
                                        .deleteCookies("JSESSIONID")
                                        .invalidateHttpSession(true)
                                        .clearAuthentication(true)
                                        .logoutSuccessUrl("/api/home"))

                                .authenticationProvider(authenticationProvider())
//                              .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                        ;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoDetailsService(userRepository);
    }
}
