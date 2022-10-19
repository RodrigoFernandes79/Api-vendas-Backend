package com.rodrigo.vendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.rodrigo.vendas.domain.Produto;
import com.rodrigo.vendas.repositories.ProdutoRepository;

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
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Produto  " + produto.getDescricaoProduto() + " já existe no Banco de Dados!");
		}

		Produto obj = produtoRepository.save(produto);
		return obj;
	}

	public Produto listarProdutoPorId(Integer id) {

		Produto obj = produtoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id " + id + " não encontrado"));
		return obj;
	}

	public List<Produto> listarProdutoPorDescricao(String descricaoProduto) {

		List<Produto> obj = produtoRepository.findByDescricaoProdutoIgnoreCaseContaining(descricaoProduto);
		return obj;
	}

	public void deletarProdutoPorId(Integer id) {
		try {
			Produto prod = produtoRepository.findById(id).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id " + id + " não encontrado"));

			produtoRepository.delete(prod);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Produto não pôde ser deletado. Existem pedidos associados");
		}

	}

	public Produto atualizarProdutoPorId(Integer id, Produto produto) {
		Produto desProd = produtoRepository.findByDescricaoProduto(produto.getDescricaoProduto());

		if (desProd != null && desProd.getId() != id) {
			throw new DataIntegrityViolationException(
					"Descrição " + produto.getDescricaoProduto() + " já existe no Banco de Dados");
		}

		return produtoRepository.findById(id).map(newProd -> {
			newProd.getId();
			newProd.setDescricaoProduto(produto.getDescricaoProduto());
			newProd.setPrecoUnitario(produto.getPrecoUnitario());

			Produto obj = produtoRepository.save(newProd);

			return obj;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id " + id + " não encontrado"));

	}

}
