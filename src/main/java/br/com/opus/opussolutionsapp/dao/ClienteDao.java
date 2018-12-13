package br.com.opus.opussolutionsapp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.opus.opussolutionsapp.entity.Cliente;


@Repository
public interface ClienteDao extends PagingAndSortingRepository<Cliente, String> {
    Page<Cliente>findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    
    Cliente findByCpf(String cpf);
    
    Cliente findByCnpj(String cnpj);
}
