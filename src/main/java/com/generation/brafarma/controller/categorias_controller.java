package com.generation.brafarma.controller;

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

import com.generation.brafarma.model.categorias_model;
import com.generation.brafarma.repository.categorias_repository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/categorias")
public class categorias_controller {

	@Autowired
	private categorias_repository categoriasRepository;

	@GetMapping // Listar todas as categorias
	public ResponseEntity<List<categorias_model>> getAllCategorias() {
		return ResponseEntity.ok(categoriasRepository.findAll());
	}

	@GetMapping("/{id}") // Buscar categorias por ID
	public ResponseEntity<categorias_model> getById(@PathVariable Long id) {
		return categoriasRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}") // Buscar categorias pelo nome
	public ResponseEntity<List<categorias_model>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(categoriasRepository.findAllBynomeContainingIgnoreCase(nome));
	}

	@GetMapping("/generico/{param}") //Buscar por genericos ou não
	public ResponseEntity<List<categorias_model>> getByGeneric(@PathVariable boolean param) {
		if (param != true) {
			return ResponseEntity.ok(categoriasRepository.findBygenericoFalse());
		} else {
			return ResponseEntity.ok(categoriasRepository.findBygenericoTrue());
		}
	}

	@PostMapping // Criar nova categoria
	public ResponseEntity<categorias_model> postCategoria(@RequestBody categorias_model categoria) {
		return ResponseEntity.ok(categoriasRepository.save(categoria));
	}

	@PutMapping // Editar categoria existente
	public ResponseEntity<categorias_model> putCategoria(@RequestBody categorias_model categoria) {
		categoriasRepository.existsById(categoria.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriasRepository.save(categoria));
	}

	@DeleteMapping("/{id}") // Deletar categoria
	public ResponseEntity<categorias_model> deleteCategoria(@PathVariable Long id) {
		categoriasRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
