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

import cursoSpring.model.Pedido;

import cursoSpring.service.PedidoService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {
	@Autowired
	private PedidoService pedidoService;
	
	
	@GetMapping("/list")
	public List<Pedido> list(){
		return pedidoService.findList();
	}
	@GetMapping("view/{id}")
	public ResponseEntity<?> view(@PathVariable(value = "id") Integer id) {
		Pedido pedido = pedidoService.findOne(id);
		return ResponseEntity.ok().body(pedido);
	}
	@PostMapping("/add")
	public Pedido add(@Valid @RequestBody Pedido pedido) {
		return pedidoService.create(pedido);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<Pedido> update(@PathVariable(value = "id") Integer id, @Valid @RequestBody Pedido detalhePedido){
		return pedidoService.update(id, detalhePedido);
	} 
	@DeleteMapping("/delete/{id}")
	public Map<String, Boolean> delete(@PathVariable(value="id") Integer id) throws ObjectNotFoundException {
		Pedido pedido = pedidoService.findOne(id);
		return pedidoService.delete(pedido);
		
	}
}
