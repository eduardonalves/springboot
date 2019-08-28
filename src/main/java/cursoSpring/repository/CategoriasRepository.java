package cursoSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cursoSpring.model.Categoria;

@Repository
public interface CategoriasRepository  extends JpaRepository<Categoria, Integer>{

}
