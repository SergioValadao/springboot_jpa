package br.com.savsoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.savsoftware.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
}
