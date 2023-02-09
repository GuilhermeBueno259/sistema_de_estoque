package com.controle_de_estoque.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemPedidoCliente extends Item {

	private int pedidoClienteId;

	public ItemPedidoCliente() {
	}

	public ItemPedidoCliente(int idDoProduto, int quantidade) {
		super(idDoProduto, quantidade);
	}

	public ItemPedidoCliente(int id, int quantidade, int pedidoClienteId, int idProduto) {
		super(id, idProduto, quantidade);
		this.pedidoClienteId = pedidoClienteId;
	}
}
