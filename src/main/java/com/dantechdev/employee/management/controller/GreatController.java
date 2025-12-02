package com.dantechdev.employee.management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/great")
public class GreatController {
    @GetMapping("/with_auth")
    public String greatWithAuth() {
        return "Great -> WITH AUTH";
    }
    @GetMapping("/without_auth")
    public String greatWithoutAuth() {
        return "Great -> WITHOUT AUTH";
    }
}
