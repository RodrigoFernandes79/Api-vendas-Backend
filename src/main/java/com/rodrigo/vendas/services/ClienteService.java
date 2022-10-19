package com.rodrigo.vendas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.rodrigo.vendas.domain.Cliente;
import com.rodrigo.vendas.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente salvar(Cliente cliente) {
		Cliente cli = clienteRepository.findBycpf(cliente.getCpf());

		if (cli != null) {
			throw new DataIntegrityViolationException("CPF já existe no Banco de Dados");
		} else {
			return clienteRepository.save(cliente);
		}
	}

	public Cliente listarClientePorId(Integer id) {

		Optional<Cliente> obj = clienteRepository.findById(id);

		return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

	}

	public void deletarCliente(Integer id) {
		try {
			Cliente obj = clienteRepository.findById(id)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

			clienteRepository.delete(obj);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Cliente não pode ser deletado.Possui pedidos associados");
		}
	}

	public Cliente atualizarCliente(Integer id, Cliente cliente) {
		Cliente client = clienteRepository.findBycpf(cliente.getCpf());

		if (client != null && client.getId() != id) {
			throw new DataIntegrityViolationException("CPF Já existe no banco de dados");
		}
		return clienteRepository.findById(id).map(cli -> {
			cli.getId();
			cli.setNome(cliente.getNome());
			cli.setCpf(cliente.getCpf());

			Cliente obj = clienteRepository.save(cli);

			return obj;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
	}

	public Page<Cliente> listarClientesPorNome(String nome, Integer page, Integer linesPerPages, String orderBy,
			String direction) {

		PageRequest pgRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction), orderBy);

		Page<Cliente> nomeCliente = clienteRepository.findByNomeIgnoreCaseContaining(nome, pgRequest);
		return nomeCliente;
	}

}
