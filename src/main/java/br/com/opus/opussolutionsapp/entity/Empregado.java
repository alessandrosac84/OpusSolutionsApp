package br.com.opus.opussolutionsapp.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Otorus
 * @since : 1/4/18
 */
@Data
@ToString(exclude = "listEndereco")
@Entity
@Table(name = "empregado",
        indexes = {
                @Index(columnList = "nome", name = "nome_empregado_idx"),
                @Index(columnList = "info", name = "info_empregado_idx")
        })
public class Empregado {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 255)
    @Column(nullable = false)
    private String nome;

    @NotEmpty
    @Size(max = 255)
    @Column(nullable = false)
    private String info;

    @OneToMany(mappedBy = "empregado",
            cascade = CascadeType.REFRESH,
            orphanRemoval = true)
    private List<Endereco> listEndereco = new ArrayList<>();

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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<Endereco> getListEndereco() {
		return listEndereco;
	}

	public void setListEndereco(List<Endereco> listEndereco) {
		this.listEndereco = listEndereco;
	}

}
