package com.generation.brafarma.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.brafarma.model.produtos_model;

@Repository
public interface produtos_repository extends JpaRepository<produtos_model, Long>{

	public List<produtos_model> findAllBynomeContainingIgnoreCase (@Param("nome") String nome); //Listar todos pelo nome
	public List<produtos_model> findAllByvalorGreaterThanEqual (BigDecimal valor); //Valor maior que
	public List<produtos_model> findAllByvalorLessThanEqual (BigDecimal valor); //Valor Menor que
	public List<produtos_model> findAllByvalorBetween (BigDecimal valorMin, BigDecimal valorMax); //Intervalo de valores
	
	public List<produtos_model> findAllByNomeContainingAndLaboratorioContainingIgnoreCase 
		(@Param("nome") String nome, @Param("laboratorio") String laboratorio);
}
