package dn.soft.agendapets.datafetchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dn.soft.agendapets.model.Servico;
import dn.soft.agendapets.repository.ServicoRepository;
import graphql.schema.DataFetcher;

@Component
@SuppressWarnings("rawtypes")
public class ServicoDataFetchers {
	
	@Autowired
	ServicoRepository servicoRepository;

    
	public DataFetcher getServicoById() {
        return dataFetchingEnvironment -> {
            Integer servicoId = Integer.parseInt(dataFetchingEnvironment.getArgument("id"));
            return servicoRepository.findById(servicoId);
        };
    }

    public DataFetcher getTodosServicos() {
        return dataFetchingEnvironment -> {
            return servicoRepository.findAll();
        };
    }
    
    public DataFetcher addServico() {
    	return dataFetchingEnvironment -> {            
            String nome = dataFetchingEnvironment.getArgument("nome");
            Double preco = dataFetchingEnvironment.getArgument("preco");
            String descricao = dataFetchingEnvironment.getArgument("descricao");
            
            Servico servico = new Servico();
            servico.setNome(nome);
            servico.setPreco(preco.floatValue());
            servico.setDescricao(descricao);
            
            return servicoRepository.save(servico);
        };
    }
    
    public DataFetcher updateServico() {
    	return dataFetchingEnvironment -> {
    		Integer servicoId = Integer.parseInt(dataFetchingEnvironment.getArgument("id"));
    		String nome = dataFetchingEnvironment.getArgument("nome");
            Double preco = dataFetchingEnvironment.getArgument("preco");
            String descricao = dataFetchingEnvironment.getArgument("descricao");
            
            Servico servico = new Servico();
            servico.setId(servicoId);
            servico.setNome(nome);
            servico.setPreco(preco.floatValue());
            servico.setDescricao(descricao);
            
            return servicoRepository.save(servico);
        };
    }
    
    public DataFetcher deleteServico() {
    	return dataFetchingEnvironment -> {
    		Integer servicoId = Integer.parseInt(dataFetchingEnvironment.getArgument("id"));
    		servicoRepository.deleteById(servicoId);
    		
            return servicoId;
        };
    }
}
