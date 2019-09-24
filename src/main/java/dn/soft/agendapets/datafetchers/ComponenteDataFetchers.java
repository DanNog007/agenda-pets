package dn.soft.agendapets.datafetchers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dn.soft.agendapets.model.Componente;
import dn.soft.agendapets.repository.ComponenteRepository;
import graphql.schema.DataFetcher;

@Component
@SuppressWarnings("rawtypes")
public class ComponenteDataFetchers {
	
	@Autowired
	ComponenteRepository componenteRepository;

    
	public DataFetcher getComponenteById() {
        return dataFetchingEnvironment -> {
            Integer componenteId = Integer.parseInt(dataFetchingEnvironment.getArgument("id"));
            return componenteRepository.findById(componenteId);
        };
    }

    public DataFetcher getTodosComponentes() {
        return dataFetchingEnvironment -> {
            return componenteRepository.findAll();
        };
    }
    
    public DataFetcher addComponente() {
    	return dataFetchingEnvironment -> {
    		JSONObject dados = new JSONObject((String)dataFetchingEnvironment.getArgument("jsonString"));
            String nome = !dados.isNull("name") ? dados.getString("name") : null;
            String codigo = !dados.isNull("codigo") ? dados.getString("codigo") : null;
            String titulo = !dados.isNull("titulo") ? dados.getString("titulo") : null;
            
            Componente componente = new Componente();
            componente.setNome(nome);
            componente.setCodigo(codigo);
            componente.setTitulo(titulo);
            componente.setDados(dados.toString());
            return componenteRepository.save(componente);
        };
    }
    
    public DataFetcher addComponentes() {
    	return dataFetchingEnvironment -> {
    		
    		JSONArray dadosArray = new JSONArray((String)dataFetchingEnvironment.getArgument("jsonString"));
    		
    		List<Componente> listaComponentes = new ArrayList<Componente>();
    		
    		for(int i = 0; i < dadosArray.length(); i++) {
    			
    			JSONObject dados = dadosArray.getJSONObject(i);
        		
                String nome = !dados.isNull("name") ? dados.getString("name") : null;
                String codigo = !dados.isNull("codigo") ? dados.getString("codigo") : null;
                String titulo = !dados.isNull("titulo") ? dados.getString("titulo") : null;
                
                Componente componente = new Componente();
                componente.setNome(nome);
                componente.setCodigo(codigo);
                componente.setTitulo(titulo);
                componente.setDados(dados.toString());
                componenteRepository.save(componente);
                listaComponentes.add(componente);    			
    		}
    		
    		return listaComponentes;
        };
    }
}
