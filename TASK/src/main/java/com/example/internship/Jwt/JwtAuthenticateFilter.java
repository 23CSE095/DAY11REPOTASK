package com.example.internship.Jwt;
//
import com.example.internship.Service.CustomerUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenticateFilter extends OncePerRequestFilter {
    @Autowired
    CustomerUserService customuserservice;
    @Autowired
    JwtTokenProvider jwttoken;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");
        String token = null;
        String username=null;
        if(authHeader != null && authHeader.startsWith("Bearer")){
            token=authHeader.substring(7);
            username =jwttoken.getUserNameFromToken(token);

        }
        if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails  userDetails=customuserservice.loadUserByUsername(username);
            if(jwttoken.validateToken(token)){
                UsernamePasswordAuthenticationToken authToken =new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);

        }

    }


