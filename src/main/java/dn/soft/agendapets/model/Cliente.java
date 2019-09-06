package dn.soft.agendapets.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="cliente")
public class Cliente {
	
	@Id
	private Integer id;
	private String nome;
	private String cpf;
	
	@OneToMany(mappedBy="dono")
	private List<Pet> pets;
	
	
}
