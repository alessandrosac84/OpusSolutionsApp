package br.com.opus.opussolutionsapp.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import br.com.opus.opussolutionsapp.dto.Evolution;

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
}
