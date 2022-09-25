package br.com.savsoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.savsoftware.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
}
