package com.controle_de_estoque.model.entity;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoFornecedor extends Pedido {
	private int fornecedorId;
	private List<ItemPedidoFornecedor> listaDeItens;

	public PedidoFornecedor() {
	}

	public PedidoFornecedor(int fornecedorId) {
		this.fornecedorId = fornecedorId;
	}

	public PedidoFornecedor(int id, float precoTotal, String nfE, int fornecedorId) {
		super(id, precoTotal, nfE);
		this.fornecedorId = fornecedorId;
	}

	public PedidoFornecedor(float precoTotal, String nfE, int fornecedorId) {
		super(precoTotal, nfE);
		this.fornecedorId = fornecedorId;
	}

	public PedidoFornecedor(int id, float precoTotal, Date data, String nfe, int fornecedorId) {
		super(id, precoTotal, data, nfe);
		this.fornecedorId = fornecedorId;
	}

	public PedidoFornecedor(float precoTotal) {
		super(precoTotal);
	}
}
