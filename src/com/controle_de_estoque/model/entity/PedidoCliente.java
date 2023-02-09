package com.controle_de_estoque.model.entity;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PedidoCliente extends Pedido {

	private int clienteId;
	private List<ItemPedidoCliente> listaDeItens;

	public PedidoCliente() {
	}

	public PedidoCliente(float precoTotal) {
		super(precoTotal);
	}

	public PedidoCliente(int clienteId) {
		this.clienteId = clienteId;
	}

	public PedidoCliente(float precoTotal, String nfE, int clienteId) {
		super(precoTotal, nfE);
		this.clienteId = clienteId;
	}

	public PedidoCliente(int id, float precoTotal, String nfE, int clienteId) {
		super(id, precoTotal, nfE);
		this.clienteId = clienteId;
	}

	public PedidoCliente(int id, float precoTotal, Date data, String nfE, int clienteId) {
		super(id, precoTotal, data, nfE);
		this.clienteId = clienteId;
	}
}
