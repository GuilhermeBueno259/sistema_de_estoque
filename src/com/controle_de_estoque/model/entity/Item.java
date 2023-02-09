package com.controle_de_estoque.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Item {

	protected int id;
	protected int produtoId;
	protected int quantidade;

	public Item() {
	}

	public Item(int produtoId, int quantidade) {
		this.produtoId = produtoId;
		this.quantidade = quantidade;
	}
}
