package br.com.PetShop.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.PetShop.model.domain.Funcionario;
import br.com.PetShop.model.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario incluir(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }
    
    public Funcionario incluirOuAtualizar(Funcionario funcionario) {
        // Verifica se já existe um registro de funcionário no banco de dados
        Funcionario existente = funcionarioRepository.findById(1L).orElse(null);
        
        if (existente == null) {
            // Se não existe, cria um novo registro
            return funcionarioRepository.save(funcionario);
        } else {
            // Se existe, atualiza o registro existente
            existente.setNumeroFuncionarios(funcionario.getNumeroFuncionarios());
            return funcionarioRepository.save(existente);
        }
    }
}
