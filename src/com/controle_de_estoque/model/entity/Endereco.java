package com.controle_de_estoque.model.entity;

import lombok.Data;

@Data
public class Endereco {

	private int id;
	private String rua;
	private String cidade;
	private String uf;

	public Endereco() {
	}

	public Endereco(String rua, String cidade, String uf) {
		this.rua = rua;
		this.cidade = cidade;
		this.uf = uf;
	}
}
