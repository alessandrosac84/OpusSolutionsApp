package br.com.opus.opussolutionsapp.dao;

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
        "select COUNT(*) from seguro where status = 'EMITIDO'";
      
       result = template.queryForObject(query, Integer.class);
       
       return (result != null ? result : 0);
      
    } catch (Exception e) {
      return 2;
    }
    
    
//    try {
//        String query =
//            "select COUNT(*) from seguro where status = 'EMITIDO'";
//        return template.queryForObject(query,
//            (rs, rowNum) -> Optional.ofNullable(rs.getInt("id_seguro")));
//      } catch (org.springframework.dao.EmptyResultDataAccessException e) {
//        return Optional.empty();
//      }
  }

}
