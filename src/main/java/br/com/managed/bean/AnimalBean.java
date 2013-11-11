package br.com.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.sisbov.dao.impl.AnimalDao;
import br.com.sisbov.persistence.Animal;




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
					new FacesMessage("Inser��o finalizada."));
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage("Erro ao inserir: " + e.getMessage()));
		}
	} 

	public Sexo[] getUfValues() {
		return Uf.values();
	}

}
