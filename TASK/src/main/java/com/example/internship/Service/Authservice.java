package com.example.internship.Service;
//
//import com.example.internship.Jwt.JwtTokenProvider;
import com.example.internship.Jwt.JwtTokenProvider;
import com.example.internship.models.JwtResponse;
import com.example.internship.models.Registerdetails;
import com.example.internship.models.Roles;
import com.example.internship.models.Userdetaildto;
import com.example.internship.repository.RegisterDetailrepo;
import com.example.internship.repository.RegisterRepository;
import com.example.internship.repository.Rolerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class Authservice {
    @Autowired
    RegisterRepository  regrepo;
    @Autowired
    RegisterDetailrepo regdrepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private Rolerepo rolerepo;
    @Autowired
    AuthenticationManager authentimanage;
    @Autowired
    JwtTokenProvider jwttoken;
    public String addNewEmployee(Userdetaildto registerDetails) {
        Registerdetails register = new Registerdetails();
        register.setEmpId(registerDetails.getEmpId());
        register.setEmpName(registerDetails.getEmpName());
        register.setEmail(registerDetails.getEmail());
        register.setPassword(passwordEncoder.encode(registerDetails.getPassword()));
        register.setUsername(registerDetails.getUsername());
        Set<Roles> roles = new HashSet<>();
        for (String roleName : registerDetails.getRoleNames()) {
            Roles role = rolerepo.findByRoleName(roleName)
                    .orElseThrow(() ->new RuntimeException("Role not found: " + roleName));
            roles.add(role);
        }

        register.setRole(roles);
        System.out.println(register);
        regdrepo.save(register);

        return "Employee added Successfully";
    }


//    public List<Registerdetails> getRegisterdetails() {
//
//    }

    //    public String authenticate(RegisterDetails login) {
//        RegisterDetails user =  registerDetailsRepository.findByEmail(login.getEmail());
//        if(user!=null){
//            if(passwordEncoder.matches(login.getPassword(),user.getPassword())){
//                return "Successfully logged in";
//            }
//        }
//        return "login not successfully";
//    }
//    public String authenticate( Userdetaildto login){
//        Registerdetails user=regdrepo.findByEmail(login.getEmail());
//        if(user!=null){
//            //if(user.getPassword().equals(login.getPassword())){
//            if(passwordEncoder.matches(login.getPassword(), user.getPassword())){
//                return "Login Successfully";
//            }
//
//
//        }
//        return "Not";
//    }
    MONISHA U 23CS095
    public JwtResponse authenticate(Userdetaildto login){
        Authentication authentication =authentimanage.authenticate(new UsernamePasswordAuthenticationToken(
                login.getUsername(),login.getPassword()));
        String token = jwttoken.generateToken(authentication);

        String username = login.getUsername();
        List<String> roles = authentication.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());

        String joinedRoles = String.join(",", roles);
        return new JwtResponse(token,username,joinedRoles);
    }
    public Optional<Registerdetails> getUserByUserName(String username){
        return regrepo.findByUsername(username);
    }
    public List<Registerdetails> getAllEmployees(){
        return regrepo.findAll();
    }



}