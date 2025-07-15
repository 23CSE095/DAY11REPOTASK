package com.example.internship.repository;

import com.example.internship.models.Registerdetails;
import com.example.internship.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RegisterDetailrepo extends JpaRepository<Registerdetails, Integer> {

    Registerdetails findByEmail(String email);


    Optional<Registerdetails> findByUsername(String username);



}
