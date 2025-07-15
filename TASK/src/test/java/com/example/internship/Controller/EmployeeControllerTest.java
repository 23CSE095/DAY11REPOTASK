package com.example.internship.Controller;

import com.example.internship.Service.employeeService;
import com.example.internship.models.Registerdetails;
import com.example.internship.models.Userdetaildto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EmployeeControllerTest {
     @Mock
     employeeService empservice;

     @InjectMocks
     EmployeeController empcontrol;
     @BeforeEach
     void setUp(){
         MockitoAnnotations.openMocks(this);
     }
     @Test
     void testRouter(){
         String result=empcontrol.router();
         System.out.println(result);
         assertEquals("WELCOME TO SPRING BOOT SECURITY",result);
     }
    @Test
    void testhello()    {
         Registerdetails emp1=new Registerdetails();
         Registerdetails emp2=new Registerdetails();
         when(empservice.getMethod()).thenReturn(Arrays.asList(emp1,emp2));
         List<Registerdetails> result=empcontrol.hello();
         System.out.println(result);
         assertEquals(2,result.size());
     }

     @Test
    void testGetemployeeid(){
         int empId=1;
         Registerdetails emp1=new Registerdetails();
         Registerdetails emp2=new Registerdetails();
         emp1.setEmpId(empId);
         when(empservice.getemployeeId(empId)).thenReturn(emp1);
         Registerdetails result=empcontrol.getemployeeId(empId);
         System.out.println(result);
         assertEquals(empId,result.getEmpId());
     }
     @Test
    void testPostMethod(){
        Registerdetails emp =  new Registerdetails();
        emp.setUsername("moni");
        emp.setEmail("moni@gmail.com");
        emp.setPassword("moni123");
        when(empservice.addemployee(emp)).thenReturn("employee added successfully");
        String result = empcontrol.postMethod(emp);
        assertEquals("employee added successfully",result);
}


}
