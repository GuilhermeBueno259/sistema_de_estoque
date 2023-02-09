package com.controle_de_estoque.controle.telas;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.controle_de_estoque.model.dao.PedidoClienteDao;
import com.controle_de_estoque.model.dao.ProdutoDao;
import com.controle_de_estoque.model.entity.ItemPedidoCliente;
import com.controle_de_estoque.model.entity.PedidoCliente;
import com.controle_de_estoque.visualizacao.PaginaAlterarPedidoCliente;
import com.controle_de_estoque.visualizacao.PopUpErro;
import com.controle_de_estoque.visualizacao.PopUpInformacao;

public class ControlePaginaAlterarPedidoCliente {
	private static JPanel painel = PaginaAlterarPedidoCliente.getPainel();
	private static PedidoCliente pedido = new PedidoCliente();
	private static float precoTotal = 0F;

	public static void alterarPedido(int id, String nfE) {
		try {
			if (nfE.equals("")) {
				adicionarItemNaListaDeItens();
				alterarPainelDaListaDePedidos();

				PedidoClienteDao pedidoClienteDao = new PedidoClienteDao();
				PedidoCliente pedido = pedidoClienteDao.retornar(id);

				if (pedido == null) {
					new PopUpInformacao("Pedido Inexistente", "O pedido fornecido não existe");
				} else {
					pedidoClienteDao.alterar(new PedidoCliente(precoTotal));
				}

			} else {
				adicionarItemNaListaDeItens();
				alterarPainelDaListaDePedidos();

				PedidoClienteDao pedidoClienteDao = new PedidoClienteDao();
				PedidoCliente pedido = pedidoClienteDao.retornar(id);

				if (pedido == null) {
					new PopUpInformacao("Pedido Inexistente", "O pedido fornecido não existe");
				} else {
					pedidoClienteDao.alterar(new PedidoCliente(precoTotal, nfE, pedido.getClienteId()));
				}
			}
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}

	public static void adicionarItemNaListaDeItens() {
		try {
			if (pedido.getListaDeItens() == null) {
				pedido.setListaDeItens(new ArrayList<>());
			}

			String descricaoProduto = ((JTextField) painel.getComponent(painel.getComponentCount() - 3)).getText();
			ProdutoDao produtoDao = new ProdutoDao();
			int idProduto = produtoDao.retornaIdPorNome(descricaoProduto);

			if (idProduto != 0) {
				pedido.getListaDeItens().add(new ItemPedidoCliente(idProduto, Integer
						.parseInt(((JTextField) painel.getComponent(painel.getComponentCount() - 2)).getText())));

				precoTotal += produtoDao.retornarPrecoPorId(idProduto);
			}
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}

	public static void alterarPainelDaListaDePedidos() {
		adicionarItemNaListaDeItens();

		painel.remove(painel.getComponentCount());
		painel.add(new JTextField(10));
		painel.add(new JTextField(3));
		JButton botaoAdicionarItem = new JButton();
		botaoAdicionarItem.setFocusable(false);
		botaoAdicionarItem.addActionListener(e -> {
			alterarPainelDaListaDePedidos();
		});
		painel.add(botaoAdicionarItem);
	}
}
