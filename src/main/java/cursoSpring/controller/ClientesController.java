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

import cursoSpring.model.Cliente;

import cursoSpring.service.ClienteService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/clientes")
public class ClientesController {
	@Autowired
	private ClienteService clienteService;
	
	
	@GetMapping("/list")
	public List<Cliente> list(){
		return clienteService.findList();
	}
	@GetMapping("view/{id}")
	public ResponseEntity<?> view(@PathVariable(value = "id") Integer id) {
		Cliente cliente = clienteService.findOne(id);
		return ResponseEntity.ok().body(cliente);
	}
	@PostMapping("/add")
	public Cliente add(@Valid @RequestBody Cliente cliente) {
		return clienteService.create(cliente);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<Cliente> update(@PathVariable(value = "id") Integer id, @Valid @RequestBody Cliente detalheCliente){
		return clienteService.update(id, detalheCliente);
	} 
	@DeleteMapping("/delete/{id}")
	public Map<String, Boolean> delete(@PathVariable(value="id") Integer id) throws ObjectNotFoundException {
		Cliente cliente = clienteService.findOne(id);
		return clienteService.delete(cliente);
		
	}
}
