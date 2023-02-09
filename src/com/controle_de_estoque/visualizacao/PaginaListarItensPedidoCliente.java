package com.controle_de_estoque.visualizacao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.controle_de_estoque.controle.telas.ControlePaginaListarItensPedidoCliente;
import com.controle_de_estoque.model.entity.ItemPedidoCliente;

public class PaginaListarItensPedidoCliente extends JFrame {
	private static final long serialVersionUID = -2471578532409808843L;

	private JButton botaoImprimir, botaoGerarRelatorio;
	private JScrollPane painelDeRolagem;
	private JTable tabelaDeItens;
	private DefaultTableModel modeloDeTabela;
	private JPanel painelSul;
	private int idPedidoCliente;

	public PaginaListarItensPedidoCliente(int idPedidoCliente) {
		setTitle("Controle de Estoque");
		setSize(800, 400);
		setResizable(false);
		setLocationRelativeTo(null);

		this.idPedidoCliente = idPedidoCliente;

		inicializarComponentes();

		setVisible(true);
	}

	private void inicializarComponentes() {
		botaoImprimir = new JButton("Imprimir");
		botaoGerarRelatorio = new JButton("Gerar Relatório");

		tabelaDeItens = new JTable();
		painelDeRolagem = new JScrollPane(tabelaDeItens);
		painelSul = new JPanel(new FlowLayout(FlowLayout.CENTER, 18, 20));

		configurarComponentes();
	}

	private void configurarComponentes() {
		configurarTabela();

		botaoImprimir.setFocusable(false);
		botaoImprimir.addActionListener(e -> {
			ControlePaginaListarItensPedidoCliente.imprimir();
		});

		botaoGerarRelatorio.setFocusable(false);
		botaoGerarRelatorio.addActionListener(e -> {
			new PaginaEscolherFormatoDoRelatorio("Itens Pedido Cliente");
		});

		adicionarComponentes();
	}

	private void configurarTabela() {
		modeloDeTabela = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			List<ItemPedidoCliente> lista = ControlePaginaListarItensPedidoCliente.retornarLista(idPedidoCliente);

			@Override
			public Object getValueAt(int row, int column) {
				switch (column) {
				case 0:
					return lista.get(row).getId();
				case 1:
					return ControlePaginaListarItensPedidoCliente
							.retornarDescricaoProduto(lista.get(row).getProdutoId());
				case 2:
					return lista.get(row).getQuantidade();
				case 3:
					return ControlePaginaListarItensPedidoCliente
							.retornarNomeCliente(lista.get(row).getPedidoClienteId());
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
					return 0;
				}
			}

			@Override
			public int getColumnCount() {
				return 3;
			}
		};
		modeloDeTabela.addColumn("ID");
		modeloDeTabela.addColumn("Nome");
		modeloDeTabela.addColumn("Quantidade");
		modeloDeTabela.addColumn("Cliente");
	}

	private void adicionarComponentes() {
		painelSul.add(botaoImprimir);
		painelSul.add(botaoGerarRelatorio);

		tabelaDeItens.setModel(modeloDeTabela);
		add(painelDeRolagem, BorderLayout.CENTER);
		add(painelSul, BorderLayout.SOUTH);
	}
}
