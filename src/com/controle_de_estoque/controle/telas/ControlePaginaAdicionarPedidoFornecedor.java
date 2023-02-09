package com.controle_de_estoque.controle.telas;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.controle_de_estoque.model.dao.FornecedorDao;
import com.controle_de_estoque.model.dao.ItemPedidoFornecedorDao;
import com.controle_de_estoque.model.dao.PedidoFornecedorDao;
import com.controle_de_estoque.model.dao.ProdutoDao;
import com.controle_de_estoque.model.entity.Fornecedor;
import com.controle_de_estoque.model.entity.ItemPedidoFornecedor;
import com.controle_de_estoque.model.entity.PedidoFornecedor;
import com.controle_de_estoque.visualizacao.PaginaAdicionarPedidoFornecedor;
import com.controle_de_estoque.visualizacao.PopUpErro;
import com.controle_de_estoque.visualizacao.PopUpInformacao;

public class ControlePaginaAdicionarPedidoFornecedor {
	private static PedidoFornecedor pedido = new PedidoFornecedor();
	private static JPanel painel = PaginaAdicionarPedidoFornecedor.getPainel();

	public static void adicionarPedido() {
		try {
			verificarSeNfeJaExiste(PaginaAdicionarPedidoFornecedor.retornarInformacoesDoPedido()[1]);
			Fornecedor fornecedor = new FornecedorDao()
					.retornarPorCpfCnpj(PaginaAdicionarPedidoFornecedor.retornarInformacoesDoPedido()[0]);

			if (fornecedor != null) {
				pedido.setFornecedorId(fornecedor.getId());
			} else {
				new PopUpInformacao("Fornecedor Inexistente", "O fornecedor informado não existe");
			}

			pedido.setNfE(PaginaAdicionarPedidoFornecedor.retornarInformacoesDoPedido()[1]);
			pedido.setData(new Date(Calendar.getInstance().getTimeInMillis()));

			retornarDescricaoDoProdutoEQuantidade();

			new PedidoFornecedorDao().inserir(pedido);

			int idPedido = new PedidoFornecedorDao().retornarIdPorData(pedido.getData());

			for (ItemPedidoFornecedor item : pedido.getListaDeItens()) {
				item.setPedidoFornecedorId(idPedido);
				new ItemPedidoFornecedorDao().inserir(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void adicionarItemNaListaDePedidos(String nome, int quantidade) {
		try {
			if (pedido.getListaDeItens() == null) {
				pedido.setListaDeItens(new ArrayList<>());
			}

			int idDoProduto = new ProdutoDao().retornaIdPorNome(nome);

			if (idDoProduto != 0) {
				pedido.getListaDeItens().add(new ItemPedidoFornecedor(idDoProduto, quantidade));
			} else {
				new PopUpInformacao("Produto Inexistente",
						"O produto informado não está cadastrado\nou não existe na quantidade especificada");
			}
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}

	public static void alterarInterfaceDaListaDeItensDoPedido() {
		painel.remove(painel.getComponents().length - 1);
		painel.add(new JTextField(10));
		painel.add(new JTextField(3));
		JButton botaoAdicionarItem = new JButton("Adicionar outro item");
		botaoAdicionarItem.addActionListener(e -> {
			alterarInterfaceDaListaDeItensDoPedido();
		});
		botaoAdicionarItem.setFocusable(false);
		painel.add(botaoAdicionarItem);
	}

	private static void retornarDescricaoDoProdutoEQuantidade() {
		adicionarItemNaListaDePedidos(((JTextField) painel.getComponent(painel.getComponents().length - 3)).getText(),
				Integer.parseInt(((JTextField) painel.getComponent(painel.getComponents().length - 2)).getText()));
	}

	private static void verificarSeNfeJaExiste(String nfe) throws SQLException {
		PedidoFornecedorDao pedidoFornecedorDao = new PedidoFornecedorDao();

		if (pedidoFornecedorDao.retornarPorNfe(nfe) != null) {
			new PopUpInformacao("Falha", "Esta nfe já existe");
		}
	}
}