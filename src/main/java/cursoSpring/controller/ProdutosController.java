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

import cursoSpring.model.Produto;

import cursoSpring.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {
	@Autowired
	private ProdutoService produtoService;
	
	
	@GetMapping("/list")
	public List<Produto> list(){
		return produtoService.findList();
	}
	@GetMapping("view/{id}")
	public ResponseEntity<?> view(@PathVariable(value = "id") Integer id){
		Produto produto = produtoService.findOne(id);
		return ResponseEntity.ok().body(produto);
	}
	@PostMapping("/add")
	public Produto add(@Valid @RequestBody Produto produto) {
		return produtoService.create(produto);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<Produto> update(@PathVariable(value = "id") Integer id, @Valid @RequestBody Produto detalheProduto){
		return produtoService.update(id, detalheProduto);
	} 
	@DeleteMapping("/delete/{id}")
	public Map<String, Boolean> delete(@PathVariable(value="id") Integer id) {
		Produto produto = produtoService.findOne(id);
		return produtoService.delete(produto);
		
	}
}
