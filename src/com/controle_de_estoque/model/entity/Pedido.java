package com.controle_de_estoque.model.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Pedido {

	protected int id;
	protected float precoTotal;
	protected Date data;
	protected String nfE;

	public Pedido() {
	}

	public Pedido(float precoTotal) {
		this.precoTotal = precoTotal;
	}

	public Pedido(float precoTotal, String nfE) {
		this.precoTotal = precoTotal;
		this.nfE = nfE;
	}

	public Pedido(int id, float precoTotal, String nfE) {
		this.id = id;
		this.precoTotal = precoTotal;
		this.nfE = nfE;
	}
}
