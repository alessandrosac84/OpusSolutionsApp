package br.com.opus.opussolutionsapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="file_model")
public class FileModel {
    @Id
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "type")
    private String type;
    
    @Lob
    @Column(name="pic")
    private byte[] pic;
    
    public FileModel(){}
    
    public FileModel(long id, String name, String type, byte[] pic){
        this.id = id;
        this.name = name;
        this.type = type;
        this.pic = pic;
    }
    
    public Long getId(){
        return this.id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getType(){
        return this.type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public byte[] getPic(){
        return this.pic;
    }
    
    public void setPic(byte[] pic){
        this.pic = pic;
    }
}
