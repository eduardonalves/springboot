package cursoSpring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cursoSpring.model.Cidade;
import cursoSpring.model.Estado;
import cursoSpring.repository.CategoriasRepository;
import cursoSpring.repository.CidadeRepository;
import cursoSpring.repository.EstadoRepository;
import cursoSpring.repository.ProdutoRepository;

@SpringBootApplication
public class CursoSpringApplication implements CommandLineRunner{
	@Autowired
	private CategoriasRepository categoriasRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*Categoria cat1 = new Categoria(null, "Bebidas");
		Categoria cat2 = new Categoria(null, "Cama e Mesa");
		Produto p1= new Produto(null, "Cocal Cola", 8.00);
		Produto p2 = new Produto(null, "Cerveja", 10.00);
		Produto p3 = new Produto(null, "Toalha Teka", 15.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2));
		cat2.getProdutos().addAll(Arrays.asList(p3));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat2));
		
		categoriasRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));*/
		
		/*Estado est1 = new Estado(null, "RJ");
		Estado est2 = new Estado(null, "SP");
		
		
		Cidade cid1= new Cidade(null,"Rio de Janeiro", est1);
		Cidade cid2 = new Cidade(null,"São Paulo", est2);
		
		
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2));*/
	
		
	}

}
