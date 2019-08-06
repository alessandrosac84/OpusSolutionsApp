package br.com.opus.opussolutionsapp.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import br.com.opus.opussolutionsapp.dto.Evolution;
import br.com.opus.opussolutionsapp.dto.SeguroPorSeguradora;

@Repository
public class HomeInfo {
  
  @Autowired
  private JdbcTemplate template;

   
  public Integer obterTotalSeguros() {
    
    Integer result;
    
    try {
      String query =
        "select COUNT(*) from seguro where status = 'EMITIDO' OR status = 'PROPOSTA'";
      
       result = template.queryForObject(query, Integer.class);
       
       return (result != null ? result : 0);
      
    } catch (Exception e) {
      return 9999;
    }
  }
  
  public Integer obterTotalSegurosDesteMes() {
	    
	    Integer result;
	    String mesAno = "";
	    
		Calendar cal = Calendar.getInstance();
		
		
		if(cal.get(Calendar.MONTH) < 10) {
			mesAno = String.valueOf(String.valueOf("0").concat(String.valueOf(cal.get(Calendar.MONTH) +1)).concat("/").concat(String.valueOf(cal.get(Calendar.YEAR))));	
		}else {
			mesAno = String.valueOf(cal.get(Calendar.MONTH) +1).concat("/").concat(String.valueOf(cal.get(Calendar.YEAR)));
		}
		
		Object[] parameters = new Object[] {new String(mesAno)};
	    
	    try {
	      String query =
	        "SELECT count(*) FROM seguro s WHERE right(s.data_proposta,7)= ?" ;
	      
	       result = template.queryForObject(query, parameters, Integer.class);
	       
	       return (result != null ? result : 0);
	      
	    } catch (Exception e) {
	      return 9999;
	    }
	  }
  
  public Integer obterTotalRenovacoesDesteMes() {

	    Integer result;
	    String mesAno = "";

		Calendar cal = Calendar.getInstance();


		if(cal.get(Calendar.MONTH) < 10) {
			mesAno = String.valueOf(String.valueOf("0").concat(String.valueOf(cal.get(Calendar.MONTH) +1)).concat("/").concat(String.valueOf(cal.get(Calendar.YEAR))));
		}else {
			mesAno = String.valueOf(cal.get(Calendar.MONTH) +1).concat("/").concat(String.valueOf(cal.get(Calendar.YEAR)));
		}

		Object[] parameters = new Object[] {new String(mesAno)};

	    try {
	      String query =
	        "SELECT count(*) FROM seguro s WHERE right(s.data_fim_vigencia,7)= ? AND status = 'EMITIDO'" ;

	       result = template.queryForObject(query, parameters, Integer.class);

	       return (result != null ? result : 0);

	    } catch (Exception e) {
	      return 9999;
	    }
	  }
  
