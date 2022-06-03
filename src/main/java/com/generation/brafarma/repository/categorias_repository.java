package com.generation.brafarma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.brafarma.model.categorias_model;

@Repository
public interface categorias_repository extends JpaRepository <categorias_model, Long>{

	public List<categorias_model> findAllBynomeContainingIgnoreCase (@Param("nome") String nome); //Listar todos pelo nome
	public List<categorias_model> findBygenericoTrue(); //Listar Genericos
	public List<categorias_model> findBygenericoFalse(); //Listar não Genericos
}
