package br.com.opus.opussolutionsapp.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import br.com.opus.opussolutionsapp.dao.HomeInfo;

@Controller
public class HomeController {
  
  @Autowired
  private HomeInfo homeInfo;


	@GetMapping("/")
	public String homePage(HttpSession session) {
		session.setAttribute("qtdSeguros", homeInfo.obterTotalSeguros());
		session.setAttribute("qtdSegurosThisMonth", 10);
		session.setAttribute("qtdRenovacoesThisMonth", 2);
		session.setAttribute("qtdLost", 1);
		return "home";
	}
}
