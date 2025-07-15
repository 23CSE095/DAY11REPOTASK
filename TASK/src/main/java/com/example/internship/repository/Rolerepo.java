package com.example.internship.repository;

import com.example.internship.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface Rolerepo extends JpaRepository<Roles,Integer> {





    Optional<Roles> findByRoleName(String roleNames);
}
