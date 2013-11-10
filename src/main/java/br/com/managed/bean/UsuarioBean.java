package br.com.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.sisbov.dao.impl.UsuarioDao;
import br.com.sisbov.persistence.Usuario;


@ManagedBean
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario(){
		return usuario;
	}
	
	public void inserir(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			UsuarioDao dao = new UsuarioDao();
			dao.save(usuario);
			
			context.addMessage(null,
					new FacesMessage("Inserção finalizada."));
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage("Erro ao inserir: " + e.getMessage()));
		}
	} 

}
