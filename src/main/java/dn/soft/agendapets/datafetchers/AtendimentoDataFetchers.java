package dn.soft.agendapets.datafetchers;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dn.soft.agendapets.model.Atendimento;
import dn.soft.agendapets.model.Cliente;
import dn.soft.agendapets.model.Pet;
import dn.soft.agendapets.model.Servico;
import dn.soft.agendapets.repository.AtendimentoRepository;
import dn.soft.agendapets.repository.ClienteRepository;
import dn.soft.agendapets.repository.PetRepository;
import dn.soft.agendapets.repository.ServicoRepository;
import graphql.schema.DataFetcher;

@Component
@SuppressWarnings("rawtypes")
public class AtendimentoDataFetchers {
	
	@Autowired
	AtendimentoRepository atendimentoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	PetRepository petRepository;
	@Autowired
	ServicoRepository servicoRepository;

    
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
            
            Optional<Cliente> cliente = clienteRepository.findById(clienteId);
            
            Optional<Pet> pet = petRepository.findById(petId);
            
            Optional<Servico> servico = servicoRepository.findById(servicoId);
            
            Atendimento atendimento = new Atendimento();
            atendimento.setCliente(cliente.get());
            atendimento.setPet(pet.get());
            atendimento.setServico(servico.get());
            atendimento.setStatus(status);
            atendimento.setObservacoes(observacoes);
            
            Calendar data = Calendar.getInstance();
            atendimento.setData(data);
            
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
            
            Optional<Cliente> cliente = clienteRepository.findById(clienteId);
            
            Optional<Pet> pet = petRepository.findById(petId);
            
            Optional<Servico> servico = servicoRepository.findById(servicoId);
            
            Atendimento atendimento = new Atendimento();
            atendimento.setId(atendimentoId);
            atendimento.setCliente(cliente.get());
            atendimento.setPet(pet.get());
            atendimento.setServico(servico.get());
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
