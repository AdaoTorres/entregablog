package org.generatio.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generatio.blogpessoal.model.Postagem;
import org.generatio.blogpessoal.repository.PostagemRepository;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

	@Autowired
	private PostagemRepository postagemRepository;
	
		@GetMapping
		public ResponseEntity<List<Postagem>> GetAll(){
			return ResponseEntity.ok(postagemRepository.findAll());
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Postagem> GetById(@PathVariable Long id){
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}
		
		@GetMapping("/titulo/{titulo}")
			public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
				return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
		}
		
		@PostMapping
		public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem){
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(postagemRepository.save(postagem));
			
		}
		
		@PutMapping
		public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){
			return postagemRepository.findById(postagem.getId())
					.map(resposta -> ResponseEntity.status(HttpStatus.OK)
							.body(postagemRepository.save(postagem)))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}	
		
		@ResponseStatus(HttpStatus.NO_CONTENT)
		@DeleteMapping("/{id}")
			public void delete(@PathVariable Long id) {
			Optional<Postagem> postagem = postagemRepository.findById(id);
				
				if (postagem.isEmpty())
					throw new ResponseStatusException(HttpStatus.NOT_FOUND);
				
				postagemRepository.deleteById(id);
			
		}
		
}



package org.generatio.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generatio.blogpessoal.model.TemaModel;
import org.generatio.blogpessoal.repository.TemaRepository;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {

	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping
	public ResponseEntity<List<TemaModel>> getAll(){
		return ResponseEntity.ok(temaRepository.findAll());
	}
	
	 @GetMapping("/{id}")
	    public ResponseEntity<TemaModel> getById(@PathVariable Long id){
		 
	        return temaRepository.findById(id)
	            .map(resposta -> ResponseEntity.ok(resposta))
	            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
 
	 }
	 
	 @GetMapping("/descricao/{descricao}")
	    public ResponseEntity<List<TemaModel>> getByTitle(@PathVariable 
	    String descricao){
	        return ResponseEntity.ok(temaRepository
	            .findAllByDescricaoContainingIgnoreCase(descricao));
	 }
	 
	 @PostMapping
	    public ResponseEntity<TemaModel> post(@Valid @RequestBody TemaModel tema){
	        return ResponseEntity.status(HttpStatus.CREATED)
	 	                .body(temaRepository.save(tema));
	 }
	 
	  
	    @PutMapping
	    public ResponseEntity<TemaModel> put(@Valid @RequestBody TemaModel tema){
	        return temaRepository.findById(tema.getId())
	            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
	            .body(temaRepository.save(tema)))
	            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	    }
	    
	    
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        Optional<TemaModel> tema = temaRepository.findById(id);
	        
	        if(tema.isEmpty())
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	        
	        temaRepository.deleteById(id);              
	    }
}
	 


package org.generatio.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generatio.blogpessoal.model.Usuario;
import org.generatio.blogpessoal.model.UsuarioLogin;
import org.generatio.blogpessoal.repository.UsuarioRepository;
import org.generatio.blogpessoal.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @GetMapping("/all")
    public ResponseEntity <List<Usuario>> getAll(){     
        return ResponseEntity.ok(usuarioRepository.findAll());  
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        return usuarioRepository.findById(id)
            .map(resposta -> ResponseEntity.ok(resposta))
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/logar")
    public ResponseEntity<UsuarioLogin> login(@RequestBody Optional<UsuarioLogin> usuarioLogin) {
        return usuarioService.autenticarUsuario(usuarioLogin)
            .map(resposta -> ResponseEntity.ok(resposta))
            .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario) {

        return usuarioService.cadastrarUsuario(usuario)
            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
            .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

    }

    @PutMapping("/atualizar")
    public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.atualizarUsuario(usuario)
            .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}