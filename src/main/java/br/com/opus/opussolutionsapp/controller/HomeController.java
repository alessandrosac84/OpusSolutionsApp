package br.com.opus.opussolutionsapp.controller;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
		session.setAttribute("qtdSegurosThisMonth", homeInfo.obterTotalSegurosDesteMes());
		session.setAttribute("qtdRenovacoesThisMonth", homeInfo.obterTotalRenovacoesDesteMes());
		session.setAttribute("qtdLost", homeInfo.obterTotalPerdasDesteMes());
		readJsonEvolution();
		
		return "home";
	}
	
	
	public static void readJsonEvolution() {
		try {
			//le json
			FileReader reader = new FileReader("src/main/resources/static/json/evolution.json");
		    JSONParser jsonParser = new JSONParser();
		    Object json = jsonParser.parse(reader);
			
			//busca infos
			System.out.println(json);
			//escreve json
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	public static void writeJsonSimpleDemo(String filename) throws Exception {  
	    JSONObject sampleObject = new JSONObject();
	    sampleObject.put("name", "Stackabuser");
	    sampleObject.put("age", 35);

	    JSONArray messages = new JSONArray();
	    messages.add("Hey!");
	    messages.add("What's up?!");

	    sampleObject.put("messages", messages);
	    Files.write(Paths.get(filename), sampleObject.toJSONString().getBytes());
	}
	
	
	
}
