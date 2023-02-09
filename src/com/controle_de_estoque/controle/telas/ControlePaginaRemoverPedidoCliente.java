package com.controle_de_estoque.controle.telas;

import com.controle_de_estoque.model.dao.ItemPedidoClienteDao;
import com.controle_de_estoque.model.dao.PedidoClienteDao;
import com.controle_de_estoque.model.entity.PedidoCliente;
import com.controle_de_estoque.visualizacao.PopUpErro;
import com.controle_de_estoque.visualizacao.PopUpInformacao;

public class ControlePaginaRemoverPedidoCliente {

	public static void removerPedido(int id) {
		try {
			PedidoClienteDao pedidoClienteDao = new PedidoClienteDao();
			PedidoCliente pedido = pedidoClienteDao.retornar(id);

			if (pedido == null) {
				new PopUpInformacao("Pedido Inexistente", "O pedido não existe");
			} else {
				if (new ItemPedidoClienteDao().removerPorIdDoPedido(id) && pedidoClienteDao.remover(id)) {
					new PopUpInformacao("Sucesso", "O pedido foi deletado com sucesso!");
				}
			}
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}

}
