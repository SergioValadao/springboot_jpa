package br.com.savsoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.savsoftware.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
}
