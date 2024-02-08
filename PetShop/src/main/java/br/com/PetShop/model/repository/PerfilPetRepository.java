package br.com.PetShop.model.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.PetShop.model.domain.PerfilPet;
import br.com.PetShop.model.domain.Usuario;

@Repository
public interface PerfilPetRepository extends CrudRepository<PerfilPet, Integer> {
    
    Collection<PerfilPet> findByUsuario(Usuario usuario);
    
}
