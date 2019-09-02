package cursoSpring.model;

import javax.persistence.Entity;

import cursoSpring.model.enun.StatusPagamento;
@Entity
public class PagamentoComCartao extends Pagamento {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer numeroDeParcelas;
	public PagamentoComCartao() {
		
	}
	public PagamentoComCartao(Integer id, StatusPagamento status, Pedido pedido,  Integer numeroDeParcelas) {
		super(id, status, pedido);
		// TODO Auto-generated constructor stub
		this.numeroDeParcelas = numeroDeParcelas;
	}
	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}
	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
}
