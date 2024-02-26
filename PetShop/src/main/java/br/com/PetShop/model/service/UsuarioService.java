package br.com.PetShop.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.PetShop.model.domain.Usuario;
import br.com.PetShop.model.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario autenticar(Usuario usuario) {
		return usuarioRepository.autenticacao(usuario.getEmail(), usuario.getSenha());
	}

	public Usuario incluir(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public void excluir(Integer key) {
		usuarioRepository.deleteById(key);
	}
	
	public Collection<Usuario> obterLista(){
		return (Collection<Usuario>) usuarioRepository.findAll();
	}
	
    public Usuario obterUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }
    
    public Usuario obterUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    
}
