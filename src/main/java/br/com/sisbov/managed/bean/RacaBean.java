package br.com.sisbov.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.sisbov.dao.impl.RacaDao;
import br.com.sisbov.persistence.Propriedade;
import br.com.sisbov.persistence.Raca;


@ManagedBean
@SessionScoped
public class RacaBean {
	
	Raca raca = new Raca();
	
	public Raca getRaca() {
		return raca;
	}
	
	public String inserir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			RacaDao dao = new RacaDao();
			dao.save(raca);
			
			context.addMessage(null,
					new FacesMessage("Inserção finalizada."));
					raca = new Raca();
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage("Erro ao inserir: " + e.getMessage()));
					
		}
		return "raca";
	} 

}