package com.controle_de_estoque.visualizacao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.controle_de_estoque.controle.telas.ControlePaginaPedidoCliente;
import com.controle_de_estoque.model.entity.PedidoCliente;

public class PainelPedidoCliente extends JPanel {
	private static final long serialVersionUID = 7969670907044795803L;

	private JButton adicionarPedido, alterarPedido, removerPedido, botaoImprimir, botaoGerarRelatorio,
			botaoListarItensPedido;
	private static JTable tabelaDoEstoque;
	private JPanel painelSul;
	private JScrollPane painelCentral;
	private static DefaultTableModel modeloDeTabela;

	public PainelPedidoCliente() {
		setSize(700, 400);
		setLayout(new BorderLayout());

		inicializarComponentes();

		setVisible(true);
	}

	private void inicializarComponentes() {
		painelSul = new JPanel();
		adicionarPedido = new JButton("Adicionar");
		alterarPedido = new JButton("Alterar");
		removerPedido = new JButton("Remover");
		botaoImprimir = new JButton("Imprimir");
		botaoGerarRelatorio = new JButton("Gerar Relatório");
		botaoListarItensPedido = new JButton("Listar Itens");
		tabelaDoEstoque = new JTable(modeloDeTabela);
		painelCentral = new JScrollPane(tabelaDoEstoque);

		configurarComponentes();
	}

	private void configurarComponentes() {
		painelSul.setLayout(new FlowLayout(FlowLayout.CENTER, 18, 20));

		adicionarPedido.addActionListener(e -> {
			new PaginaAdicionarPedidoCliente();
		});

		alterarPedido.addActionListener(e -> {
			new PaginaAlterarPedidoCliente();
		});

		removerPedido.addActionListener(e -> {
			new PaginaRemoverPedidoCliente();
		});

		botaoImprimir.addActionListener(e -> {

		});

		botaoGerarRelatorio.addActionListener(e -> {
			new PaginaEscolherFormatoDoRelatorio("Pedidos Clientes");
		});

		botaoListarItensPedido.addActionListener(e -> {
			new PaginaCodigoPedidoCliente();
		});

		configurarTabela();

		adicionarComponentes();
	}

	private static void configurarTabela() {
		List<PedidoCliente> pedidoEntrada = ControlePaginaPedidoCliente.retornarLista();

		modeloDeTabela = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				switch (columnIndex) {
				case 0:
					return pedidoEntrada.get(rowIndex).getId();
				case 1:
					return ControlePaginaPedidoCliente.retornarNomeCliente(pedidoEntrada.get(rowIndex).getClienteId());
				case 2:
					return pedidoEntrada.get(rowIndex).getData();
				case 3:
					return pedidoEntrada.get(rowIndex).getPrecoTotal();
				case 4:
					return pedidoEntrada.get(rowIndex).getNfE();
				}
				return null;
			}

			@Override
			public int getRowCount() {
				return pedidoEntrada.size();
			}

			@Override
			public int getColumnCount() {
				return 5;
			}
		};

		modeloDeTabela.addColumn("ID");
		modeloDeTabela.addColumn("Cliente");
		modeloDeTabela.addColumn("Data");
		modeloDeTabela.addColumn("Preço Total");
		modeloDeTabela.addColumn("nf-e");
	}

	private void adicionarComponentes() {
		painelSul.add(adicionarPedido);
		painelSul.add(alterarPedido);
		painelSul.add(removerPedido);
		painelSul.add(botaoImprimir);
		painelSul.add(botaoGerarRelatorio);
		painelSul.add(botaoListarItensPedido);
		add(BorderLayout.SOUTH, painelSul);
		add(painelCentral);
	}

	public static void recarregarTabela() {
		configurarTabela();
		tabelaDoEstoque.setModel(modeloDeTabela);
	}
}
