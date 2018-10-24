package br.com.opus.opussolutionsapp.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.opus.opussolutionsapp.entity.Empregado;
import br.com.opus.opussolutionsapp.entity.Endereco;


@Repository
public interface EnderecoDao extends PagingAndSortingRepository<Endereco, String> {
    List<Endereco> findByEmpregado(Empregado empregado);

    Page<Endereco> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}