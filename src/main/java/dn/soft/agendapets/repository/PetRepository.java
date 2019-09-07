package dn.soft.agendapets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dn.soft.agendapets.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer> {
	
}
