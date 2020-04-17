package com.india.GatedSocietyApp.security;

import com.india.GatedSocietyApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RESTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    UserRepository repository;
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse, AuthenticationException e)
            throws IOException, ServletException {
        httpServletRequest.getHeader("id");
    }
}
