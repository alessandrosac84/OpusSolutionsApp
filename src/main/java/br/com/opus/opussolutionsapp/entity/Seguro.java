package br.com.opus.opussolutionsapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;


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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clienteFK")
    private Cliente cliente;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_seguroFK")
    private TipoSeguro tipoSeguro;
    
    @ManyToOne(optional=true, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "propostaFK")
    private FileModel proposta;
    
    @ManyToOne(optional=true, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "apoliceFK")
    private FileModel apolice;
    
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
    
    @Transient
    private String cpf;
    
    @Transient
    private String cnpj;
    
    @Transient
    private String tipoPessoa;
    
    @Size(max = 255)
    @Column(nullable = true)
    private String seguradora;

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
	
	public FileModel getProposta() {
      return proposta;
    }

    public void setProposta(FileModel proposta) {
        this.proposta = proposta;
      }
  
      public FileModel getApolice() {
        return apolice;
      }

    public void setApolice(FileModel apolice) {
        this.apolice = apolice;
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

	public String getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(String seguradora) {
		this.seguradora = seguradora;
	}
}
