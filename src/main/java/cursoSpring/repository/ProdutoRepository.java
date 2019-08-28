package cursoSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cursoSpring.model.Produto;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, Integer>{

}
