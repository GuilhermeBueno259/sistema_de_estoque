package com.controle_de_estoque.visualizacao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.controle_de_estoque.controle.telas.ControlePaginaEstoque;
import com.controle_de_estoque.model.entity.Produto;

public class PainelEstoque extends JPanel {
	private static final long serialVersionUID = -3915843168766737056L;

	private JButton botaoAdicionarProduto, botaoAlterarProduto, botaoRemoverProduto, botaoImprimirTabela,
			botaoGerarRelatorio;
	private static JTable tabelaDoEstoque;
	private JPanel painelSul;
	private JScrollPane painelCentral;
	private static DefaultTableModel modeloDeTabela;

	public PainelEstoque() {
		setSize(600, 400);
		setLayout(new BorderLayout());

		inicializarComponentes();

		setVisible(true);
	}

	private void inicializarComponentes() {
		tabelaDoEstoque = new JTable();
		painelCentral = new JScrollPane(tabelaDoEstoque);
		painelSul = new JPanel();
		botaoAdicionarProduto = new JButton("Adicionar");
		botaoAlterarProduto = new JButton("Alterar");
		botaoRemoverProduto = new JButton("Remover");
		botaoImprimirTabela = new JButton("Imprimir");
		botaoGerarRelatorio = new JButton("Gerar Relatório");

		configurarComponentes();
	}

	private void configurarComponentes() {
		painelSul.setLayout(new FlowLayout(FlowLayout.CENTER, 18, 20));

		botaoAdicionarProduto.setFocusable(false);
		botaoAdicionarProduto.addActionListener(e -> {
			new PaginaAdicionarProduto();
		});

		botaoAdicionarProduto.setFocusable(false);
		botaoAlterarProduto.addActionListener(e -> {
			new PaginaAlterarProduto();
		});

		botaoRemoverProduto.setFocusable(false);
		botaoRemoverProduto.addActionListener(e -> {
			new PaginaRemoverProduto();
		});

		botaoImprimirTabela.setFocusable(false);
		botaoImprimirTabela.addActionListener(e -> {
//			new ControlePaginaEstoque().imprimir();
		});

		botaoGerarRelatorio.setFocusable(false);
		botaoGerarRelatorio.addActionListener(e -> {
			new PaginaEscolherFormatoDoRelatorio("Estoque");
		});

		configurarModeloDeTabela();

		adicionarComponentes();
	}

	private static void configurarModeloDeTabela() {
		List<Produto> estoque = ControlePaginaEstoque.retornarListaDeProdutos();

		modeloDeTabela = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				switch (columnIndex) {
				case 0:
					return estoque.get(rowIndex).getId();
				case 1:
					return estoque.get(rowIndex).getDescricao();
				case 2:
					return estoque.get(rowIndex).getCategoria();
				case 3:
					return estoque.get(rowIndex).getPreco();
				case 4:
					return estoque.get(rowIndex).getQuantidade();
				}
				return null;
			}

			@Override
			public int getRowCount() {
				return estoque.size();
			}

			@Override
			public int getColumnCount() {
				return 5;
			}
		};

		modeloDeTabela.addColumn("ID");
		modeloDeTabela.addColumn("Descrição");
		modeloDeTabela.addColumn("Categoria");
		modeloDeTabela.addColumn("Preço");
		modeloDeTabela.addColumn("Quantidade");
	}

	private void adicionarComponentes() {
		tabelaDoEstoque.setModel(modeloDeTabela);

		painelSul.add(botaoRemoverProduto);
		painelSul.add(botaoAdicionarProduto);
		painelSul.add(botaoAlterarProduto);
		painelSul.add(botaoImprimirTabela);
		painelSul.add(botaoGerarRelatorio);

		add(painelCentral);
		add(BorderLayout.SOUTH, painelSul);
	}

	public static void recarregarTabela() {
		configurarModeloDeTabela();
		tabelaDoEstoque.setModel(modeloDeTabela);
	}
}
