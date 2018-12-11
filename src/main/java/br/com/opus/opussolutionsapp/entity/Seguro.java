package br.com.opus.opussolutionsapp.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Entity
@Table(name = "seguro",
        indexes = {
                @Index(columnList = "clienteFK", name = "clienteFK_idx"),
                @Index(columnList = "status", name = "status_idx")
        })
public class Seguro {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clienteFK")
    private Cliente cliente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_seguroFK")
    private TipoSeguro tipoSeguro;
    
    @Size(max = 50)
    @Column(nullable = true)
    private String valorSeguro;
    
    @Size(max = 50)
    @Column(nullable = true)
    private String valorSegurado;

    @Size(max = 50)
    @Column(nullable = true)
    private String percentualComissao;
    
    @Size(max = 10)
    @Column(nullable = true)
    private String dataProposta;
    
    @Size(max = 10)
    @Column(nullable = true)
    private String dataInicioVigencia;
    
    @Size(max = 10)
    @Column(nullable = true)
    private String dataFimVigencia;
    
    @Size(max = 15)
    @Column(nullable = true)
    private String status;
    
    @Size(max = 150)
    @Column(nullable = true)
    private String nome;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoSeguro getTipoSeguro() {
		return tipoSeguro;
	}

	public void setTipoSeguro(TipoSeguro tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}

	public String getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(String valorSeguro) {
		this.valorSeguro = valorSeguro;
	}

	public String getValorSegurado() {
		return valorSegurado;
	}

	public void setValorSegurado(String valorSegurado) {
		this.valorSegurado = valorSegurado;
	}

	public String getPercentualComissao() {
		return percentualComissao;
	}

	public void setPercentualComissao(String percentualComissao) {
		this.percentualComissao = percentualComissao;
	}

	public String getDataProposta() {
		return dataProposta;
	}

	public void setDataProposta(String dataProposta) {
		this.dataProposta = dataProposta;
	}

	public String getDataInicioVigencia() {
		return dataInicioVigencia;
	}

	public void setDataInicioVigencia(String dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}

	public String getDataFimVigencia() {
		return dataFimVigencia;
	}

	public void setDataFimVigencia(String dataFimVigencia) {
		this.dataFimVigencia = dataFimVigencia;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
    

}
