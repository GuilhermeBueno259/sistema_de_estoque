package com.controle_de_estoque.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemPedidoFornecedor extends Item {

	private int pedidoFornecedorId;

	public ItemPedidoFornecedor() {
	}

	public ItemPedidoFornecedor(int produtoId, int quantidade) {
		super(produtoId, quantidade);
	}

	public ItemPedidoFornecedor(int id, int quantidade, int pedidoFornecedorId, int produtoId) {
		super(id, produtoId, quantidade);
		this.pedidoFornecedorId = pedidoFornecedorId;
	}
}
