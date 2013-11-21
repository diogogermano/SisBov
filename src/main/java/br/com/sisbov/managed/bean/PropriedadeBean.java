package br.com.sisbov.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.sisbov.dao.impl.PropriedadeDao;
import br.com.sisbov.persistence.Animal;
import br.com.sisbov.persistence.Propriedade;


@ManagedBean
@SessionScoped
public class PropriedadeBean {
	
	Propriedade propriedade = new Propriedade();
	
	public Propriedade getPropriedade() {
		return propriedade;
	}
	
	public String inserir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			PropriedadeDao dao = new PropriedadeDao();
			dao.save(propriedade);
			
			context.addMessage(null,
					new FacesMessage("Inserção finalizada."));
					propriedade = new Propriedade();
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage("Erro ao inserir: " + e.getMessage()));
					
		}
		return "propriedade";
	} 

}