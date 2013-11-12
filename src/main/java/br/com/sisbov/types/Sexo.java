package br.com.sisbov.types;



public enum Sexo {

	MACHO("Macho"), FEMEA("Femea");

	Sexo(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

}
