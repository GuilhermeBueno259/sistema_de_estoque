package com.controle_de_estoque.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Telefone {

	private int id;
	private String numero;
	private String tipo;

	public Telefone() {
	}

	public Telefone(String numero, String tipo) {
		this.numero = numero;
		this.tipo = tipo;
	}
}
