package com.controle_de_estoque.controle.telas;

import java.util.List;

import com.controle_de_estoque.model.dao.ClienteDao;
import com.controle_de_estoque.model.dao.ItemPedidoClienteDao;
import com.controle_de_estoque.model.dao.PedidoClienteDao;
import com.controle_de_estoque.model.dao.ProdutoDao;
import com.controle_de_estoque.model.entity.ItemPedidoCliente;
import com.controle_de_estoque.visualizacao.PopUpErro;

public class ControlePaginaListarItensPedidoCliente {

	public static List<ItemPedidoCliente> retornarLista(int idPedidoCliente) {
		List<ItemPedidoCliente> lista = null;
		try {
			lista = new ItemPedidoClienteDao().retornarLista(idPedidoCliente);
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
		return lista;
	}

	public static String retornarDescricaoProduto(int idProduto) {
		String descricao = "";
		try {
			descricao = new ProdutoDao().retornar(idProduto).getDescricao();
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
		return descricao;
	}

	public static String retornarNomeCliente(int idPedidoCliente) {
		String nomeCliente = "";
		try {
			nomeCliente = new ClienteDao().retornar(new PedidoClienteDao().retornar(idPedidoCliente).getClienteId())
					.getNome();
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
		return nomeCliente;
	}
	
	public static void imprimir() {
		
	}
}
