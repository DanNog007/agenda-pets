package dn.soft.agendapets.datafetchers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dn.soft.agendapets.model.Cliente;
import dn.soft.agendapets.model.Pet;
import dn.soft.agendapets.repository.ClienteRepository;
import dn.soft.agendapets.repository.PetRepository;
import graphql.schema.DataFetcher;

@Component
@SuppressWarnings("rawtypes")
public class PetDataFetchers {
	
	@Autowired
	PetRepository petRepository;
	@Autowired
	ClienteRepository clienteRepository;

    
	public DataFetcher getPetById() {
        return dataFetchingEnvironment -> {
            Integer petId = Integer.parseInt(dataFetchingEnvironment.getArgument("id"));
            return petRepository.findById(petId);
        };
    }

    public DataFetcher getTodosPets() {
        return dataFetchingEnvironment -> {
            return petRepository.findAll();
        };
    }
    
    public DataFetcher addPet() {
    	return dataFetchingEnvironment -> {
    		String nome = dataFetchingEnvironment.getArgument("nome");
            Integer donoId = (Integer)dataFetchingEnvironment.getArgument("donoId");
            String tipo = dataFetchingEnvironment.getArgument("tipo");
            String observacoes = dataFetchingEnvironment.getArgument("observacoes");
            
            Optional<Cliente> dono = clienteRepository.findById(donoId);
            
            Pet pet = new Pet();
            pet.setNome(nome);
            pet.setDono(dono.get());
            pet.setTipo(tipo);
            pet.setObservacoes(observacoes);
            
            return petRepository.save(pet);
        };
    }
    
    public DataFetcher updatePet() {
    	return dataFetchingEnvironment -> {
    		Integer petId = Integer.parseInt(dataFetchingEnvironment.getArgument("id"));
    		String nome = dataFetchingEnvironment.getArgument("nome");
            Integer donoId = (Integer)dataFetchingEnvironment.getArgument("donoId");
            String tipo = dataFetchingEnvironment.getArgument("tipo");
            String observacoes = dataFetchingEnvironment.getArgument("observacoes");
            
            Optional<Cliente> dono = clienteRepository.findById(donoId);
            
            Pet pet = new Pet();
            pet.setId(petId);
            pet.setNome(nome);
            pet.setDono(dono.get());
            pet.setTipo(tipo);
            pet.setObservacoes(observacoes);
            
            return petRepository.save(pet);
        };
    }
    
    public DataFetcher deletePet() {
    	return dataFetchingEnvironment -> {
    		Integer petId = Integer.parseInt(dataFetchingEnvironment.getArgument("id"));
    		petRepository.deleteById(petId);
    		
            return petId;
        };
    }
}
