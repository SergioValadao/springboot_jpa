package br.com.savsoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.savsoftware.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
}
