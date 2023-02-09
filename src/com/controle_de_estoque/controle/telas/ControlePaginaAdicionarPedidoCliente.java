package com.controle_de_estoque.controle.telas;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.controle_de_estoque.model.dao.ClienteDao;
import com.controle_de_estoque.model.dao.ItemPedidoClienteDao;
import com.controle_de_estoque.model.dao.PedidoClienteDao;
import com.controle_de_estoque.model.dao.ProdutoDao;
import com.controle_de_estoque.model.entity.Cliente;
import com.controle_de_estoque.model.entity.ItemPedidoCliente;
import com.controle_de_estoque.model.entity.PedidoCliente;
import com.controle_de_estoque.visualizacao.PaginaAdicionarPedidoCliente;
import com.controle_de_estoque.visualizacao.PopUpErro;
import com.controle_de_estoque.visualizacao.PopUpInformacao;

public class ControlePaginaAdicionarPedidoCliente {
	private static PedidoCliente pedido = new PedidoCliente();
	private static JPanel painel = PaginaAdicionarPedidoCliente.getPainel();

	public static void adicionarPedidoCliente() {
		try {
			Cliente cliente = new ClienteDao()
					.retornarPorCpf(PaginaAdicionarPedidoCliente.retornarInformacoesDoPedido()[0]);

			if (cliente != null) {
				pedido.setClienteId(cliente.getId());
			} else {
				new PopUpInformacao("Cliente Inexistente", "O cliente informado não existe");
			}

			pedido.setNfE(PaginaAdicionarPedidoCliente.retornarInformacoesDoPedido()[1]);
			pedido.setData(new Date(Calendar.getInstance().getTimeInMillis()));

			retornarDescricaoDoProdutoEQuantidade();

			new PedidoClienteDao().inserir(pedido);

			int idPedido = new PedidoClienteDao().retornarIdPorData(pedido.getData());

			for (ItemPedidoCliente item : pedido.getListaDeItens()) {
				item.setPedidoClienteId(idPedido);
				new ItemPedidoClienteDao().inserir(item);
			}
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}

	public static void alterarPainelDeItens() {
		painel.remove(painel.getComponentCount() - 1);
		painel.add(new JTextField(10));
		painel.add(new JTextField(3));
		JButton botaoAdicionarItem = new JButton("Adicionar outro item");
		botaoAdicionarItem.addActionListener(e -> {
			alterarPainelDeItens();
		});
		painel.add(botaoAdicionarItem);
	}

	private static void adicionarItemNaListaDeItens(String nome, int quantidade) {
		try {
			if (pedido.getListaDeItens() == null) {
				pedido.setListaDeItens(new ArrayList<>());
			}

			int idDoProduto = new ProdutoDao().retornaIdPorNome(nome);

			if (idDoProduto != 0) {
				pedido.getListaDeItens().add(new ItemPedidoCliente(idDoProduto, quantidade));
			} else {
				new PopUpInformacao("Produto Inexistente",
						"O produto informado não está cadastrado\nou não existe na quantidade especificada");
			}
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}

	private static void retornarDescricaoDoProdutoEQuantidade() {
		adicionarItemNaListaDeItens(((JTextField) painel.getComponent(painel.getComponents().length - 3)).getText(),
				Integer.parseInt(((JTextField) painel.getComponent(painel.getComponents().length - 2)).getText()));
	}
}
