package br.com.PetShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.PetShop.model.domain.Usuario;
import br.com.PetShop.model.service.UsuarioService;

@Order(1)
@Component
public class UsuarioLoader implements ApplicationRunner {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Usuario userAdmin = new Usuario("Administrador ", "admin@admin.com", "123456");
		userAdmin.setCelular("81994685930");
		userAdmin.setAdmin(true);

		usuarioService.incluir(userAdmin);
		
		System.out.println("Inclusão do administrador "+userAdmin.getNome()+" realizada com sucesso!!!");

	}
}