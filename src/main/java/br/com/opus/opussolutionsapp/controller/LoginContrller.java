package br.com.opus.opussolutionsapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginContrller {

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
}
