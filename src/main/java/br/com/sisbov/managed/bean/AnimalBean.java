package br.com.sisbov.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.sisbov.dao.impl.AnimalDao;
import br.com.sisbov.persistence.Animal;



@ManagedBean
public class AnimalBean {

	private Animal animal = new Animal();
	

	public void inserirAnimal() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			AnimalDao dao = new AnimalDao();
			dao.save(animal);

			animal = new Animal();
			context.addMessage(null, new FacesMessage("inserido com sucesso"));
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage("Erro ao inserir: " + e.getMessage()));
		}
	}

	
	

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	

}
