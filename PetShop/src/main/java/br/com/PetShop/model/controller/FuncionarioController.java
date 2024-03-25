package br.com.PetShop.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.PetShop.model.domain.Funcionario;
import br.com.PetShop.model.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/incluir")
    public ResponseEntity<Funcionario> incluirFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario novoOuAtualizado = funcionarioService.incluirOuAtualizar(funcionario);
        return new ResponseEntity<>(novoOuAtualizado, HttpStatus.CREATED);
    }

}
