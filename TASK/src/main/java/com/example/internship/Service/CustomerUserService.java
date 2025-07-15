
package com.example.internship.Service;

import com.example.internship.models.Registerdetails;
import com.example.internship.repository.RegisterDetailrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
@Service
public class CustomerUserService implements UserDetailsService {
    @Autowired
    RegisterDetailrepo regdrepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*
    //3things
    1.Loading data from the database
    2. Setting up the authorities
    3.returning up proper user details
     */
        //step 1
        Registerdetails user;
        user = regdrepo.findByUsername(username).orElseThrow
                (()->new RuntimeException("User Not Found"));

        //step 2

        Set<GrantedAuthority> authorities = user.getRole().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getRoleName()))
                .collect(Collectors.toSet());
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}