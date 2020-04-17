package com.india.GatedSocietyApp.repository;

import com.india.GatedSocietyApp.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();

    Optional<User> findByEmail(String email);

}
