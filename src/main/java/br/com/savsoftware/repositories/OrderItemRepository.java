package br.com.savsoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.savsoftware.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
}
