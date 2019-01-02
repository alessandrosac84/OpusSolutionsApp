package br.com.opus.opussolutionsapp.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import br.com.opus.opussolutionsapp.dao.SeguroDao;


@Controller
public class HomeController {
  
  @Autowired
  private SeguroDao seguroDao;

    @GetMapping("/")
    public String homePage(HttpSession session){
      
      System.out.println(seguroDao.count());
      session.setAttribute("qtdSeguros", seguroDao.obterTotalSeguros());
      session.setAttribute("qtdSegurosThisMonth", 10);
      session.setAttribute("qtdRenovacoesThisMonth", 2);
      session.setAttribute("qtdLost", 1);
      
        return "home";
    }
}
