package dn.soft.agendapets.datafetchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dn.soft.agendapets.model.Cliente;
import dn.soft.agendapets.repository.ClienteRepository;
import graphql.schema.DataFetcher;

@Component
@SuppressWarnings("rawtypes")
public class ClienteDataFetchers {
	
	@Autowired
	ClienteRepository clienteRepository;

    
	public DataFetcher getClienteById() {
        return dataFetchingEnvironment -> {
            Integer clienteId = Integer.parseInt(dataFetchingEnvironment.getArgument("id"));
            return clienteRepository.findById(clienteId);
        };
    }

    public DataFetcher getTodosClientes() {
        return dataFetchingEnvironment -> {
            return clienteRepository.findAll();
        };
    }
    
    public DataFetcher addCliente() {
    	return dataFetchingEnvironment -> {
            String clienteNome = dataFetchingEnvironment.getArgument("nome");
            String clienteCpf = dataFetchingEnvironment.getArgument("cpf");
            Cliente cliente = new Cliente();
            cliente.setNome(clienteNome);
            cliente.setCpf(clienteCpf);
            return clienteRepository.save(cliente);
        };
    }
    
    public DataFetcher updateCliente() {
    	return dataFetchingEnvironment -> {
    		Integer clienteId = Integer.parseInt(dataFetchingEnvironment.getArgument("id"));
            String clienteNome = dataFetchingEnvironment.getArgument("nome");
            String clienteCpf = dataFetchingEnvironment.getArgument("cpf");
            
            Cliente cliente = new Cliente();
            cliente.setId(clienteId);
            cliente.setNome(clienteNome);
            cliente.setCpf(clienteCpf);
            return clienteRepository.save(cliente);
        };
    }
    
    public DataFetcher deleteCliente() {
    	return dataFetchingEnvironment -> {
    		Integer clienteId = Integer.parseInt(dataFetchingEnvironment.getArgument("id"));
    		clienteRepository.deleteById(clienteId);
    		
            return clienteId;
        };
    }
}
