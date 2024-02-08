package br.com.PetShop.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.PetShop.model.domain.PerfilPet;
import br.com.PetShop.model.domain.Usuario;
import br.com.PetShop.model.repository.PerfilPetRepository;
import java.util.Optional;

@Service
public class PerfilPetService {

    @Autowired
    private PerfilPetRepository perfilPetRepository;

    public PerfilPet incluir(PerfilPet perfilPet) {
        return perfilPetRepository.save(perfilPet);
    }

    public void excluir(Integer id) {
        perfilPetRepository.deleteById(id);
    }

    public Collection<PerfilPet> obterPerfisPorUsuario(Usuario usuario) {
        return perfilPetRepository.findByUsuario(usuario);
    }
    
    public PerfilPet editar(PerfilPet perfilPet) {
        return perfilPetRepository.save(perfilPet);
    }
    
    public PerfilPet obterPerfilPorId(Integer id) {
        return perfilPetRepository.findById(id).orElse(null);
    }
    
    public PerfilPet obterPerfilPetPorId(Integer id) {
        Optional<PerfilPet> perfilPetOptional = perfilPetRepository.findById(id);
        return perfilPetOptional.orElse(null);
    }
}
