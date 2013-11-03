package br.com.sisbov.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private Animal animais[];
	
	@Column
	private Piquete piquete;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Animal[] getAnimais() {
		return animais;
	}

	public void setAnimais(Animal[] animais) {
		this.animais = animais;
	}

	public Piquete getPiquete() {
		return piquete;
	}

	public void setPiquete(Piquete piquete) {
		this.piquete = piquete;
	}
	
	
	

}
