package cursoSpring;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cursoSpring.model.Categoria;
import cursoSpring.model.Cidade;
import cursoSpring.model.Cliente;
import cursoSpring.model.Endereco;
import cursoSpring.model.Estado;
import cursoSpring.model.ItemPedido;
import cursoSpring.model.Pagamento;
import cursoSpring.model.PagamentoComBoleto;
import cursoSpring.model.PagamentoComCartao;
import cursoSpring.model.Pedido;
import cursoSpring.model.Produto;
import cursoSpring.model.enun.StatusPagamento;
import cursoSpring.model.enun.TipoCliente;
import cursoSpring.repository.CategoriasRepository;
import cursoSpring.repository.CidadeRepository;
import cursoSpring.repository.ClientesRepository;
import cursoSpring.repository.EnderecosRepository;
import cursoSpring.repository.EstadoRepository;
import cursoSpring.repository.ItemPedidoRepository;
import cursoSpring.repository.PagamentoRepository;
import cursoSpring.repository.PedidoRepository;
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
	@Autowired
	private ClientesRepository clientesRepository;
	@Autowired
	private EnderecosRepository enderecosRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/**/
		/**/
		Categoria cat1 = new Categoria(null, "Bebidas");
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
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "RJ");
		Estado est2 = new Estado(null, "SP");
		
		
		Cidade cid1= new Cidade(null,"Rio de Janeiro", est1);
		
		Cidade cid2 = new Cidade(null,"São Paulo", est2);
		
		
		
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2));
		
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "09556255812",TipoCliente.PESSOAJURIDICA);
		cli1.getTelefones().addAll(Arrays.asList("21 2755-5588", "21 88855-9888"));		
		Endereco end1 = new Endereco(null, "Rua Vento Bravo", "554", "Casa Fundos", "Cordova", "21.775-000", cli1, cid1);
		Endereco end2 = new Endereco(null, "Rua Que Cai", "547", "Apto 301", "Vai Bem", "21.745-578", cli1, cid1);
		
		
		
		Cliente cli2 = new Cliente(null, "Evandro Gomes", "evandro@gmail.com", "58956255812",TipoCliente.PESSOAFISICA);
		cli2.getTelefones().addAll(Arrays.asList("11 2455-5588", "11 97855-9888"));
		Endereco end3 = new Endereco(null, "Rua Ogrimar", "175", "Casa", "Campo Lindo", "11.975-000", cli2, cid2);
	
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));	
		
		cli2.getEnderecos().addAll(Arrays.asList(end3));
		
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2));		
		clientesRepository.saveAll(Arrays.asList(cli1,cli2));
		enderecosRepository.saveAll(Arrays.asList(end1, end2, end3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2018 10:30:00"), end1, cli1);
		
		Pedido ped2 = new Pedido(null, sdf.parse("07/06/2018 10:30:00"), end3, cli2);
		
		Pagamento pgto1 = new PagamentoComCartao(null, StatusPagamento.QUITADO, ped1, 3);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(null, StatusPagamento.PENDENTE, ped2, sdf.parse("25/02/2018 10:30:00"), sdf.parse("17/05/2018 10:30:00"));
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1));
		cli2.getPedidos().addAll(Arrays.asList(ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
		ItemPedido item1 = new ItemPedido(0.00, 1, 8.00, ped1, p1);
		ItemPedido item2 = new ItemPedido(0.00, 1, 8.00, ped1, p2);
		
		ItemPedido item3 = new ItemPedido(0.00, 1, 8.00, ped2, p3);
		
		ped1.getItens().addAll(Arrays.asList(item1, item2));
		ped2.getItens().addAll(Arrays.asList(item3));
		
		p1.getItens().addAll(Arrays.asList(item1));
		p2.getItens().addAll(Arrays.asList(item2));
		p3.getItens().addAll(Arrays.asList(item3));
		
		itemPedidoRepository.saveAll(Arrays.asList(item1, item2, item3));
		
		
	}

}
