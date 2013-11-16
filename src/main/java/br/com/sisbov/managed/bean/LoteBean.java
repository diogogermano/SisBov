package br.com.sisbov.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.sisbov.dao.impl.LoteDao;
import br.com.sisbov.persistence.Lote;


@ManagedBean
public class LoteBean {
	
	private Lote lote = new Lote();

	public Lote getLote() {
		return lote;
	}
	
	public void inserir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			LoteDao dao = new LoteDao();
			dao.save(lote);
			
			context.addMessage(null,
					new FacesMessage("Inserção finalizada."));
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage("Erro ao inserir: " + e.getMessage()));
		}
	} 



}
