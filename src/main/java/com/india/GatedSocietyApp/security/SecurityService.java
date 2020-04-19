package com.india.GatedSocietyApp.security;

import com.india.GatedSocietyApp.entity.Privilege;
import com.india.GatedSocietyApp.entity.Role;
import com.india.GatedSocietyApp.repository.RoleRepository;
import com.india.GatedSocietyApp.service.ExploreCaliUserDetailsService;
import com.india.GatedSocietyApp.utils.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityService.class);
    private final RoleRepository roleRepository;
    private Logger logger = LoggerFactory.getLogger(SecurityService.class);

    @Autowired
    public ExploreCaliUserDetailsService userDetailsService;

    public boolean hasPrivilege(String privilege) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        logger.info("RequestAttributes =>" + attributes.toString());
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Authentication =>" + auth);
        final ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        final HttpSession session = attr.getRequest().getSession(false);
        if (auth != null && auth.isAuthenticated() && session != null) {
            final String currentRole = (String) session.getAttribute("role");
            if (currentRole != null) {
                final Role role = roleRepository.findByName(currentRole);
                return role.getPrivileges()
                        .stream()
                        .map(Privilege::getName)
                        .anyMatch(p -> p.equals(privilege));
            }
        }
        return false;
    }
}