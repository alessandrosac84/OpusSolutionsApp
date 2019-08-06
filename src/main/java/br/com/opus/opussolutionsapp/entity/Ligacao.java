package br.com.opus.opussolutionsapp.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Entity
@Table(name = "ligacao",
        indexes = {
                @Index(columnList = "nome", name = "ligacao_idx"),
        })
public class Ligacao {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 150)
    @Column(nullable = false)
    private String nome;

	@Size(max = 14)
	@Column(nullable = true)
    private String telefone;

	@Column(nullable = false)
	@NotNull
	@NotEmpty
    private String status;

	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private String orcado;

	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private String fechado;

	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private String observacao;

	@Size(max = 10)
	@Column(nullable = true)
	private String dataContato;

	@Size(max = 10)
	@Column(nullable = true)
	private String dataRetorno;

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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDataContato() {
		return dataContato;
	}

	public void setDataContato(String dataContato) {
		this.dataContato = dataContato;
	}

	public String getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(String dataRetorno) {
		this.dataRetorno = dataRetorno;
	}
}
