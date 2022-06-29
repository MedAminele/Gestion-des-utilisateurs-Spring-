package org.dsi.security;

import org.dsi.service.AdminDetailsServiceImpl;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


 
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	

 
    @Bean
    public UserDetailsService userDetailsService() {
        return new AdminDetailsServiceImpl();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http.authorizeRequests()
    	     .antMatchers("/", "/register").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login")
            .permitAll()
           .and()  
            .rememberMe()  
            .key("rem-me-key")  
            .rememberMeParameter("remember") // it is name of checkbox at login page  
             
            .tokenValiditySeconds(100) // remember for number of seconds  
            .and()
            .logout().permitAll();
    	  http.exceptionHandling().accessDeniedPage("/403");
        
    }
    

}
