package com.example.internship.Controller;

import com.example.internship.Service.employeeService;
import com.example.internship.models.Employee;
import com.example.internship.models.Registerdetails;
import com.example.internship.models.Roles;
import com.example.internship.models.Userdetaildto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private employeeService empService;
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/")
    public String router(){
        return "WELCOME TO SPRING BOOT SECURITY";
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/employee")
    public List<Registerdetails> hello(){
        return empService.getMethod();
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/employee/{empId}")
    public Registerdetails getemployeeId(@PathVariable int empId){
        return empService.getemployeeId(empId);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/roles/{role}")
    public List<Registerdetails> getemployeeByRole(@PathVariable String role) {
        return  empService.getemployeeRole(role);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/employee")
    public String postMethod(@RequestBody Registerdetails employee){

        return empService.addemployee(employee);
    }
    @PreAuthorize("hasRole('User')")
    @PutMapping("/employee/{empId}")
    public String putMethod(@PathVariable int empId,@RequestBody Registerdetails reg) {

        return empService.updateemployee(empId,reg);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/employee/{empId}")
    public String deleteMethod(@PathVariable int empId){
        return empService.deleteemployee(empId);
    }



//    public List<Registerdetails> getMethod() {
//    }

//    @DeleteMapping("/employee")
//    public String deleteemployees(){
//        return hws.deleteemployees();
//    }

//    @PutMapping
//    public String putMethod(){
//        return hws.putMethod();
//    }
//    @DeleteMapping
//    public String deleteMethod(){
//        return hws.deleteMethod();
//    }
//@PutMapping("/{empid}")
//public String updateemployee(@RequestBody Employee employee){
//    return hws.updateemployee(employee);
//}
//    @DeleteMapping("/{empid}")
//    public String deleteemployeeId(@PathVariable int empid){
//        return hws.deleteemployeeId(empid);
//    }
}