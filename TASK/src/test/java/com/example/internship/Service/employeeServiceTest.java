package com.example.internship.Service;

import com.example.internship.models.Registerdetails;
import com.example.internship.models.Roles;
import com.example.internship.repository.RegisterDetailrepo;
import com.example.internship.repository.Rolerepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class employeeServiceTest {

    @Mock
    RegisterDetailrepo regdrepo;
    @Mock
    Rolerepo rolerepo;
    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    employeeService  empservice;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

    }
    @Test
    void testGetMethod(){
        Registerdetails emp1=new Registerdetails();
        Registerdetails emp2=new Registerdetails();
        when(regdrepo.findAll()).thenReturn(Arrays.asList(emp1,emp2));
        List<Registerdetails> result=empservice.getMethod();
        System.out.println(result);
        assertEquals(2,result.size());
    }
    @Test
    void testGetEmployeeById(){
        int empId=1;
        Registerdetails emp = new Registerdetails();
        emp.setEmpId(empId);
        when(regdrepo.findById(empId)).thenReturn(Optional.of(emp));
        Registerdetails result = empservice.getemployeeId(empId);
        assertEquals(empId, result.getEmpId());
    }//MONISHA.U23CS095
    @Test
    void testAddEmployee() {
        Registerdetails emp = new Registerdetails();
        emp.setEmpId(1);
        emp.setEmpName("moni");
        emp.setEmail("moni@example.com");
        emp.setUsername("moni123");
        emp.setPassword("password");
        emp.setRole(Set.of());
        Roles adminRole = new Roles();
        adminRole.setRoleId(1);
        adminRole.setRoleName("ADMIN");
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        String result = empservice.addemployee(emp);
        assertEquals("employee added successfully",result);
    }





}