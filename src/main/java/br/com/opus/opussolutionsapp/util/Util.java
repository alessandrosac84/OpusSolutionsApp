package br.com.opus.opussolutionsapp.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import javax.annotation.ManagedBean;

import org.springframework.util.StringUtils;

import br.com.opus.opussolutionsapp.entity.FileModel;

@ManagedBean
public class Util {
	
	public FileModel storeFile(MultipartFile file) throws Exception {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            
            FileModel upFile = new FileModel(fileName, file.getContentType(), file.getBytes());

            return upFile;
        } catch (IOException ex) {
            throw new Exception();
        }
    }

}
