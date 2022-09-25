package br.com.savsoftware.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.savsoftware.entities.enun.OrderStatus;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant data;
	
	private int orderStatus;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Usuario client;
	
	@OneToMany(mappedBy = "id.order")	//Aqui se refere ao campo associado no OrdemItem "id.order"
	private Set<OrderItem> items = new HashSet<>();
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Pagamento pagamento;

	public Order() {
	}

	public Order(Long id, Instant data, OrderStatus orderStatus, Usuario client) {
		super();
		this.id = id;
		this.data = data;
		setOrderStatus(orderStatus); 		// porque já verifica se é null
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getClient() {
		return client;
	}

	public void setClient(Usuario client) {
		this.client = client;
	}

	public Instant getData() {
		return data;
	}
	
	public void setData(Instant data) {
		this.data = data;
	}	
	
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueof(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}
	
	public Set<OrderItem> getItems(){
		return items;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public Double getTotal() {
		Double sum = 0.0;
		for(OrderItem x: items) {
			sum += x.getSubTotal();
		}
		return sum;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
}
