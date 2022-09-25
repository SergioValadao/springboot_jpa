package br.com.savsoftware.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.savsoftware.entities.Produto;
import br.com.savsoftware.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository reposity;
	
	public List<Produto> findAll(){
		return reposity.findAll();
	}
		
	public Produto findById(Long id) {
		Optional<Produto> obj = reposity.findById(id);
		return obj.get();
	}

}
