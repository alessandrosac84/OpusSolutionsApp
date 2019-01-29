package br.com.opus.opussolutionsapp.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.opus.opussolutionsapp.dao.FileModelDAO;
import br.com.opus.opussolutionsapp.entity.FileModel;

@Service
public class FileModelImpl {

    @Autowired
    private FileModelDAO fileModelDAO;

    public FileModel getFile(String fileId) {
        return fileModelDAO.findOne(fileId);
    }
}
