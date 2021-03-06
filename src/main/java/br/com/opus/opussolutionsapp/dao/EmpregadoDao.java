package br.com.opus.opussolutionsapp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.opus.opussolutionsapp.entity.Empregado;


@Repository
public interface EmpregadoDao extends PagingAndSortingRepository<Empregado, String> {
    Page<Empregado>findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
