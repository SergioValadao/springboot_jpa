package br.com.savsoftware;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.savsoftware.entities.Categoria;
import br.com.savsoftware.entities.Order;
import br.com.savsoftware.entities.OrderItem;
import br.com.savsoftware.entities.Pagamento;
import br.com.savsoftware.entities.Produto;
import br.com.savsoftware.entities.Usuario;
import br.com.savsoftware.entities.enun.OrderStatus;
import br.com.savsoftware.repositories.CategoriaRepository;
import br.com.savsoftware.repositories.OrderItemRepository;
import br.com.savsoftware.repositories.OrderRepository;
import br.com.savsoftware.repositories.ProdutoRepository;
import br.com.savsoftware.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {

	@Autowired
	private UsuarioRepository userRep;

	@Autowired
	private OrderRepository ordRep;

	@Autowired
	private CategoriaRepository Cat;

	@Autowired
	private ProdutoRepository Prod;
	
	@Autowired
	private OrderItemRepository OrdItemRep;

	@Override
	public void run(String... args) throws Exception {

		Usuario u1 = new Usuario(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		Usuario u2 = new Usuario(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

		userRep.saveAll(Arrays.asList(u1, u2));

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

		ordRep.saveAll(Arrays.asList(o1, o2, o3));

		Categoria cat1 = new Categoria(null, "Electronics");
		Categoria cat2 = new Categoria(null, "Books");
		Categoria cat3 = new Categoria(null, "Computers");

		Cat.saveAll(Arrays.asList(cat1, cat2, cat3));

		Produto p1 = new Produto(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Produto p2 = new Produto(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Produto p3 = new Produto(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Produto p4 = new Produto(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");		
		Produto p5 = new Produto(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		p1.getCategoria().add(cat2);
		p2.getCategoria().add(cat1);
		p3.getCategoria().add(cat1);
		p3.getCategoria().add(cat3);
		p4.getCategoria().add(cat1);
		p4.getCategoria().add(cat3);
		p5.getCategoria().add(cat2);
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPreco());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPreco());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPreco());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPreco());
		
		Prod.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		OrdItemRep.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		Pagamento pay = new Pagamento(null, Instant.parse("2022-09-15T08:46:30Z"), o1);
		o1.setPagamento(pay);
		ordRep.save(o1);
		
	}

}
