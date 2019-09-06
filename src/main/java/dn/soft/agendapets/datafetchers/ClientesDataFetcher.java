package dn.soft.agendapets.datafetchers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dn.soft.agendapets.model.Cliente;
import dn.soft.agendapets.repository.ClienteRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class ClientesDataFetcher implements DataFetcher<List<Cliente>> {
	
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public List<Cliente> get(DataFetchingEnvironment environment) throws Exception {
		return clienteRepository.findAll();
	}

}
