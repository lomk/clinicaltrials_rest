package ua.com.clinicaltrials.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ua.com.clinicaltrials.custom.MyCustomLoginSuccessHandler;

/**
 * Created by Igor on 09-Jun-16.
 */
@Configuration
//@EnableWebSecurity
//@EnableWebMvcSecurity
//@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {
        "ua.com.clinicaltrials",
        "ua.com.clinicaltrials.controllers",
        "ua.com.clinicaltrials.domain",
        "ua.com.clinicaltrials.repositories",
        "ua.com.clinicaltrials.services",
        "ua.com.clinicaltrials.validator"})

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new MyCustomLoginSuccessHandler("/");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/admin/**").access("hasAuthority('ADMIN')")
                .antMatchers(
                        "/**"
//                        "/article/**",
//                        "/registration**",
//                        "/resourses**",
//                        "/bootstrap/**",
//                        "/resourses/**",
//                        "/images**",
//                        "/images/**",
//                        "/css/**",
//                        "/css**"
                )
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
        http.formLogin()
                .failureUrl("/login?error")
//                .defaultSuccessUrl("/")
                .loginPage("/login")
                .usernameParameter("username").passwordParameter("password")
                .successHandler(successHandler())
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .permitAll();
        http.headers().frameOptions().sameOrigin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
    }
}