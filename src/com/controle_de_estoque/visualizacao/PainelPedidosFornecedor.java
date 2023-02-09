package com.controle_de_estoque.visualizacao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.controle_de_estoque.controle.telas.ControlePaginaPedidoFornecedor;
import com.controle_de_estoque.model.entity.PedidoFornecedor;

public class PainelPedidosFornecedor extends JPanel {
	private static final long serialVersionUID = -4943615490471328602L;

	private JButton botaoAdicionarPedido, botaoAlterarPedido, botaoRemoverPedido, botaoImprimirTabela,
			botaoGerarRelatorio, botaoListarPedido;
	private static JTable tabelaDoEstoque;
	private JPanel painelSul;
	private JScrollPane painelCentral;
	private static DefaultTableModel modeloDeTabela;

	public PainelPedidosFornecedor() {
		setSize(700, 400);
		setLayout(new BorderLayout());

		inicializarComponentes();

		setVisible(true);
	}

	private void inicializarComponentes() {
		painelSul = new JPanel();
		botaoAdicionarPedido = new JButton("Adicionar");
		botaoAlterarPedido = new JButton("Alterar");
		botaoRemoverPedido = new JButton("Remover");
		botaoImprimirTabela = new JButton("Imprimir");
		botaoGerarRelatorio = new JButton("Gerar Relatório");
		botaoListarPedido = new JButton("Itens do Pedido");

		tabelaDoEstoque = new JTable();
		painelCentral = new JScrollPane(tabelaDoEstoque);

		configurarComponentes();
	}

	private void configurarComponentes() {
		painelSul.setLayout(new FlowLayout(FlowLayout.CENTER, 18, 20));
		botaoAdicionarPedido.addActionListener(e -> {
			new PaginaAdicionarPedidoFornecedor();
		});

		botaoAlterarPedido.addActionListener(e -> {
			new PaginaAlterarPedidoFornecedor();
		});

		botaoRemoverPedido.addActionListener(e -> {
			new PaginaRemoverPedidoFornecedor();
		});

		botaoImprimirTabela.addActionListener(e -> {

		});

		botaoGerarRelatorio.addActionListener(e -> {
			new PaginaEscolherFormatoDoRelatorio("Pedidos Fornecedores");
		});

		botaoListarPedido.addActionListener(e -> {
			new PaginaCodigoPedidoFornecedor();
		});

		configurarTabela();

		adicionarComponentes();
	}

	private static void configurarTabela() {
		List<PedidoFornecedor> pedidoEntrada = ControlePaginaPedidoFornecedor.retornarLista();

		modeloDeTabela = new DefaultTableModel() {

			private static final long serialVersionUID = -1514738736251546347L;

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				switch (columnIndex) {
				case 0:
					return pedidoEntrada.get(rowIndex).getId();
				case 1:
					return ControlePaginaPedidoFornecedor
							.retornarFantasiaNomeFornecedor(pedidoEntrada.get(rowIndex).getFornecedorId());
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
		modeloDeTabela.addColumn("Fornecedor");
		modeloDeTabela.addColumn("Data");
		modeloDeTabela.addColumn("Preço Total");
		modeloDeTabela.addColumn("nf-e");
	}

	private void adicionarComponentes() {
		tabelaDoEstoque.setModel(modeloDeTabela);

		painelSul.add(botaoAdicionarPedido);
		painelSul.add(botaoAlterarPedido);
		painelSul.add(botaoRemoverPedido);
		painelSul.add(botaoImprimirTabela);
		painelSul.add(botaoGerarRelatorio);
		painelSul.add(botaoListarPedido);
		add(BorderLayout.SOUTH, painelSul);
		add(painelCentral);
	}

	public static void recarregarTabela() {
		configurarTabela();
		tabelaDoEstoque.setModel(modeloDeTabela);
	}
}
