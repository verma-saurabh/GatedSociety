package com.india.GatedSocietyApp.service;

import com.india.GatedSocietyApp.entity.Role;
import com.india.GatedSocietyApp.entity.User;
import com.india.GatedSocietyApp.repository.RoleRepository;
import com.india.GatedSocietyApp.repository.UserRepository;
import com.india.GatedSocietyApp.requests.SignUpRequest;
import com.india.GatedSocietyApp.utils.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService  {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;


    /**
     * Sign in a user into the application, with JWT-enabled authentication
     *
     * @param email  username
     * @param password  password
     * @return Optional of the Java Web Token, empty otherwise
     */
    public Optional<String> login(String email, String password) {
        LOGGER.info("New user attempting to sign in");
        Optional<String> token = Optional.empty();
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
                token = Optional.of(jwtProvider.createToken(email, user.get().getRoles()));
            } catch (AuthenticationException e){
                LOGGER.info("Log in failed for user {}", email);
            }
        }
        return token;
    }
    public Optional<User> signup(SignUpRequest request) {

        if (!userRepository.findByEmail(request.getEmail()).isPresent()) {
            List<Role> roleList = new ArrayList<>();
            for (Role role : request.getRoleList()) {
                Role r = roleRepository.findByName(role.getName());
                if (r == null) {
                    roleList.add(roleRepository.save(role));
                } else {
                    roleList.add(r);
                }
            }
            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRoles(roleList);
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLName());
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }
}
