package cursoSpring.controller;

import java.util.List;
import java.util.Map;


import javax.validation.Valid;

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

import cursoSpring.model.Categoria;

import cursoSpring.service.CategoriaService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {
	@Autowired
	private CategoriaService categoriaService;
	
	
	@GetMapping("/list")
	public List<Categoria> list(){
		return categoriaService.findList();
	}
	@GetMapping("view/{id}")
	public ResponseEntity<?> view(@PathVariable(value = "id") Integer id) {
		Categoria categoria = categoriaService.findOne(id);
		return ResponseEntity.ok().body(categoria);
	}
	@PostMapping("/add")
	public Categoria add(@Valid @RequestBody Categoria categoria) {
		return categoriaService.create(categoria);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<Categoria> update(@PathVariable(value = "id") Integer id, @Valid @RequestBody Categoria detalheCategoria){
		return categoriaService.update(id, detalheCategoria);
	} 
	@DeleteMapping("/delete/{id}")
	public Map<String, Boolean> delete(@PathVariable(value="id") Integer id) throws ObjectNotFoundException {
		Categoria categoria = categoriaService.findOne(id);
		return categoriaService.delete(categoria);
		
	}
}
