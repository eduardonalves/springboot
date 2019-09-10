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

import cursoSpring.dto.ClienteDto;
import cursoSpring.dto.ClienteNewDTO;
import cursoSpring.model.Cliente;
import cursoSpring.service.ClienteService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/clientes")
public class ClientesController {
	@Autowired
	private ClienteService clienteService;
	
	
	@GetMapping("/list")
	public ResponseEntity<List<ClienteDto>> list(){
		List<Cliente> clientes = clienteService.findList();
		List<ClienteDto> listDto = clientes.stream().map(cliente -> new ClienteDto(cliente)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping("/listpage")
	public ResponseEntity<Page<ClienteDto>> listpage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "1") Integer size,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "sort", defaultValue = "ASC") String sort
	){
		Page<Cliente> clientes = clienteService.findPage(page, size, orderBy, sort);
		Page<ClienteDto> listDto = clientes.map(cliente -> new ClienteDto(cliente));
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping("view/{id}")
	public ResponseEntity<?> view(@PathVariable(value = "id") Integer id) {
		Cliente cliente = clienteService.findOne(id);
		return ResponseEntity.ok().body(cliente);
	}
	@PostMapping("/add")
	public ResponseEntity<Void> add(@Valid @RequestBody ClienteNewDTO clientedto) {
		Cliente obj = clienteService.fromDto(clientedto); 
		obj = clienteService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/clientes/view/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<Void> update(@PathVariable(value = "id") Integer id, @Valid @RequestBody ClienteNewDTO detalheCliente){
		Cliente obj = clienteService.fromDto(detalheCliente);
		clienteService.update(obj);
		return ResponseEntity.noContent().build();
	} 
	@DeleteMapping("/delete/{id}")
	public Map<String, Boolean> delete(@PathVariable(value="id") Integer id) throws ObjectNotFoundException {
		Cliente cliente = clienteService.findOne(id);
		return clienteService.delete(cliente);
		
	}
}
