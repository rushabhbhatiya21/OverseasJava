package com.example.overseas.security;

import com.example.overseas.model.UserDao;
import com.example.overseas.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Optional;

@SuppressWarnings("ALL")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private final UserRepository userRepository;

//    @Autowired
    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(
                        authorizeRequests -> {
                            try {
                                    authorizeRequests.requestMatchers("/roleSelect", "/register", "/loadRegistrationForm", "/login").permitAll()
                                        .requestMatchers("/student/**")
                                            .hasAuthority(UserDao.Role.STUDENT.name())
                                        .requestMatchers("/consultant","/consultant/**")
                                            .hasAuthority(UserDao.Role.CONSULTANT.name())
                                        .requestMatchers("/auth/changePassword").authenticated()
//                                            .anyRequest().authenticated()
                                        .and()
                                        .httpBasic(Customizer.withDefaults())
                                            .authenticationProvider(authenticationProvider())
                                        .formLogin()
                                            .loginPage("/login")
//                                            .permitAll()  // Specify the custom login page URL
//                                            .loginProcessingUrl("/auth/login").permitAll() // Specify the processing URL for the login form
                                            .successHandler((request, response, authentication) -> {
                                                System.out.println("logging in");
                                                for (GrantedAuthority authority : authentication.getAuthorities()) {
//                                                    System.out.println(authority.getAuthority());
                                                    if (authority.getAuthority().equals(UserDao.Role.STUDENT.name())) {
                                                        response.sendRedirect("/student/loadStudentPage");
                                                        return;
                                                    } else if (authority.getAuthority().equals(UserDao.Role.CONSULTANT.name())) {
//                                                        System.out.println(authority.getAuthority().equals(UserDao.Role.CONSULTANT.name()));
                                                        response.sendRedirect("/consultant/loadConsultantPage");
                                                        return;
                                                    }
                                                }
                                                System.out.println("logging out");
                                                response.sendRedirect("/logout");
                                            })
                                        .and()
//                                            /////////// NEVER USE SESSION IN YOUR LIFE ////////////////////////
//                                        .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                                                .invalidSessionUrl("/logout?expired"))
                                        .logout(l -> l
                                                .deleteCookies("JSESSIONID")
                                                .invalidateHttpSession(true)
                                                .clearAuthentication(true)
//                                                .logoutSuccessUrl("/abc"));
                                                .logoutSuccessUrl("/roleSelect"));
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
//        System.out.println("inside authentication provider");
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
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<UserDao> user = userRepository.findUserDaoByUsername(username);
//                System.out.println(user.get().getUsername());
                return user.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("User Does Not Exist"));
            }
        };
    }

}
