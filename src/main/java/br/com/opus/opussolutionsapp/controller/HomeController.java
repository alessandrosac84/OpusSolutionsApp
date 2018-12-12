package br.com.opus.opussolutionsapp.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(HttpSession session){
      session.setAttribute("qtd", 50);
        return "home";
    }
}
