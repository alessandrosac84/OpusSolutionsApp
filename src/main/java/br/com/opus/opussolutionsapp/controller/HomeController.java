package br.com.opus.opussolutionsapp.controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import br.com.opus.opussolutionsapp.dao.HomeInfo;
import br.com.opus.opussolutionsapp.dto.Evolution;

@Controller
public class HomeController {

  @Autowired
  private HomeInfo homeInfo;


  @GetMapping("/")
  public String homePage(HttpSession session) {
    File file = new File("src/main/resources/static/json/evolution.json"); 
    file.delete();
    readJsonEvolution();
    session.setAttribute("qtdSeguros", homeInfo.obterTotalSeguros());
    session.setAttribute("qtdSegurosThisMonth", homeInfo.obterTotalSegurosDesteMes());
    session.setAttribute("qtdRenovacoesThisMonth", homeInfo.obterTotalRenovacoesDesteMes());
    session.setAttribute("qtdLost", homeInfo.obterTotalPerdasDesteMes());
    
    return "home";
  }


  @SuppressWarnings("unchecked")
  public void readJsonEvolution() {
    try {

      List<Evolution> result = new ArrayList<>();
      JSONArray listJson = new JSONArray();

      result = homeInfo.obterTotaisAno();

      for (Evolution item : result) {
        JSONObject objJson = new JSONObject();

        objJson.put("mes", item.getMes());
        objJson.put("totalMes", item.getTotalMes());
                
        listJson.add(objJson);
      }

      FileWriter file = new FileWriter("src/main/resources/static/json/evolution.json");
        
        file.write(listJson.toJSONString());
        file.flush();
        
        System.out.println(listJson.toJSONString());

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
