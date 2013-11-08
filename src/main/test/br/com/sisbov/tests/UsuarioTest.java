package br.com.sisbov.tests;

import br.com.sisbov.dao.impl.UsuarioDao;
import br.com.sisbov.persistence.Usuario;

public class UsuarioTest {
	
	public void test(){
		Usuario usuario = new Usuario();
		UsuarioDao dao = new UsuarioDao();
		
		
		usuario.setLogin(fernando);
		usuario.setSenha(fernandomenolli);
		
		
	}
	
	
}
