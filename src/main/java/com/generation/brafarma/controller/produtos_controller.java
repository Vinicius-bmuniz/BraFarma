package com.generation.brafarma.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.brafarma.model.produtos_model;
import com.generation.brafarma.repository.categorias_repository;
import com.generation.brafarma.repository.produtos_repository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/produtos")
public class produtos_controller {

	@Autowired
	private produtos_repository produtosRepository;

	@Autowired
	private categorias_repository categoriasRepository;

	@GetMapping // Listar todos os produtos
	public ResponseEntity<List<produtos_model>> getAllprodutos() {
		return ResponseEntity.ok(produtosRepository.findAll());
	}

	@GetMapping("/{id}") // Listar produtos por id
	public ResponseEntity<produtos_model> getProdutosById(@PathVariable("id") Long id) {
		return produtosRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}") //Listar produtos somente pelo nome
	public ResponseEntity<List<produtos_model>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(produtosRepository.findAllBynomeContainingIgnoreCase(nome));
	}

	@GetMapping("/{nome}/{laboratorio}") //Listar produtos pelo nome e laboratorio
	public ResponseEntity<List<produtos_model>> getByNomelaboratorio(@PathVariable String nome, @PathVariable String laboratorio) {
		return ResponseEntity.ok(produtosRepository.findAllByNomeContainingAndLaboratorioContainingIgnoreCase (nome, laboratorio));
	}
	
	@GetMapping("preco/maiorque{valor}") //Buscar por valor maior que
	public ResponseEntity<List<produtos_model>> getByValorMaior(@PathVariable BigDecimal valor) {
		return ResponseEntity.ok(produtosRepository.findAllByvalorGreaterThanEqual(valor));
	}

	@GetMapping("preco/menorque{valor}") //Buscar por valor menor que
	public ResponseEntity<List<produtos_model>> getByValorMenor(@PathVariable BigDecimal valor) {
		return ResponseEntity.ok(produtosRepository.findAllByvalorLessThanEqual(valor));
	}

	@GetMapping("preco/de{valor1}a{valor2}") //Buscar por intervalor de valor
	public ResponseEntity<List<produtos_model>> getByValor(@PathVariable BigDecimal valor1,	@PathVariable BigDecimal valor2) {
		return ResponseEntity.ok(produtosRepository.findAllByvalorBetween(valor1, valor2));
	}

	@PostMapping // Verificar como resolver erro ao não enviar o ID da categoria na requisição
	public ResponseEntity<produtos_model> postProduto(@RequestBody produtos_model produto) {
		return categoriasRepository.findById(produto.getCategorias().getId())
				.map(save -> ResponseEntity.ok(produtosRepository.save(produto)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PutMapping // Colocar tratamento de erro para não criar novo produto caso não exista
	public ResponseEntity<produtos_model> putProduto(@RequestBody produtos_model produto) {
		if (produtosRepository.existsById(produto.getId())
				&& categoriasRepository.existsById(produto.getCategorias().getId())) {
			return ResponseEntity.status(HttpStatus.CREATED).body(produtosRepository.save(produto));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<produtos_model> deleteProduto(@PathVariable Long id) {
		if (produtosRepository.existsById(id))
			produtosRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
