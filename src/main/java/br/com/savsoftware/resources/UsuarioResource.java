package br.com.savsoftware.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.savsoftware.entities.Usuario;
import br.com.savsoftware.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;

	@GetMapping
	public ResponseEntity<List<Usuario>> finAll(){
		List<Usuario> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		Usuario obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario obj){
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		obj = service.Salvar(obj);
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	//public void excluirUsuario(@PathVariable Long id){  // Aqui, sem o ResponseEntity funcionou
	public ResponseEntity<Void> Excluir(@PathVariable Long id){
		service.Excluir(id);
		return ResponseEntity.noContent().build();		//Não precisa sem o response entity
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> Alterar(@RequestBody Usuario obj){
		service.Alterar(obj);
		//return ResponseEntity.noContent().build();
		return ResponseEntity.ok().body(obj);
	}
}
