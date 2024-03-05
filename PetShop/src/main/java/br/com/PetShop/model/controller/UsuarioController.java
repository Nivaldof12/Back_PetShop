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

import br.com.PetShop.model.domain.Usuario;
import br.com.PetShop.model.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    // Endpoint para autenticar um usuário
    @PostMapping("/autenticar")
    public ResponseEntity<Usuario> autenticarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioAutenticado = usuarioService.autenticar(usuario);
        if (usuarioAutenticado != null) {
            return ResponseEntity.ok(usuarioAutenticado);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    // Endpoint para incluir um novo usuário
    @PostMapping("/incluir")
    public ResponseEntity<Usuario> incluirUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.incluir(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }
    
    // Endpoint para excluir um usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Integer id) {
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
    
    // Endpoint para obter todos os usuários
    @GetMapping
    public ResponseEntity<Collection<Usuario>> obterTodosUsuarios() {
        Collection<Usuario> usuarios = usuarioService.obterLista();
        return ResponseEntity.ok(usuarios);
    }
    
    // Endpoint para obter um usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterUsuarioPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioService.obterUsuarioPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Endpoint para obter um usuário por e-mail
    @GetMapping("/buscarPorEmail/{email}")
    public ResponseEntity<Usuario> obterUsuarioPorEmail(@PathVariable String email) {
        Usuario usuario = usuarioService.obterUsuarioPorEmail(email);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Endpoint para editar um usuário existente
    @PutMapping("/editar/{id}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable Integer id, @Validated @RequestBody Usuario usuarioAlterado) {
        // Verificar se o usuário existe
        Usuario usuarioExistente = usuarioService.obterUsuarioPorId(id);
        if (usuarioExistente != null) {
            // Atualiza os atributos do usuário existente com os valores do usuário alterado
            usuarioExistente.setNome(usuarioAlterado.getNome());
            usuarioExistente.setEmail(usuarioAlterado.getEmail());
            usuarioExistente.setSenha(usuarioAlterado.getSenha());
            usuarioExistente.setCelular(usuarioAlterado.getCelular());

            // Salva as alterações no banco de dados
            usuarioService.incluir(usuarioExistente);
            
            // Retorna o usuário alterado como JSON com status 200 (OK)
            return ResponseEntity.ok(usuarioExistente);
        } else {
            // Se o usuário não existir, retorna um status 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
    }
}