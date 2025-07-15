package com.example.internship.Service;

import com.example.internship.models.Registerdetails;
//import com.example.internship.repository.Employeerepo;
import com.example.internship.models.Roles;
import com.example.internship.repository.RegisterDetailrepo;
import com.example.internship.repository.Rolerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class employeeService {
//    @Autowired
//    Employeerepo emprepo;// bean or object is a emprepo
      @Autowired
    RegisterDetailrepo regdrepo;

      @Autowired
    PasswordEncoder passwordEncoder;
      @Autowired
    Rolerepo rolerepo;

    public List<Registerdetails> getMethod() {
        return  regdrepo.findAll();
    }
    public  Registerdetails getemployeeId(int empId) {
        return regdrepo.findById(empId).orElse(new Registerdetails());
    }

//    public List<Registerdetails> getemployeejob(String job) {
//
//        return regdrepo.findByJob(job);
//
//    }
    public String addemployee(Registerdetails employee) {
            regdrepo.save(employee);
        return "employee added successfully";
    }

//    public String updateemployee(int empId, Registerdetails reg) {
//
//        Registerdetails user=regdrepo.findById(empId)
//                .orElseThrow(()->new RuntimeException("employee not found"));
//
//        regdrepo.save(user);
//        return " Employee updated  successfully";
//    }

    public String updateemployee(int empId, Registerdetails updatedData) {
        Registerdetails existing = regdrepo.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + empId));

        existing.setEmpName(updatedData.getEmpName());
        existing.setUsername(updatedData.getUsername());
        existing.setEmail(updatedData.getEmail());
        existing.setPassword(passwordEncoder.encode(updatedData.getPassword()));
        existing.setRole(updatedData.getRole());

        regdrepo.save(existing);
        return "Employee updated successfully";
    }

    public String deleteemployee(int empId) {
        regdrepo.deleteById(empId);
        return "employee deleted successfully";
    }

    public List<Registerdetails> getemployeeRole(String roleName) {
        List<Registerdetails> employ=new ArrayList<>();
        for(Registerdetails registerDetails :regdrepo.findAll()){
            for(Roles role: registerDetails.getRole()){
                if(role.getRoleName().equals(roleName.toUpperCase())){
                    employ.add(registerDetails);
                }
            }
        }
        return employ;
    }


//    public String deleteemployees() {
//        emprepo.deleteAll();
//        return "deleted all employees detail";
//    }




//    public List<Employee> getMethod() {
//        return emp;
//    }
//
//    public Employee getemployeeId(int empid) {
//        int index = 0;
//        boolean flag = false;
//        for (int i = 0; i < emp.size(); i++) {
//            if (empid == emp.get(i).getEmpid()) {
//                System.out.println("Emp_Id" + emp.get(i).getEmpid() + emp.get(i));
//                index = i;
//                flag = true;
//                break;
//            }
//        }
//        if (flag) {
//            return emp.get(index);
//        } else {
//            return new Employee();
//        }
//    }
//
//    public String postMethod(Employee employee) {
//        emp.add(employee);
//        String s = "created successfully";
//        return s;
//    }
//
////    public String putMethod() {
////        return "This is Put Method";
////    }
//
//    //
//    public String deleteemployeeId(int empid) {
//        int index = 0;
//        boolean flag = false;
//        for (int i = 0; i < emp.size(); i++) {
//            if (empid == emp.get(i).getEmpid()) {
//                System.out.println("Emp_Id" + emp.get(i).getEmpid() + emp.get(i));
//                index = i;
//                flag = true;
//                break;
//            }
//        }
//        if (flag) {
//            emp.remove(index);
//            return "employeeid deleted";
//        } else {
//            return "not found id";
//        }
//    }
//
//    public String updateemployee(Employee employee) {
//        int index = 0;
//        boolean flag = false;
//        for (int i = 0; i < emp.size(); i++) {
//            if (employee.getEmpid() == emp.get(i).getEmpid()) {
//                System.out.println("Emp_Id" + emp.get(i).getEmpid() + emp.get(i));
//                index = i;
//                flag = true;
//                break;
//            }
//        }
//        if (flag) {
//            emp.set(index,employee);
//            return "successfully updated";
//        } else {
//            return "not found id";
//        }
//    }
    }







