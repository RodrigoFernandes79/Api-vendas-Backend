package com.rodrigo.vendas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rodrigo.vendas.domain.Cliente;
import com.rodrigo.vendas.domain.ItemPedido;
import com.rodrigo.vendas.domain.Pedido;
import com.rodrigo.vendas.domain.Produto;
import com.rodrigo.vendas.repositories.ClienteRepository;
import com.rodrigo.vendas.repositories.ItemPedidoRepository;
import com.rodrigo.vendas.repositories.PedidoRepository;
import com.rodrigo.vendas.repositories.ProdutoRepository;

@SpringBootApplication
public class VendasApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		Produto prod1 = new Produto(null, "Tv 24 polegadas Samsung", BigDecimal.valueOf(2000.95));
		Produto prod2 = new Produto(null, "Geladeira 2.000L Brastemp", BigDecimal.valueOf(2500.50));

		produtoRepository.saveAll(Arrays.asList(prod1, prod2));

		Cliente cli1 = new Cliente(null, "Pedro Almeida", "34544334412");
		clienteRepository.saveAll(Arrays.asList(cli1));

		Pedido ped1 = new Pedido(null, cli1, LocalDate.now(), null);
		Pedido ped2 = new Pedido(null, cli1, LocalDate.of(2022, 8, 25), null);

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		ItemPedido item1 = new ItemPedido(null, ped1, prod1, 1);
		ItemPedido item2 = new ItemPedido(null, ped2, prod2, 2);

		itemPedidoRepository.saveAll(Arrays.asList(item1, item2));

	}

}
