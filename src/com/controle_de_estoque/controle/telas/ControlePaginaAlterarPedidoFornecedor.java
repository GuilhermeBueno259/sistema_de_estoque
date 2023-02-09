package com.controle_de_estoque.controle.telas;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.controle_de_estoque.model.dao.PedidoFornecedorDao;
import com.controle_de_estoque.model.dao.ProdutoDao;
import com.controle_de_estoque.model.entity.ItemPedidoFornecedor;
import com.controle_de_estoque.model.entity.PedidoFornecedor;
import com.controle_de_estoque.visualizacao.PaginaAlterarPedidoFornecedor;
import com.controle_de_estoque.visualizacao.PopUpErro;
import com.controle_de_estoque.visualizacao.PopUpInformacao;

public class ControlePaginaAlterarPedidoFornecedor {
	private static JPanel painel = PaginaAlterarPedidoFornecedor.getPainel();
	private static PedidoFornecedor pedido = new PedidoFornecedor();
	private static float precoTotal = 0F;

	public static void alterarPedido(int id, String nfE) {
		try {
			if (nfE.equals("")) {
				adicionarItemNaListaDeItens();
				alterarPainelDaListaDePedidos();

				PedidoFornecedorDao pedidoFornecedorDao = new PedidoFornecedorDao();
				PedidoFornecedor pedido = pedidoFornecedorDao.retornar(id);

				if (pedido == null) {
					new PopUpInformacao("Pedido Inexistente", "O pedido fornecido não existe");
				} else {
					pedidoFornecedorDao.alterar(new PedidoFornecedor(precoTotal));
				}
			} else {
				adicionarItemNaListaDeItens();
				alterarPainelDaListaDePedidos();

				PedidoFornecedorDao pedidoFornecedorDao = new PedidoFornecedorDao();
				PedidoFornecedor pedido = pedidoFornecedorDao.retornar(id);

				if (pedido == null) {
					new PopUpInformacao("Pedido Inexistente", "O pedido fornecido não existe");
				} else {
					pedidoFornecedorDao.alterar(new PedidoFornecedor(precoTotal, nfE, pedido.getFornecedorId()));
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
				pedido.getListaDeItens().add(new ItemPedidoFornecedor(idProduto, Integer
						.parseInt(((JTextField) painel.getComponent(painel.getComponentCount() - 2)).getText())));

				precoTotal += produtoDao.retornarPrecoPorId(idProduto);
			}
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}

	public static void alterarPainelDaListaDePedidos() {
		adicionarItemNaListaDeItens();

		painel.remove(painel.getComponentCount() - 1);
		painel.add(new JTextField(10));
		painel.add(new JTextField(3));
		JButton botaoAdicionarItem = new JButton("Adicionar outro item");
		botaoAdicionarItem.setFocusable(false);
		botaoAdicionarItem.addActionListener(e -> {
			alterarPainelDaListaDePedidos();
		});
		painel.add(botaoAdicionarItem);
	}
}
