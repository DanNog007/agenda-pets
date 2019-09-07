package dn.soft.agendapets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dn.soft.agendapets.model.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Integer> {
	
}
