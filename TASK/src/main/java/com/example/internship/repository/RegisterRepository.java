package com.example.internship.repository;

import com.example.internship.models.Registerdetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Registerdetails,Long> {
    Optional<Registerdetails> findByUsername(String username);
}
