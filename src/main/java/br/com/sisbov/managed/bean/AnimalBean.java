package br.com.sisbov.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.sisbov.dao.impl.AnimalDao;
import br.com.sisbov.persistence.Animal;
import br.com.sisbov.types.Sexo;

@ManagedBean
public class AnimalBean {
	
	private Animal animal = new Animal();

	public Animal getAnimal() {
		return animal;
	}
	
	public void inserir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			AnimalDao dao = new AnimalDao();
			dao.save(animal);
			
			context.addMessage(null,
					new FacesMessage("Inserção finalizada."));
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage("Erro ao inserir: " + e.getMessage()));
		}
	} 

	public Sexo[] getSexoValues() {
		return Sexo.values();
	}

}
