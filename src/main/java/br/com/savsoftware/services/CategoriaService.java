package br.com.savsoftware.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.savsoftware.entities.Categoria;
import br.com.savsoftware.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository reposity;
	
	public List<Categoria> findAll(){
		return reposity.findAll();
	}
		
	public Categoria findById(Long id) {
		Optional<Categoria> obj = reposity.findById(id);
		return obj.get();
	}

}
