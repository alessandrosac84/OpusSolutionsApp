package br.com.opus.opussolutionsapp.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import br.com.opus.opussolutionsapp.dao.SeguroDao;

@Repository
abstract class SeguroDaoImpl implements SeguroDao{
  
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
