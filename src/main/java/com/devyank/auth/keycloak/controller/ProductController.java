package com.devyank.auth.keycloak.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    //Authorization combined with the roles showed in Keycloak.
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public String list(){
        return "Listando produtos";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String create(){
        return "Cadastrando produtos";
    }
}


