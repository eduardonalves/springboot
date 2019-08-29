package cursoSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cursoSpring.model.Endereco;

@Repository
public interface EnderecosRepository  extends JpaRepository<Endereco, Integer>{

}
