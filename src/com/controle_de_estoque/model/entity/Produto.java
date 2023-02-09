package com.controle_de_estoque.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Produto {

	private int id;
	private String descricao;
	private String categoria;
	private float preco;
	private int quantidade;

	public Produto() {
	}

	public Produto(String descricao, String categoria, float preco, int quantidade) {
		this.descricao = descricao;
		this.categoria = categoria;
		this.preco = preco;
		this.quantidade = quantidade;
	}
}
