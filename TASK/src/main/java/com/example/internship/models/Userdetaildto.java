package com.example.internship.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Userdetaildto {
     private int empId;
     private String empName;
     private String email;
     private String Password;
     private String username;
     private Set<String> roleNames;


}
