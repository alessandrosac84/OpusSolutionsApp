package br.com.opus.opussolutionsapp.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Entity
@Table(name = "cliente",
        indexes = {
                @Index(columnList = "nome", name = "nome_cliente_idx"),
        })
public class Cliente {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 150)
    @Column(nullable = false)
    private String nome;

    @NotNull
    @NotEmpty
    @Size(max = 20)
    @Column(nullable = false)
    private String tipoPessoa;

    @Size(max = 14)
    @Column(nullable = true)
    private String cpf;

    @Size(max = 18)
    @Column(nullable = true)
    private String cnpj;  
    
    @Size(max = 150)
    @Column(nullable = true)
    private String razaoSocial;
    
    @Size(max = 11)
    @Column(nullable = false)
    private String status;
    
    @Size(max = 14)
    @Column(nullable = true)
    private String telefone;
    
    @Size(max = 14)
    @Column(nullable = true)
    private String celular;
    
    @Size(max = 150)
    @Column(nullable = true)
    private String email;
    
    @Size(max = 10)
    @Column(nullable = true)
    private String dataContato;
    
    @Size(max = 1)
    @Column(nullable = true)
    private String sexo;
    
    
    

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataContato() {
		return dataContato;
	}

	public void setDataContato(String dataContato) {
		this.dataContato = dataContato;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
