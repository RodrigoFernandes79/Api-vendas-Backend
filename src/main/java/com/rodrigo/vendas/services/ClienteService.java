package com.rodrigo.vendas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigo.vendas.domain.Cliente;
import com.rodrigo.vendas.repositories.ClienteRepository;
import com.rodrigo.vendas.services.exceptions.DataViolationException;
import com.rodrigo.vendas.services.exceptions.EntityNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente salvar(Cliente cliente) {
		Cliente cli = clienteRepository.findBycpf(cliente.getCpf());

		if (cli != null) {
			throw new DataViolationException("CPF já existe no Banco de Dados");
		} else {
			return clienteRepository.save(cliente);
		}
	}

	public Cliente listarClientePorId(Integer id) {

		Optional<Cliente> obj = clienteRepository.findById(id);

		return obj.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

	}

	public void deletarCliente(Integer id) {
		try {
			Cliente obj = clienteRepository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException("Cliente não Encontrado!"));

			clienteRepository.delete(obj);

		} catch (DataViolationException e) {
			throw new DataViolationException("Cliente não pode ser deletado.Possui pedidos associados");
		}
	}

	public Cliente atualizarCliente(Integer id, Cliente cliente) {
		Cliente client = clienteRepository.findBycpf(cliente.getCpf());

		if (client != null && client.getId() != id) {
			throw new DataViolationException("CPF Já existe no banco de dados");
		}
		return clienteRepository.findById(id).map(cli -> {
			cli.getId();
			cli.setNome(cliente.getNome());
			cli.setCpf(cliente.getCpf());

			Cliente obj = clienteRepository.save(cli);

			return obj;
		}).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado!"));
	}

	public Page<Cliente> listarClientesPorNome(String nome, Integer page, Integer linesPerPages, String orderBy,
			String direction) {

		PageRequest pgRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction), orderBy);

		Page<Cliente> nomeCliente = clienteRepository.findByNomeIgnoreCaseContaining(nome, pgRequest);
		return nomeCliente;
	}

}