  public Integer obterTotalPerdasDesteMes() {
	    
	    Integer result;
	    String mesAno = "";
	    
		Calendar cal = Calendar.getInstance();
		
		
		if(cal.get(Calendar.MONTH) < 10) {
			mesAno = String.valueOf(String.valueOf("0").concat(String.valueOf(cal.get(Calendar.MONTH) +1)).concat("/").concat(String.valueOf(cal.get(Calendar.YEAR))));	
		}else {
			mesAno = String.valueOf(cal.get(Calendar.MONTH) +1).concat("/").concat(String.valueOf(cal.get(Calendar.YEAR)));
		}
		
		Object[] parameters = new Object[] {new String(mesAno)};
	    
	    try {
	      String query =
	        "SELECT count(*) FROM seguro s WHERE right(s.data_fim_vigencia,7)= ? AND status = 'CANCELADO'" ;
	      
	       result = template.queryForObject(query, parameters, Integer.class);
	       
	       return (result != null ? result : 0);
	      
	    } catch (Exception e) {
	      return 9999;
	    }
	  }

  
  public List<Evolution> obterTotaisAno(){
    
    List<Evolution> returnList = new ArrayList<>();
    Integer countMes = 0;
    String mesAno = "";
    
        
    for (int i = 1; i < 13; i++) {
      
      Calendar cal = Calendar.getInstance();
      
      if(i < 10) {
          mesAno = String.valueOf(String.valueOf("0").concat(String.valueOf(i).concat("/").concat(String.valueOf(cal.get(Calendar.YEAR))))); 
      }else {
          mesAno = String.valueOf(i).concat("/").concat(String.valueOf(cal.get(Calendar.YEAR)));
      }
      
      Object[] parameters = new Object[] {new String(mesAno)};

      String query =
          "SELECT count(*) FROM seguro s WHERE right(s.data_proposta,7)= ? AND status = 'EMITIDO'" ;
      
      countMes = template.queryForObject(query, parameters, Integer.class);
      
      Evolution itemMes = new Evolution();
      switch (i) {
        case 1:
          itemMes.setTotalMes(countMes.toString());
          itemMes.setMes("Janeiro");
          
          break;
        case 2:
          itemMes.setTotalMes(countMes.toString());
          itemMes.setMes("Fevereiro");
          
          break;
        case 3:
          itemMes.setTotalMes(countMes.toString());
          itemMes.setMes("Março");
          
          break; 
        case 4:
          itemMes.setTotalMes(countMes.toString());
          itemMes.setMes("Abril");
          
          break; 
        case 5:
          itemMes.setTotalMes(countMes.toString());
          itemMes.setMes("Maio");
          
          break; 
        case 6:
          itemMes.setTotalMes(countMes.toString());
          itemMes.setMes("Junho");
          
          break; 
        case 7:
          itemMes.setTotalMes(countMes.toString());
          itemMes.setMes("Junho");
          
          break; 
        case 8:
          itemMes.setTotalMes(countMes.toString());
          itemMes.setMes("Agosto");
          
          break; 
        case 9:
          itemMes.setTotalMes(countMes.toString());
          itemMes.setMes("Setembro");
          
          break; 
        case 10:
          itemMes.setTotalMes(countMes.toString());
          itemMes.setMes("Outubro");
          
          break; 
        case 11:
          itemMes.setTotalMes(countMes.toString());
          itemMes.setMes("Novembro");
          
          break; 
        case 12:
          itemMes.setTotalMes(countMes.toString());
          itemMes.setMes("Dezembro");
          
          break; 
        default:
          break;
      }
      
      returnList.add(itemMes);
    }
    
    return returnList;
  }
  
  
public List<SeguroPorSeguradora> obterTotaisPorSeguradora(){
    
    List<SeguroPorSeguradora> returnList = new ArrayList<>();
    SeguroPorSeguradora porto = new SeguroPorSeguradora();
    porto.setSeguradora("Porto Seguro");
    porto.setTotal(0);
    porto.setCollor("rgba(0, 119, 204, 0.3)");
    
    SeguroPorSeguradora azul = new SeguroPorSeguradora();
    azul.setSeguradora("Azul Seguros");
    azul.setTotal(0);
    azul.setCollor("rgba(0, 8, 255, 0.3)");
    
    SeguroPorSeguradora itau = new SeguroPorSeguradora();
    itau.setSeguradora("Itáu");
    itau.setTotal(0);
    itau.setCollor("rgba(255, 131, 5, 0.3)");
    
    SeguroPorSeguradora tokio = new SeguroPorSeguradora();
    tokio.setSeguradora("Tokio Marine");
    tokio.setTotal(0);
    tokio.setCollor("rgba(2, 255, 5, 0.3)");
    
    SeguroPorSeguradora allianz = new SeguroPorSeguradora();
    allianz.setSeguradora("Allianz");
    allianz.setTotal(0);
    allianz.setCollor("rgba(74, 1, 6, 0.3)");
    
    SeguroPorSeguradora liberty = new SeguroPorSeguradora();
    liberty.setSeguradora("Liberty Seguros");
    liberty.setTotal(0);
    liberty.setCollor("rgba(155, 159, 6, 0.3)");
    
    SeguroPorSeguradora suhai = new SeguroPorSeguradora();
    suhai.setSeguradora("Suhai");
    suhai.setTotal(0);
    suhai.setCollor("rgba(1, 3, 6, 0.3)");
    
    SeguroPorSeguradora hdi = new SeguroPorSeguradora();
    hdi.setSeguradora("HDI");
    hdi.setTotal(0);
    hdi.setCollor("rgba(155, 254, 6, 0.3)");
    
    SeguroPorSeguradora bradesco = new SeguroPorSeguradora();
    bradesco.setSeguradora("Bradesco");
    bradesco.setTotal(0);
    bradesco.setCollor("rgba(252, 8, 0, 0.3)");
    
    SeguroPorSeguradora mapfre = new SeguroPorSeguradora();
    mapfre.setSeguradora("Mapfre");
    mapfre.setTotal(0);
    mapfre.setCollor("rgba(136, 3, 121, 0.3)");
    
    String query =
        "SELECT * FROM seguro s WHERE status = 'EMITIDO'" ;
    
    List<Map<String,Object>> list = template.queryForList(query);
        
    for (int i = 1; i < list.size(); i++) {
      
      switch (list.get(i).get("seguradora").toString()) {
        case "PORTO SEGURO":
          porto.setTotal(porto.getTotal() + 1);
          break;
        case "AZUL SEGUROS":
          azul.setTotal(azul.getTotal() + 1);
          break;
        case "ITAU":
          itau.setTotal(itau.getTotal() + 1);
          break; 
        case "TOKIO MARINE":
          tokio.setTotal(tokio.getTotal() + 1);
          break; 
        case "ALLIANZ":
          allianz.setTotal(allianz.getTotal() + 1);
          break; 
        case "LIBERTY SEGUROS":
          liberty.setTotal(liberty.getTotal() + 1);
          break; 
        case "SUHAI":
          suhai.setTotal(suhai.getTotal() + 1);
          break; 
        case "HDI":
          hdi.setTotal(hdi.getTotal() + 1);
          break; 
        case "BRADESCO":
          bradesco.setTotal(bradesco.getTotal() + 1);
          break; 
        case "MAPFRE":
          mapfre.setTotal(mapfre.getTotal() + 1);
          break; 
        default:
          break;
      }
    }
    
    returnList.add(porto);
    returnList.add(azul);
    returnList.add(itau);
    returnList.add(tokio);
    returnList.add(allianz);
    returnList.add(liberty);
    returnList.add(suhai);
    returnList.add(hdi);
    returnList.add(bradesco);
    returnList.add(mapfre);
    
    
    return returnList;
  }


