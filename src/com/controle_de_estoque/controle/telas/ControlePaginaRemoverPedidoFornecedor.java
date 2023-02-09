package com.controle_de_estoque.controle.telas;

import com.controle_de_estoque.model.dao.ItemPedidoFornecedorDao;
import com.controle_de_estoque.model.dao.PedidoFornecedorDao;
import com.controle_de_estoque.model.entity.PedidoFornecedor;
import com.controle_de_estoque.visualizacao.PopUpErro;
import com.controle_de_estoque.visualizacao.PopUpInformacao;

public class ControlePaginaRemoverPedidoFornecedor {
	public static void removerPedido(int id) {
		try {
			PedidoFornecedorDao pedidoFornecedorDao = new PedidoFornecedorDao();
			PedidoFornecedor pedido = pedidoFornecedorDao.retornar(id);

			if (pedido == null) {
				new PopUpInformacao("Pedido Inexistente", "O pedido não existe");
			} else {
				if (new ItemPedidoFornecedorDao().removerPorIdDoPedido(id) && pedidoFornecedorDao.remover(id)) {
					new PopUpInformacao("Sucesso", "O pedido foi deletado com sucesso!");
				}
			}
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}
}
