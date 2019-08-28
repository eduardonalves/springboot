package cursoSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cursoSpring.model.Cidade;
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
