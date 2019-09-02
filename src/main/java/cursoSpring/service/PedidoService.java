package cursoSpring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cursoSpring.exception.ObjectNotFoundException;
import cursoSpring.model.Pedido;
import cursoSpring.repository.PedidoRepository;


@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;
	
	public Pedido findOne(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> pedido = repo.findById(id);
		
		return pedido.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado Id: "+ id +" Tipo: " + Pedido.class.getName()));
	}
	
	public List<Pedido> findList(){
		return repo.findAll();
	}
	public Pedido create(Pedido pedido) {
		return repo.save(pedido);
	}
	
	public ResponseEntity<Pedido> update(Integer id, Pedido pedidoParaAtualizar){
		Pedido pedido = repo.findById(id).orElse(null);
		if(pedido != null) {
			pedido.setStatus(pedidoParaAtualizar.getStatus());
		}
		final Pedido pedidoAtualizada = repo.save(pedido);
		return ResponseEntity.ok(pedidoAtualizada);
	}
	
	public Map<String, Boolean> delete(Pedido pedido) {
		repo.delete(pedido);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
