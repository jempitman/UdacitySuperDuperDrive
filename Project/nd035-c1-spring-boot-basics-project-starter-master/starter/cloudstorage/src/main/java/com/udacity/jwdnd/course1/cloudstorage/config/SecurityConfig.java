package com.udacity.jwdnd.course1.cloudstorage.config;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Class to limit unauthorized user access to login and signup pages
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //Instance field: Authentication service for security
    private final AuthenticationService authenticationService;

    //SecurityConfig constructor:
    public SecurityConfig(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    //Configuring Spring Authentication Manager
    // set AuthenticationService class as the authentication provider within WebSecurityConfigurerAdapter
    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(this.authenticationService);
    }

    //Configuring access permissions for authorized and unauthorized users
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //restricting unauthorized user access to signup page
        http.authorizeRequests()
                .antMatchers("/signup", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated();

        //handling calls to /login page
        http.formLogin()
                .loginPage("/login")
                .permitAll();

        //handling calls to /logout page
        http.logout()
                .logoutUrl("/myLogout")
                .logoutSuccessUrl("/login?logout")
                .permitAll();

        //Setting redirect call for authorized users to home page
        http.formLogin()
                .defaultSuccessUrl("/home", true);
    }

}
