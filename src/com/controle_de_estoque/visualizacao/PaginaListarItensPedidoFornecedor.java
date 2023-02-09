package com.controle_de_estoque.visualizacao;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.controle_de_estoque.controle.telas.ControlePaginaListarItensPedidoFornecedor;
import com.controle_de_estoque.model.entity.ItemPedidoFornecedor;

public class PaginaListarItensPedidoFornecedor extends JFrame {
	private static final long serialVersionUID = -3429962280297363989L;

	private int idPedidoFornecedor;
	private JScrollPane painelDeRolagem;
	private JTable tabelaDeItens;
	private DefaultTableModel modeloDeTabela;

	public PaginaListarItensPedidoFornecedor(int idPedidoFornecedor) {
		setTitle("Controle de Estoque");
		setSize(800, 400);
		setResizable(false);
		setLocationRelativeTo(null);

		this.idPedidoFornecedor = idPedidoFornecedor;

		inicializarComponentes();

		setVisible(true);
	}

	private void inicializarComponentes() {
		tabelaDeItens = new JTable();
		painelDeRolagem = new JScrollPane(tabelaDeItens);

		configurarTabela();

		configurarComponentes();
	}

	private void configurarTabela() {
		modeloDeTabela = new DefaultTableModel() {
			private static final long serialVersionUID = -1373276634520070852L;

			List<ItemPedidoFornecedor> lista = ControlePaginaListarItensPedidoFornecedor
					.retornarLista(idPedidoFornecedor);

			@Override
			public Object getValueAt(int row, int column) {
				switch (column) {
				case 0:
					return lista.get(column).getId();
				case 1:
					return ControlePaginaListarItensPedidoFornecedor
							.retornarDescricao(lista.get(column).getProdutoId());
				case 2:
					return lista.get(column).getQuantidade();
				case 3:
					return ControlePaginaListarItensPedidoFornecedor
							.retornarFantasiaNomeFornecedor(lista.get(row).getPedidoFornecedorId());
				default:
					return null;
				}
			}

			@Override
			public int getRowCount() {
				try {
					return lista.size();
				} catch (Exception e) {
					new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
				}
				return 0;
			}

			@Override
			public int getColumnCount() {
				return 3;
			}
		};
		modeloDeTabela.addColumn("ID");
		modeloDeTabela.addColumn("Nome");
		modeloDeTabela.addColumn("Quantidade");
	}

	private void configurarComponentes() {
		adicionarComponentes();
	}

	private void adicionarComponentes() {
		tabelaDeItens.setModel(modeloDeTabela);
		add(painelDeRolagem);
	}
}
