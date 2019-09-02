package cursoSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cursoSpring.model.Pagamento;

@Repository
public interface PagamentoRepository  extends JpaRepository<Pagamento, Integer>{

}
