package br.com.sisbov.types;

public enum Sexo {
	
	MACHO("Macho"), FEMEA("F�mea");
	
	private String descricao;

	Sexo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
