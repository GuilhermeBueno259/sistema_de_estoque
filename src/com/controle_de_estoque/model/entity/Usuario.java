package com.controle_de_estoque.model.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Usuario {

	private int id;
	private String email;
	private String senha;

	public Usuario() {
	}

	public Usuario(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}
}