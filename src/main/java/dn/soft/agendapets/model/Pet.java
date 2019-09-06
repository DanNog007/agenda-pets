package dn.soft.agendapets.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="pet")
public class Pet {
	
	@Id
	private Integer id;
	private String nome;
	private String tipo;
	@Column(name="observacao")
	private String observacoes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dono")
	private Cliente dono;

}
