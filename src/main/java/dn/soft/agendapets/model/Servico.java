package dn.soft.agendapets.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="servico")
public class Servico {
	
	@Id
	private Integer id;
	private String nome;
	private Float preco;
	private String descricao;
	
	public Servico() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
