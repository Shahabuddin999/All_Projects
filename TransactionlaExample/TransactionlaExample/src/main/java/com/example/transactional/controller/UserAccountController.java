package com.example.transactional.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.transactional.service.UserAccountService;

@RestController
@RequestMapping("/api")
public class UserAccountController {
  private final UserAccountService service;

  public UserAccountController(UserAccountService service) {
    this.service = service;
  }

  @PostMapping("/create")
  public String create(@RequestParam String name, @RequestParam String account, @RequestParam(defaultValue = "false") boolean fail) {
    try {
      service.createUserAndAccount(name, account, fail);
      return "Success";
    } catch (Exception e) {
      return "Failed: " + e.getMessage();
    }
  }
}
