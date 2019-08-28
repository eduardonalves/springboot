package cursoSpring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cursoSpring.exception.ObjectNotFoundException;
import cursoSpring.model.Categoria;
import cursoSpring.repository.CategoriasRepository;


@Service
public class CategoriaService {
	@Autowired
	private CategoriasRepository repo;
	
	public Categoria findOne(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> categoria = repo.findById(id);
		
		return categoria.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado Id: "+ id +" Tipo: " + Categoria.class.getName()));
	}
	
	public List<Categoria> findList(){
		return repo.findAll();
	}
	public Categoria create(Categoria categoria) {
		return repo.save(categoria);
	}
	
	public ResponseEntity<Categoria> update(Integer id, Categoria categoriaParaAtualizar){
		Categoria categoria = repo.findById(id).orElse(null);
		if(categoria != null) {
			categoria.setNome(categoriaParaAtualizar.getNome());
		}
		final Categoria categoriaAtualizada = repo.save(categoria);
		return ResponseEntity.ok(categoriaAtualizada);
	}
	
	public Map<String, Boolean> delete(Categoria categoria) {
		repo.delete(categoria);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
