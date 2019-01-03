package br.com.opus.opussolutionsapp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.opus.opussolutionsapp.entity.TipoSeguro;


@Repository
public interface TipoSeguroDao extends PagingAndSortingRepository<TipoSeguro, String> {
    Page<TipoSeguro>findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    
    TipoSeguro findByNome(String tipoSeguro);
}
