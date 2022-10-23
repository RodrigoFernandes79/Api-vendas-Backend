package com.rodrigo.vendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.vendas.domain.Produto;
import com.rodrigo.vendas.repositories.ProdutoRepository;
import com.rodrigo.vendas.services.exceptions.DataViolationException;
import com.rodrigo.vendas.services.exceptions.EntityNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> listarProdutos() {
		List<Produto> obj = produtoRepository.findAll();
		return obj;
	}

	public Produto inserirProduto(Produto produto) {
		Produto descProduto = produtoRepository.findByDescricaoProduto(produto.getDescricaoProduto());

		if (descProduto != null) {
			throw new DataViolationException(
					"Produto  " + produto.getDescricaoProduto() + " já existe no Banco de Dados!");
		}

		Produto obj = produtoRepository.save(produto);
		return obj;
	}

	public Produto listarProdutoPorId(Integer id) {

		Produto obj = produtoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Id " + id + " não encontrado"));
		return obj;
	}

	public List<Produto> listarProdutoPorDescricao(String descricaoProduto) {

		List<Produto> obj = produtoRepository.findByDescricaoProdutoIgnoreCaseContaining(descricaoProduto);
		return obj;
	}

	public void deletarProdutoPorId(Integer id) {
		try {
			Produto prod = produtoRepository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException("Id " + id + " não encontrado"));

			produtoRepository.delete(prod);
		} catch (DataViolationException e) {
			throw new DataViolationException("Produto não pôde ser deletado. Existem pedidos associados");
		}

	}

	public Produto atualizarProdutoPorId(Integer id, Produto produto) {
		Produto desProd = produtoRepository.findByDescricaoProduto(produto.getDescricaoProduto());

		if (desProd != null && desProd.getId() != id) {
			throw new DataViolationException(
					"Descrição " + produto.getDescricaoProduto() + " já existe no Banco de Dados");
		}

		return produtoRepository.findById(id).map(newProd -> {
			newProd.getId();
			newProd.setDescricaoProduto(produto.getDescricaoProduto());
			newProd.setPrecoUnitario(produto.getPrecoUnitario());

			Produto obj = produtoRepository.save(newProd);

			return obj;
		}).orElseThrow(() -> new EntityNotFoundException("Id " + id + " não encontrado"));

	}

}
