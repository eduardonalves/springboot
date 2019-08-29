package cursoSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cursoSpring.model.Cliente;

@Repository
public interface ClientesRepository  extends JpaRepository<Cliente, Integer>{

}
