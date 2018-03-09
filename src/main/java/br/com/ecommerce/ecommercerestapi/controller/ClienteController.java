package br.com.ecommerce.ecommercerestapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.ecommercerestapi.exception.ResourceNotFoundException;
import br.com.ecommerce.ecommercerestapi.model.Cliente;
import br.com.ecommerce.ecommercerestapi.repository.ClienteRepository;

@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	// Obtém todos os clientes.
	@GetMapping("/clientes")
	public List<Cliente> obtemClientes() {
		return clienteRepository.findAll();
	}
	
	
	// Cria um novo cliente.
	@PostMapping("/clientes")
	public Cliente criaCliente(@Valid @RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	
	// Obtém um cliente específico.
	@GetMapping("/clientes/{id}")
	public Cliente obtemClientePorId(@PathVariable(value="id") Long idCliente) {
		return clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", idCliente));
	}
	
	
	// Altera um cliente específico.
	@PutMapping("/clientes/{id}")
	public Cliente alteraCliente(@PathVariable(value="id") Long idCliente,
			@Valid @RequestBody Cliente clienteComAlteracoes) {
		
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", idCliente));
		
		cliente.setNome(clienteComAlteracoes.getNome());
		cliente.setCpf(clienteComAlteracoes.getCpf());
		cliente.setEndereco(clienteComAlteracoes.getEndereco());
		cliente.setEstado(clienteComAlteracoes.getEstado());
		cliente.setMunicipio(clienteComAlteracoes.getMunicipio());
		cliente.setTelefone(clienteComAlteracoes.getTelefone());
		cliente.setEmail(clienteComAlteracoes.getEmail());
		cliente.setSenha(clienteComAlteracoes.getSenha());
		
		Cliente clienteAlterado = clienteRepository.save(cliente);
		
		return clienteAlterado;

	}
	
	
	// Exclui um cliente específico.
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> excluiCliente(@PathVariable(value="id") Long idCliente) {
		
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", idCliente));
		
		clienteRepository.delete(cliente);
		
		return ResponseEntity.ok().build();
	}
	
}
