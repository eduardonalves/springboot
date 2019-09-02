package cursoSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cursoSpring.model.ItemPedido;

@Repository
public interface ItemPedidoRepository  extends JpaRepository<ItemPedido, Integer>{

}
