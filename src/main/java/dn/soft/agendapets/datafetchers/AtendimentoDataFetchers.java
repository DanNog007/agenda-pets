package dn.soft.agendapets.datafetchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dn.soft.agendapets.model.Atendimento;
import dn.soft.agendapets.model.Cliente;
import dn.soft.agendapets.model.Pet;
import dn.soft.agendapets.model.Servico;
import dn.soft.agendapets.repository.AtendimentoRepository;
import graphql.schema.DataFetcher;

@Component
@SuppressWarnings("rawtypes")
public class AtendimentoDataFetchers {
	
	@Autowired
	AtendimentoRepository atendimentoRepository;

    
	public DataFetcher getAtendimentoById() {
        return dataFetchingEnvironment -> {
            Integer atendimentoId = Integer.parseInt(dataFetchingEnvironment.getArgument("id"));
            return atendimentoRepository.findById(atendimentoId);
        };
    }

    public DataFetcher getTodosAtendimentos() {
        return dataFetchingEnvironment -> {
            return atendimentoRepository.findAll();
        };
    }
    
    public DataFetcher addAtendimento() {
    	return dataFetchingEnvironment -> {            
            Integer clienteId = (Integer)dataFetchingEnvironment.getArgument("clienteId");
            Integer petId = (Integer)dataFetchingEnvironment.getArgument("petId");
            Integer servicoId = (Integer)dataFetchingEnvironment.getArgument("servicoId");
            String status = dataFetchingEnvironment.getArgument("status");
            String observacoes = dataFetchingEnvironment.getArgument("observacoes");
            
            Cliente cliente = new Cliente();
            cliente.setId(clienteId);
            
            Pet pet = new Pet();
            pet.setId(petId);
            
            Servico servico = new Servico();
            servico.setId(servicoId);
            
            Atendimento atendimento = new Atendimento();
            atendimento.setCliente(cliente);
            atendimento.setPet(pet);
            atendimento.setServico(servico);
            atendimento.setStatus(status);
            atendimento.setObservacoes(observacoes);
            
            return atendimentoRepository.save(atendimento);
        };
    }
    
    public DataFetcher updateAtendimento() {
    	return dataFetchingEnvironment -> {
    		Integer atendimentoId = Integer.parseInt(dataFetchingEnvironment.getArgument("id"));
    		Integer clienteId = (Integer)dataFetchingEnvironment.getArgument("clienteId");
            Integer petId = (Integer)dataFetchingEnvironment.getArgument("petId");
            Integer servicoId = (Integer)dataFetchingEnvironment.getArgument("servicoId");
            String status = dataFetchingEnvironment.getArgument("status");
            String observacoes = dataFetchingEnvironment.getArgument("observacoes");
            
            Cliente cliente = new Cliente();
            cliente.setId(clienteId);
            
            Pet pet = new Pet();
            pet.setId(petId);
            
            Servico servico = new Servico();
            servico.setId(servicoId);
            
            Atendimento atendimento = new Atendimento();
            atendimento.setId(atendimentoId);
            atendimento.setCliente(cliente);
            atendimento.setPet(pet);
            atendimento.setServico(servico);
            atendimento.setStatus(status);
            atendimento.setObservacoes(observacoes);
            
            return atendimentoRepository.save(atendimento);
        };
    }
    
    public DataFetcher deleteAtendimento() {
    	return dataFetchingEnvironment -> {
    		Integer atendimentoId = Integer.parseInt(dataFetchingEnvironment.getArgument("id"));
    		atendimentoRepository.deleteById(atendimentoId);
    		
            return atendimentoId;
        };
    }
}
