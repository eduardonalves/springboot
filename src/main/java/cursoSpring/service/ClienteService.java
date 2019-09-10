package cursoSpring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import cursoSpring.dto.ClienteNewDTO;
import cursoSpring.exception.DataIntegrityException;
import cursoSpring.exception.ObjectNotFoundException;
import cursoSpring.model.Cidade;
import cursoSpring.model.Cliente;
import cursoSpring.model.Endereco;
import cursoSpring.model.enun.TipoCliente;
import cursoSpring.repository.CidadeRepository;
import cursoSpring.repository.ClientesRepository;
import cursoSpring.repository.EnderecosRepository;


@Service
public class ClienteService {
	@Autowired
	private ClientesRepository repo;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecosRepository enderecosRepository;
	
	@Transactional
	public Cliente create(Cliente cliente) {
		cliente.setId(null);
		Cliente cli = repo.save(cliente);
		enderecosRepository.saveAll(cli.getEnderecos());
		return cli;
	}
	
	public Cliente update(Cliente cliente) {
		Cliente newObj = repo.findById(cliente.getId()).orElse(null);
		updateDate(newObj, cliente);
		return repo.save(cliente);
	}
	public Cliente findOne(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> cliente = repo.findById(id);
		
		return cliente.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado Id: "+ id +" Tipo: " + Cliente.class.getName()));
	}
	
	public List<Cliente> findList(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer size, String orderBy, String sort){
		return repo.findAll(PageRequest.of(page, size, Direction.valueOf(sort), orderBy));
	}
	
	
	public Map<String, Boolean> delete(Cliente cliente) {
		try {
			repo.delete(cliente);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é permitido deletar cliente com produtos");
		}
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	public Cliente fromDto(ClienteNewDTO objDto) {
		Cliente cli= new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = cidadeRepository.findById(objDto.getCidadeId()).orElse(null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getComplemento(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		
		if(objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
		
	}
	
	private void updateDate(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	
}
