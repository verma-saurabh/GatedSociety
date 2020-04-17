package com.india.GatedSocietyApp.security;

import com.india.GatedSocietyApp.service.ExploreCaliUserDetailsService;
import com.india.GatedSocietyApp.service.UserService;
import com.india.GatedSocietyApp.utils.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private ExploreCaliUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(security);

        //security.httpBasic().disable();

        /*security.cors().and().csrf().disable().authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().disable() // <-- this will disable the login route
                .addFilter(JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/


        http.authorizeRequests().antMatchers("/", "/auth/login").permitAll()
                .anyRequest().authenticated();

        http.csrf().disable();
      /*  http.cors().and().csrf().disable().authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().disable() // <-- this will disable the login route
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(new JwtTokenFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class);

    }


    //@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}