package br.com.PetShop.model.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.PetShop.model.domain.PerfilPet;
import br.com.PetShop.model.domain.Usuario;
import br.com.PetShop.model.service.PerfilPetService;
import br.com.PetShop.model.service.UsuarioService;

@RestController
@RequestMapping("/api/perfilpet")
public class PerfilPetController {

    @Autowired
    private PerfilPetService perfilPetService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/incluir")
    public ResponseEntity<PerfilPet> incluirPerfilPet(@RequestBody PerfilPet perfilPet) {
        // Verificar se o usuário existe
        Usuario usuario = usuarioService.obterUsuarioPorId(perfilPet.getUsuario().getId());
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        // Definir o usuário no perfil de pet
        perfilPet.setUsuario(usuario);

        // Incluir o perfil de pet
        PerfilPet novoPerfilPet = perfilPetService.incluir(perfilPet);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPerfilPet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPerfilPet(@PathVariable Integer id) {
        perfilPetService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Collection<PerfilPet>> obterPerfisPorUsuario(@PathVariable Integer id) {
        Usuario usuario = usuarioService.obterUsuarioPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        Collection<PerfilPet> perfis = perfilPetService.obterPerfisPorUsuario(usuario);
        return ResponseEntity.ok(perfis);
    }
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarPerfilPet(@PathVariable Integer id, @Validated @RequestBody PerfilPet perfilPetAlterado) {
        // Verificar se o perfil de pet existe
        PerfilPet perfilPetExistente = perfilPetService.obterPerfilPorId(id);
        if (perfilPetExistente != null) {
            // Atualiza os atributos do perfil de pet existente com os valores do perfil de pet alterado
            perfilPetExistente.setNome(perfilPetAlterado.getNome());
            perfilPetExistente.setRaca(perfilPetAlterado.getRaca());
            perfilPetExistente.setIdade(perfilPetAlterado.getIdade());

            // Salva as alterações no banco de dados
            perfilPetService.incluir(perfilPetExistente);
            return ResponseEntity.ok("Perfil de pet alterado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
