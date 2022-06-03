package com.generation.brafarma.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "tb_categorias")
public class categorias_model {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size (min = 5, max = 100, message = "O nome da categoria não pode ser menor que 5 e maior que 100 caracteres")
	private String nome;
	
	@NotBlank
	@Size (min = 5, max = 700, message = "A descrição da categoria não pode ser menor que 5 e maior que 700 caracteres")
	private String descricao;
	
	@Column (name = "is_generic")
	private boolean generico;

	@OneToMany (mappedBy = "categorias", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("categorias")
	private List<produtos_model> produtos;
	
	
	
	// ===== Getters and Setters ===== //
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isGenerico() {
		return generico;
	}

	public void setGenerico(boolean generico) {
		this.generico = generico;
	}

	public List<produtos_model> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<produtos_model> produtos) {
		this.produtos = produtos;
	}	

}
