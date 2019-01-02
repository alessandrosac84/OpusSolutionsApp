package br.com.opus.opussolutionsapp.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import br.com.opus.opussolutionsapp.dao.SeguroDao;
import br.com.opus.opussolutionsapp.entity.Seguro;

public class SeguroDaoImpl implements SeguroDao{
  
  @Autowired
  private JdbcTemplate template;

  @Override
  public Iterable<Seguro> findAll(Sort sort) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Page<Seguro> findAll(Pageable pageable) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <S extends Seguro> S save(S entity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <S extends Seguro> Iterable<S> save(Iterable<S> entities) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Seguro findOne(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean exists(String id) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Iterable<Seguro> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Iterable<Seguro> findAll(Iterable<String> ids) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public long count() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void delete(String id) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void delete(Seguro entity) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void delete(Iterable<? extends Seguro> entities) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void deleteAll() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Page<Seguro> findByNomeContainingIgnoreCase(String nome, Pageable pageable) {
    // TODO Auto-generated method stub
    return null;
  }
  
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
