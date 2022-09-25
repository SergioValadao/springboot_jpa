package br.com.savsoftware.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.savsoftware.entities.pk.OrderItemPk;

@Entity
@Table(name = "tb_order_Item")
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderItemPk id = new OrderItemPk();
	private Integer Quantidade;
	private Double Preco;

	public OrderItem() {		
	}

	public OrderItem(Order ord, Produto prod, Integer quantidade, Double preco) {
		super();
		id.setOrder(ord);
		id.setProduto(prod);
		Quantidade = quantidade;
		Preco = preco;
	}
	
	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
		
	public Produto getProduto() {
		return id.getProduto();
	}
	
	public void setProduto(Produto prod) {
		id.setProduto(prod);
	}

	public Integer getQuantidade() {
		return Quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		Quantidade = quantidade;
	}

	public Double getPreco() {
		return Preco;
	}

	public void setPreco(Double preco) {
		Preco = preco;
	}
	
	public Double getSubTotal() {
		return Preco * Quantidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
}
