package br.com.sisbov.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.sisbov.dao.impl.PiqueteDao;
import br.com.sisbov.persistence.Piquete;



@ManagedBean
public class PiqueteBean {
	
	private Piquete piquete = new Piquete();

	public Piquete getPiquete() {
		return piquete;
	}
	
	public void inserir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			PiqueteDao dao = new PiqueteDao();
			dao.save(piquete);
			
			context.addMessage(null,
					new FacesMessage("Inserção finalizada."));
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage("Erro ao inserir: " + e.getMessage()));
		}
	} 



}
