package com.india.GatedSocietyApp.repository;

import com.india.GatedSocietyApp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query(value = "select * from Role where name =?1", nativeQuery = true)
    Role findByName(String name);

    @Override
    void delete(Role role);

}