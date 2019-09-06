package dn.soft.agendapets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dn.soft.agendapets.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
	
	

}
