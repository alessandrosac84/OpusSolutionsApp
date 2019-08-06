package br.com.opus.opussolutionsapp.dao;

import java.util.List;

import br.com.opus.opussolutionsapp.entity.Ligacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LigacaoDao extends PagingAndSortingRepository<Ligacao, String> {

    Page<Ligacao> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}