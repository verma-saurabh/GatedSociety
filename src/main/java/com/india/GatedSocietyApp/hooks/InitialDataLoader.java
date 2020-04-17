package com.india.GatedSocietyApp.hooks;

import com.india.GatedSocietyApp.entity.Privilege;
import com.india.GatedSocietyApp.entity.Role;
import com.india.GatedSocietyApp.entity.User;
import com.india.GatedSocietyApp.repository.PrivilegeRepository;
import com.india.GatedSocietyApp.repository.RoleRepository;
import com.india.GatedSocietyApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class InitialDataLoader implements
        ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final PrivilegeRepository privilegeRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        Privilege readUsers = createPrivilegeIfNotFound("READ_USERS");
        Privilege updateUsers = createPrivilegeIfNotFound("UPDATE_USERS");
        Privilege addUsers = createPrivilegeIfNotFound("ADD_USERS");

        Privilege approveCab = createPrivilegeIfNotFound("APPROVE_CAB");
        Privilege approveGuest = createPrivilegeIfNotFound("APPROVE_GUEST");
        Privilege approveDelivery = createPrivilegeIfNotFound("APPROVE_DELIVERY");
        Privilege approveMaid = createPrivilegeIfNotFound("APPROVE_MAID");

        Privilege readTickets = createPrivilegeIfNotFound("READ_TICKETS");

        Privilege raiseTickets = createPrivilegeIfNotFound("RAISE_TICKETS");


        /*Role adminRole = createRoleIfNotFound("ROLE_ADMIN", Arrays.asList(readUsers, updateUsers, addUsers));
        Role residentRole = createRoleIfNotFound("ROLE_RESIDENT", Arrays.asList(approveCab, approveDelivery, approveGuest, approveMaid));
        Role facilityMgrRole = createRoleIfNotFound("ROLE_FM", Arrays.asList(readTickets));

        //Role SecurityPersonnelRole = createRoleIfNotFound("ROLE_SP", Arrays.asList());

        User user = new User();
        user.setFirstName("Saurabh");
        user.setLastName("verma");
        user.setRoles(Arrays.asList(adminRole));*/
        //userRepository.save(new User());


    }

    private Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    private Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }

}

