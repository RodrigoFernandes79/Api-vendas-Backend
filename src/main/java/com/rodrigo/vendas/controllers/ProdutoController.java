package com.rodrigo.vendas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigo.vendas.domain.Produto;
import com.rodrigo.vendas.services.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	
	public ResponseEntity<List<Produto>> listarProdutos(){
		List<Produto> obj = produtoService.listarProdutos();
		
		return ResponseEntity.ok().body(obj);
	}
	
	
	@PostMapping
	public ResponseEntity<Produto> inserirProduto(@RequestBody Produto produto){
		
		Produto obj = produtoService.inserirProduto(produto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> listarProdutoPorId(@PathVariable Integer id){
	
		Produto obj = produtoService.listarProdutoPorId(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/descricao")
	public ResponseEntity<List<Produto>> listarProdutoPorDescricao(
									@RequestParam(value="descricao", required=false) String descricaoProduto){
		List<Produto> obj = produtoService.listarProdutoPorDescricao(descricaoProduto);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarProdutoPorId(@PathVariable Integer id) {
		
		 produtoService.deletarProdutoPorId(id);
		
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizarProdutoPorId(@PathVariable Integer id,
														 @RequestBody Produto produto){
		
		Produto prod = produtoService.atualizarProdutoPorId(id, produto);
		
		return ResponseEntity.ok().body(prod);
	}
	

}
