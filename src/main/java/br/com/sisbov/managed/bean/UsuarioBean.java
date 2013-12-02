package br.com.sisbov.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.sisbov.dao.impl.UsuarioDao;
import br.com.sisbov.persistence.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioBean {

    private Usuario usuario = new Usuario();

    private UsuarioDao dao = new UsuarioDao();

    public Usuario getUsuario() {
    	return usuario;
    }

    public void setUsuario(Usuario usuario) {
    	this.usuario = usuario;
    }

    public String logar() {
	if (dao.logar(usuario.getLogin(), usuario.getSenha())) {
	    return "index";
	} else {
	    FacesContext context = FacesContext.getCurrentInstance();
	    context.addMessage(null,
		    new FacesMessage("Login e senha inválidos"));
	    return "login";
	}
    }

    public boolean isLogado() {
    	return usuario.getLogin() != null;
    }

    public String logoff() {
    	this.usuario = new Usuario();
    	return "login";
    }
}
