package cursoSpring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cursoSpring.dto.CategoriaDto;
import cursoSpring.exception.DataIntegrityException;
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
	
	public Page<Categoria> findPage(Integer page, Integer size, String orderBy, String sort){
		return repo.findAll(PageRequest.of(page, size, Direction.valueOf(sort), orderBy));
	}
	
	public Categoria create(Categoria categoria) {
		return repo.save(categoria);
	}
	
	public Categoria update(Categoria categoria) {
		Categoria newObj = repo.findById(categoria.getId()).orElse(null);
		updateDate(newObj, categoria);
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
		try {
			repo.delete(categoria);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é permitido deletar categoria com produtos");
		}
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	public Categoria fromDto(CategoriaDto objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	private void updateDate(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
		
	}
}
