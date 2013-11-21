package br.com.sisbov.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.sisbov.types.Sexo;

@Entity
public class Animal {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	@Column
	private int idade;	
	
	@Column
	private int lote;	
	
	@ManyToOne( targetEntity = Raca.class, fetch=FetchType.LAZY )  
    @JoinColumn(name="id", insertable=false, updatable=false)
	private Raca raca;
	
	@Column
	private double peso;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getLote() {
		return lote;
	}

	public void setLote(int lote) {
		this.lote = lote;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

}
