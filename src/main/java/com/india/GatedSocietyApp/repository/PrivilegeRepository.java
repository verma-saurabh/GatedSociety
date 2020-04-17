package com.india.GatedSocietyApp.repository;

import com.india.GatedSocietyApp.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
    @Query(value = "select * from Privilege where name=?1", nativeQuery = true)
    Privilege findByName(String name);
}
