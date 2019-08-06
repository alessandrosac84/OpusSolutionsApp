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

import br.com.opus.opussolutionsapp.dto.Indicadores;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import br.com.opus.opussolutionsapp.dao.HomeInfo;
import br.com.opus.opussolutionsapp.dto.Evolution;
import br.com.opus.opussolutionsapp.dto.SeguroPorSeguradora;
import br.com.opus.opussolutionsapp.entity.Seguro;

@Controller
public class HomeController {

  @Autowired
  private HomeInfo homeInfo;


  @GetMapping("/")
  public String homePage(HttpSession session) {
    File fileEvolution = new File("src/main/resources/static/json/evolution.json"); 
    fileEvolution.delete();
    readJsonEvolution();
    
    File fileSeguroPorSeguradora = new File("src/main/resources/static/json/seguroPorSeguradora.json"); 
    fileSeguroPorSeguradora.delete();
    readJsonSegurosPorSeguradora();

    File fileIndicadores = new File("src/main/resources/static/json/indicadores.json");
    fileIndicadores.delete();
    readJsonindicadores();
    
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
  
  @SuppressWarnings("unchecked")
  public void readJsonSegurosPorSeguradora() {
    try {

      List<SeguroPorSeguradora> result = new ArrayList<>();
      JSONArray listJson = new JSONArray();

      result =  homeInfo.obterTotaisPorSeguradora();

      for (SeguroPorSeguradora item : result) {
        JSONObject objJson = new JSONObject();

        objJson.put("name", item.getSeguradora());
        objJson.put("value", item.getTotal());
        objJson.put("background", item.getCollor());
                
        listJson.add(objJson);
      }

      FileWriter file = new FileWriter("src/main/resources/static/json/seguroPorSeguradora.json");
        
        file.write(listJson.toJSONString());
        file.flush();
        
        System.out.println(listJson.toJSONString());

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  public void readJsonindicadores() {
    try {

      Integer resultLigacoes = 0;
      Integer resultConversoes = 0;
      Integer resultOrcado = 0;
      Integer resultFechado = 0;
      JSONArray listJson = new JSONArray();

      resultLigacoes =  homeInfo.obterTotalLigacoesDesteMes();
      resultConversoes = homeInfo.obterTotalConversoesDesteMes();
      resultOrcado = homeInfo.obterTotalOrcadosDesteMes();
      resultFechado = homeInfo.obterTotalFechadosDesteMes();

      if(resultLigacoes != null){
        JSONObject objJson = new JSONObject();
        objJson.put("valor", resultLigacoes);
        objJson.put("name", "Ligações");

        listJson.add(objJson);
      }

      if(resultConversoes != null){
        JSONObject objJson = new JSONObject();
        objJson.put("valor", resultConversoes);
        objJson.put("name", "Taxa de Conversão");

        listJson.add(objJson);
      }

      if(resultOrcado != null){
        JSONObject objJson = new JSONObject();
        objJson.put("valor", resultOrcado);
        objJson.put("name", "Orçamentos");

        listJson.add(objJson);
      }

      if(resultFechado != null){
        JSONObject objJson = new JSONObject();
        objJson.put("valor", resultFechado);
        objJson.put("name", "Vendas");

        listJson.add(objJson);
      }

      FileWriter file = new FileWriter("src/main/resources/static/json/indicadores.json");

      file.write(listJson.toJSONString());
      file.flush();

      System.out.println(listJson.toJSONString());

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
