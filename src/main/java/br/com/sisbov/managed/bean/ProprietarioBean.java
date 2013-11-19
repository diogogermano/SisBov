package br.com.sisbov.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.sisbov.dao.impl.ProprietarioDao;
import br.com.sisbov.persistence.Animal;
import br.com.sisbov.persistence.Proprietario;


@ManagedBean
@SessionScoped
public class ProprietarioBean {
	
	Proprietario proprietario = new Proprietario();
	
	public Proprietario getProprietario() {
		return proprietario;
	}
	
	public String inserir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			ProprietarioDao dao = new ProprietarioDao();
			dao.save(proprietario);
			
			context.addMessage(null,
					new FacesMessage("Inserção finalizada."));
					proprietario = new Proprietario();
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage("Erro ao inserir: " + e.getMessage()));
					
		}
		return "proprietario";
	} 

}
