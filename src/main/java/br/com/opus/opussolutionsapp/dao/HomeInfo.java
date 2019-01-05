package br.com.opus.opussolutionsapp.dao;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

}
