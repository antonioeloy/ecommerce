package br.com.ecommerce.ecommercerestapi.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ecommerce.ecommercerestapi.model.Cliente;
import br.com.ecommerce.ecommercerestapi.repository.ClienteRepository;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

	private ClienteRepository clienteRepository;
	
	public UserDetailsServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(username);
        if (cliente == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(cliente.getEmail(), cliente.getSenha(), emptyList());
    }
	
}
