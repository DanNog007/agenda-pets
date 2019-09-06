package dn.soft.agendapets.datafetchers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dn.soft.agendapets.model.Cliente;
import dn.soft.agendapets.repository.ClienteRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class ClienteDataFetcher implements DataFetcher<Cliente> {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Override
	public Cliente get(DataFetchingEnvironment environment) throws Exception {
		String id = environment.getArgument("id");
		return clienteRepository.getOne(id);
	}

}
