package com.example.internship.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_details")
public class Registerdetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;
    @Column(nullable = false)
    private String empName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name="username",nullable = false,unique = true)
    private String username;
    @ManyToMany(fetch =FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="user_roles",joinColumns =@JoinColumn(name ="user_id", referencedColumnName ="empId" ),
    inverseJoinColumns =@JoinColumn(name="role_id", referencedColumnName = "roleId"))
    private Set<Roles> role;



}
