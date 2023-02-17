package org.generatio.blogpessoal.repository;

import java.util.List;

import org.generatio.blogpessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	public List <Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);


}
package org.generatio.blogpessoal.repository;

import java.util.List;

import org.generatio.blogpessoal.model.TemaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepository extends JpaRepository<TemaModel, Long>{
	public List<TemaModel> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);

}


package org.generatio.blogpessoal.repository;

import java.util.List;
import java.util.Optional;

import org.generatio.blogpessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByUsuario(String usuario);
	
	public List<Usuario> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
}
