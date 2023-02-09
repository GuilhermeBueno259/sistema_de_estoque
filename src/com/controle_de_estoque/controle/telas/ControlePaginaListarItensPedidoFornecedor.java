package com.controle_de_estoque.controle.telas;

import java.util.List;

import com.controle_de_estoque.model.dao.FornecedorDao;
import com.controle_de_estoque.model.dao.ItemPedidoFornecedorDao;
import com.controle_de_estoque.model.dao.PedidoFornecedorDao;
import com.controle_de_estoque.model.dao.ProdutoDao;
import com.controle_de_estoque.model.entity.ItemPedidoFornecedor;
import com.controle_de_estoque.visualizacao.PopUpErro;

public class ControlePaginaListarItensPedidoFornecedor {
	public static List<ItemPedidoFornecedor> retornarLista(int idPedidoFornecedor) {
		List<ItemPedidoFornecedor> lista = null;
		try {
			lista = new ItemPedidoFornecedorDao().retornarLista(idPedidoFornecedor);
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
		return lista;
	}

	public static String retornarDescricao(int idProduto) {
		String descricao = "";
		try {
			descricao = new ProdutoDao().retornar(idProduto).getDescricao();
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
		return descricao;
	}

	public static String retornarFantasiaNomeFornecedor(int idPedidoFornecedor) {
		String fantasiaNomeFornecedor = "";
		try {
			fantasiaNomeFornecedor = new FornecedorDao()
					.retornar(new PedidoFornecedorDao().retornar(idPedidoFornecedor).getFornecedorId())
					.getNome_fantasia();
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
		return fantasiaNomeFornecedor;
	}
}
