package cursoSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cursoSpring.model.Pedido;

@Repository
public interface PedidoRepository  extends JpaRepository<Pedido, Integer>{

}
