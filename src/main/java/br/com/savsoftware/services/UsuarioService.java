package br.com.savsoftware.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.savsoftware.entities.Usuario;
import br.com.savsoftware.repositories.UsuarioRepository;
import br.com.savsoftware.resources.exceptions.DatabaseException;
import br.com.savsoftware.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository reposity;
	
	public List<Usuario> findAll(){
		return reposity.findAll();
	}
		
	public Usuario findById(Long id) {
		Optional<Usuario> obj = reposity.findById(id);
		//return obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return obj.orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado."));
	}
	
	public Usuario Salvar(Usuario obj) {
		return reposity.save(obj);
	}
	
	public void Excluir(Long id) {
		try {
			reposity.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			//throw new ResourceNotFoundException(id);
			throw new ResourceNotFoundException("Informe o numero do registro para excluir.");
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Cliente com pedido não pode ser excluido.");
		}
	}
	
	public Usuario Alterar(Usuario obj) {		
		if(obj.getId() != null) {			
			reposity.save(obj);
		}else {
			throw new ResourceNotFoundException("Informe numero do registro para alterar.");
		}
		return obj;		
	}

}
