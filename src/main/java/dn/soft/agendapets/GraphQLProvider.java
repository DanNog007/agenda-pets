package dn.soft.agendapets;

import java.io.IOException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import dn.soft.agendapets.datafetchers.AtendimentoDataFetchers;
import dn.soft.agendapets.datafetchers.ClienteDataFetchers;
import dn.soft.agendapets.datafetchers.ComponenteDataFetchers;
import dn.soft.agendapets.datafetchers.PetDataFetchers;
import dn.soft.agendapets.datafetchers.ServicoDataFetchers;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Component
public class GraphQLProvider {
	
	private GraphQL graphQL;
	
	@Autowired
	ClienteDataFetchers clienteDataFetchers;
	@Autowired
	PetDataFetchers petDataFetchers;
	@Autowired
	ServicoDataFetchers servicoDataFetchers;
	@Autowired
	AtendimentoDataFetchers atendimentoDataFetchers;
	@Autowired
	ComponenteDataFetchers componenteDataFetchers;
	
	@PostConstruct
	private void loadSchema() throws IOException {
		//get the schema
		URL url = Resources.getResource("schema.graphql");
        String sdl = Resources.toString(url, Charsets.UTF_8);
		//parse schema
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
		RuntimeWiring wiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();
	}

	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", typeWiring -> typeWiring
						.dataFetcher("clientes", clienteDataFetchers.getTodosClientes())
						.dataFetcher("cliente", clienteDataFetchers.getClienteById())
						.dataFetcher("pets", petDataFetchers.getTodosPets())
						.dataFetcher("pet", petDataFetchers.getPetById())
						.dataFetcher("servicos", servicoDataFetchers.getTodosServicos())
						.dataFetcher("servico", servicoDataFetchers.getServicoById())
						.dataFetcher("atendimentos", atendimentoDataFetchers.getTodosAtendimentos())
						.dataFetcher("atendimento", atendimentoDataFetchers.getAtendimentoById())
						.dataFetcher("componentes", componenteDataFetchers.getTodosComponentes())
						.dataFetcher("componente", componenteDataFetchers.getComponenteById()))
				.type("Mutation", typeWiring -> typeWiring
						.dataFetcher("adicionarCliente", clienteDataFetchers.addCliente())
						.dataFetcher("atualizarCliente", clienteDataFetchers.updateCliente())
						.dataFetcher("deletarCliente", clienteDataFetchers.deleteCliente())
						.dataFetcher("adicionarPet", petDataFetchers.addPet())
						.dataFetcher("atualizarPet", petDataFetchers.updatePet())
						.dataFetcher("deletarPet", petDataFetchers.deletePet())
						.dataFetcher("adicionarServico", servicoDataFetchers.addServico())
						.dataFetcher("atualizarServico", servicoDataFetchers.updateServico())
						.dataFetcher("deletarServico", servicoDataFetchers.deleteServico())
						.dataFetcher("adicionarAtendimento", atendimentoDataFetchers.addAtendimento())
						.dataFetcher("atualizarAtendimento", atendimentoDataFetchers.updateAtendimento())
						.dataFetcher("deletarAtendimento", atendimentoDataFetchers.deleteAtendimento())
						.dataFetcher("adicionarComponente", componenteDataFetchers.addComponente())
						.dataFetcher("adicionarComponentes", componenteDataFetchers.addComponentes()))
				.build();
	}
	
	@Bean
	public GraphQL getGraphQL() {
		return graphQL;
	}

}