    public Integer obterTotalLigacoesDesteMes() {

        Integer result;
        String mesAno = "";

        Calendar cal = Calendar.getInstance();


        if(cal.get(Calendar.MONTH) < 10) {
            mesAno = String.valueOf(String.valueOf("0").concat(String.valueOf(cal.get(Calendar.MONTH) +1)).concat("/").concat(String.valueOf(cal.get(Calendar.YEAR))));
        }else {
            mesAno = String.valueOf(cal.get(Calendar.MONTH) +1).concat("/").concat(String.valueOf(cal.get(Calendar.YEAR)));
        }

        Object[] parameters = new Object[] {new String(mesAno)};

        try {
            String query =
                    "SELECT count(*) FROM ligacao s WHERE right(s.data_contato,7)= ? " ;

            result = template.queryForObject(query, parameters, Integer.class);

            return (result != null ? result : 0);

        } catch (Exception e) {
            return 9999;
        }
    }

    public Integer obterTotalConversoesDesteMes() {

        Integer result;
        String mesAno = "";

        Calendar cal = Calendar.getInstance();


        if(cal.get(Calendar.MONTH) < 10) {
            mesAno = String.valueOf(String.valueOf("0").concat(String.valueOf(cal.get(Calendar.MONTH) +1)).concat("/").concat(String.valueOf(cal.get(Calendar.YEAR))));
        }else {
            mesAno = String.valueOf(cal.get(Calendar.MONTH) +1).concat("/").concat(String.valueOf(cal.get(Calendar.YEAR)));
        }

        Object[] parameters = new Object[] {new String(mesAno)};

        try {
            String query =
                    "SELECT count(*) FROM ligacao s WHERE right(s.data_contato,7)= ? AND status  = 'CONVERTIDO'" ;

            result = template.queryForObject(query, parameters, Integer.class);

            return (result != null ? result : 0);

        } catch (Exception e) {
            return 9999;
        }
    }

    public Integer obterTotalOrcadosDesteMes() {

        Integer result;
        String mesAno = "";

        Calendar cal = Calendar.getInstance();


        if(cal.get(Calendar.MONTH) < 10) {
            mesAno = String.valueOf(String.valueOf("0").concat(String.valueOf(cal.get(Calendar.MONTH) +1)).concat("/").concat(String.valueOf(cal.get(Calendar.YEAR))));
        }else {
            mesAno = String.valueOf(cal.get(Calendar.MONTH) +1).concat("/").concat(String.valueOf(cal.get(Calendar.YEAR)));
        }

        Object[] parameters = new Object[] {new String(mesAno)};

        try {
            String query =
                    "SELECT count(*) FROM ligacao s WHERE right(s.data_contato,7)= ? AND orcado  = 'SIM'" ;

            result = template.queryForObject(query, parameters, Integer.class);

            return (result != null ? result : 0);

        } catch (Exception e) {
            return 9999;
        }
    }

    public Integer obterTotalFechadosDesteMes() {

        Integer result;
        String mesAno = "";

        Calendar cal = Calendar.getInstance();


        if(cal.get(Calendar.MONTH) < 10) {
            mesAno = String.valueOf(String.valueOf("0").concat(String.valueOf(cal.get(Calendar.MONTH) +1)).concat("/").concat(String.valueOf(cal.get(Calendar.YEAR))));
        }else {
            mesAno = String.valueOf(cal.get(Calendar.MONTH) +1).concat("/").concat(String.valueOf(cal.get(Calendar.YEAR)));
        }

        Object[] parameters = new Object[] {new String(mesAno)};

        try {
            String query =
                    "SELECT count(*) FROM ligacao s WHERE right(s.data_contato,7)= ? AND fechado  = 'SIM'" ;

            result = template.queryForObject(query, parameters, Integer.class);

            return (result != null ? result : 0);

        } catch (Exception e) {
            return 9999;
        }
    }
}
