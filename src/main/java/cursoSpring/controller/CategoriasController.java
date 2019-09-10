package cursoSpring.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cursoSpring.dto.CategoriaDto;
import cursoSpring.model.Categoria;

import cursoSpring.service.CategoriaService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {
	@Autowired
	private CategoriaService categoriaService;
	
	
	@GetMapping("/list")
	public ResponseEntity<List<CategoriaDto>> list(){
		List<Categoria> categorias = categoriaService.findList();
		List<CategoriaDto> listDto = categorias.stream().map(categoria -> new CategoriaDto(categoria)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping("/listpage")
	public ResponseEntity<Page<CategoriaDto>> listpage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "1") Integer size,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "sort", defaultValue = "ASC") String sort
	){
		Page<Categoria> categorias = categoriaService.findPage(page, size, orderBy, sort);
		Page<CategoriaDto> listDto = categorias.map(categoria -> new CategoriaDto(categoria));
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping("view/{id}")
	public ResponseEntity<?> view(@PathVariable(value = "id") Integer id) {
		Categoria categoria = categoriaService.findOne(id);
		return ResponseEntity.ok().body(categoria);
	}
	@PostMapping("/add")
	public ResponseEntity<Void> add(@Valid @RequestBody CategoriaDto categoriadto) {
		Categoria obj = categoriaService.fromDto(categoriadto); 
		obj = categoriaService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/categorias/view/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<Void> update(@PathVariable(value = "id") Integer id, @Valid @RequestBody CategoriaDto detalheCategoria){
		Categoria obj = categoriaService.fromDto(detalheCategoria);
		categoriaService.update(id, obj);
		return ResponseEntity.noContent().build();
	} 
	@DeleteMapping("/delete/{id}")
	public Map<String, Boolean> delete(@PathVariable(value="id") Integer id) throws ObjectNotFoundException {
		Categoria categoria = categoriaService.findOne(id);
		return categoriaService.delete(categoria);
		
	}
}
