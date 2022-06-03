package com.generation.brafarma.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produtos")
public class produtos_model {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size (min = 5, max = 100, message = "O nome do produto não pode ter menos de 5 e mais que 100 caracteres")
	private String nome;
	
	@NotBlank
	@Size (min = 5, max = 700, message = "A descrição não pode ter menos de 5 e mais que 700 caracteres")
	private String descricao;
	
	@NotNull
	private BigDecimal valor;
	
	@NotNull
	private int quantidade;
	
	@NotBlank
	@Size (min = 5, max = 100, message = "O nome do laboratorio não pode ter menos de 5 e mais que 100 caracteres")
	private String laboratorio;
	
	@Column (name = "vencimento")
	@JsonFormat(pattern = "MM/YYYY")
	private Date data_vencimento;
	
	private String foto;

	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private categorias_model categorias;
	
	
	//===== Getters and Setters ===== //
	
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public Date getData_vencimento() {
		return data_vencimento;
	}

	public void setData_vencimento(Date data_vencimento) {
		this.data_vencimento = data_vencimento;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public categorias_model getCategorias() {
		return categorias;
	}

	public void setCategorias(categorias_model categorias) {
		this.categorias = categorias;
	}	

	

}
