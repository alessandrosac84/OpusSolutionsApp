package br.com.opus.opussolutionsapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


	@GetMapping("/")
	public String homePage(HttpSession session) {
		session.setAttribute("qtdSeguros", 5);
		session.setAttribute("qtdSegurosThisMonth", 10);
		session.setAttribute("qtdRenovacoesThisMonth", 2);
		session.setAttribute("qtdLost", 1);
		return "home";
	}
}
