package dn.soft.agendapets;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import dn.soft.agendapets.datafetchers.ClienteDataFetchers;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Component
public class GraphQLService {
	
	@Value("classpath:schema.graphql")
	Resource resource;
	
	private GraphQL graphQL;
	
	@Autowired
	ClienteDataFetchers clienteDataFetchers;
	
	@PostConstruct
	private void loadSchema() throws IOException {
		//get the schema
		File schemaFile = resource.getFile();
		//parse schema
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();
	}

	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", typeWiring -> typeWiring
						.dataFetcher("clientes", clienteDataFetchers.getTodosClientes())
						.dataFetcher("cliente", clienteDataFetchers.getClienteById()))
				.type("Mutation", typeWiring -> typeWiring
						.dataFetcher("adicionarCliente", clienteDataFetchers.addCliente())
						.dataFetcher("atualizarCliente", clienteDataFetchers.updateCliente())
						.dataFetcher("deletarCliente", clienteDataFetchers.deleteCliente()))
				.build();
	}
	
	@Bean
	public GraphQL getGraphQL() {
		return graphQL;
	}

}
