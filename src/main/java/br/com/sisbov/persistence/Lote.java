package br.com.sisbov.persistence;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Lote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany
	private List<Animal> animal;
	
	@OneToOne
	private Piquete piquete;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Animal getAnimal(int pos) {
		return animal.get(pos);
	}

	public void setAnimal(Animal anim) {
		this.animal.add(anim);
	}

	public Piquete getPiquete() {
		return piquete;
	}

	public void setPiquete(Piquete piquete) {
		this.piquete = piquete;
	}
	
	
	

}
