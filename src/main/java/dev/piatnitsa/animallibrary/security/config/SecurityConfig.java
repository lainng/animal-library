package dev.piatnitsa.animallibrary.security.config;

import dev.piatnitsa.animallibrary.security.ForbiddenHandler;
import dev.piatnitsa.animallibrary.security.UnauthorizedEntryPoint;
import dev.piatnitsa.animallibrary.security.jwt.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class is configuration for Spring Security Framework. It configures access to the application and configures web filters.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenFilter tokenFilter;
    private final ForbiddenHandler forbiddenHandler;
    private final UnauthorizedEntryPoint unauthorizedEntryPoint;
    private final AuthenticationFailureHandler failureHandler;
    private final AuthenticationSuccessHandler successHandler;

    @Autowired
    public SecurityConfig(TokenFilter tokenFilter,
                          ForbiddenHandler forbiddenHandler,
                          UnauthorizedEntryPoint unauthorizedEntryPoint,
                          AuthenticationFailureHandler failureHandler, AuthenticationSuccessHandler successHandler) {
        this.tokenFilter = tokenFilter;
        this.forbiddenHandler = forbiddenHandler;
        this.unauthorizedEntryPoint = unauthorizedEntryPoint;
        this.failureHandler = failureHandler;
        this.successHandler = successHandler;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                    .antMatchers("/register", "/login", "/name-availability").permitAll()
                    .antMatchers("/animal/**").authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .failureHandler(failureHandler)
                    .successHandler(successHandler)
                .and()
                .exceptionHandling()
                    .accessDeniedHandler(forbiddenHandler)
                    .authenticationEntryPoint(unauthorizedEntryPoint)
                .and()
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}