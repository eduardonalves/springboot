package cursoSpring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cursoSpring.exception.ObjectNotFoundException;

import cursoSpring.model.Produto;
import cursoSpring.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repo;
	
	public Produto findOne(Integer id) {
		Optional<Produto> produto = repo.findById(id);
		return produto.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado Id: "+ id +" Tipo: " + Produto.class.getName()));
		
	}
	
	public List<Produto> findList(){
		return repo.findAll();
	}
	public Produto create(Produto produto) {
		return repo.save(produto);
	}
	
	public ResponseEntity<Produto> update(Integer id, Produto produtoParaAtualizar){
		Produto produto = repo.findById(id).orElse(null);
		if(produto != null) {
			produto.setNome(produtoParaAtualizar.getNome());
		}
		final Produto produtoAtualizada = repo.save(produto);
		return ResponseEntity.ok(produtoAtualizada);
	}
	
	public Map<String, Boolean> delete(Produto produto) {
		repo.delete(produto);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
