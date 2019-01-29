package br.com.opus.opussolutionsapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.opus.opussolutionsapp.entity.FileModel;

@Repository
public interface FileModelDAO extends JpaRepository<FileModel, String> {

}
