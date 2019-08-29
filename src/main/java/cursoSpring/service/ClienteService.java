package cursoSpring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cursoSpring.exception.ObjectNotFoundException;
import cursoSpring.model.Cliente;

import cursoSpring.repository.ClientesRepository;


@Service
public class ClienteService {
	@Autowired
	private ClientesRepository repo;
	
	public Cliente findOne(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> cliente = repo.findById(id);
		
		return cliente.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado Id: "+ id +" Tipo: " + Cliente.class.getName()));
	}
	
	public List<Cliente> findList(){
		return repo.findAll();
	}
	public Cliente create(Cliente cliente) {
		return repo.save(cliente);
	}
	
	public ResponseEntity<Cliente> update(Integer id, Cliente clienteParaAtualizar){
		Cliente cliente = repo.findById(id).orElse(null);
		if(cliente != null) {
			cliente.setNome(clienteParaAtualizar.getNome());
		}
		final Cliente clienteAtualizada = repo.save(cliente);
		return ResponseEntity.ok(clienteAtualizada);
	}
	
	public Map<String, Boolean> delete(Cliente cliente) {
		repo.delete(cliente);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
