package com.birschl.auth.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class HelloController {


    /**
     * webApiA resource api for web app
     * @return test content
     */
    @PreAuthorize("hasAuthority('APPROLE_WebApiA.SampleScope')")
    @GetMapping("/B")
    public String webApiASample() {

        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        authorities.stream().forEach(System.out::println);

        System.out.println("Call webApiASample() ");
        return "Request '/webApiA/sample'(WebApi A) returned successfully.";
    }



}
