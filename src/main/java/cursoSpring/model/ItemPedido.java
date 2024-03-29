package cursoSpring.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class ItemPedido implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Double desconto;
	private Integer qtde;
	private Double preco;
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPk id= new ItemPedidoPk();
	public ItemPedido() {
		
	}
	public ItemPedido(Double desconto, Integer qtde, Double preco, Pedido pedido, Produto produto) {
		super();
		this.desconto = desconto;
		this.qtde = qtde;
		this.preco = preco;
		id.setPedido(pedido);
		id.setProduto(produto);
	}
	public Double getDesconto() {
		return desconto;
	}
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	public Integer getQtde() {
		return qtde;
	}
	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public ItemPedidoPk getId() {
		return id;
	}
	public void setId(ItemPedidoPk id) {
		this.id = id;
	}
	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	public Produto getProduto() {
		return id.getProduto();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
