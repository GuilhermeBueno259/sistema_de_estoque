package com.controle_de_estoque.visualizacao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.controle_de_estoque.controle.telas.ControlePaginaFornecedores;
import com.controle_de_estoque.model.entity.Fornecedor;

public class PainelFornecedores extends JPanel {
	private static final long serialVersionUID = -3479878982767316417L;

	private JButton botaoAdicionarFornecedor, botaoAlterarFornecedor, botaoRemoverFornecedor, botaoImprimirTabela,
			botaoGerarRelatorio;
	private static JTable tabelaDeFornecedores;
	private JPanel painelSul;
	private JScrollPane painelCentral;
	private static DefaultTableModel modeloDeTabela;

	public PainelFornecedores() {
		setSize(600, 400);
		setLayout(new BorderLayout());

		inicializarComponentes();

		setVisible(true);
	}

	private void inicializarComponentes() {
		painelSul = new JPanel();
		botaoAdicionarFornecedor = new JButton("Adicionar");
		botaoAlterarFornecedor = new JButton("Alterar");
		botaoRemoverFornecedor = new JButton("Remover");
		botaoImprimirTabela = new JButton("Imprimir");
		botaoGerarRelatorio = new JButton("Gerar Relatório");

		tabelaDeFornecedores = new JTable(modeloDeTabela);
		painelCentral = new JScrollPane(tabelaDeFornecedores);

		configurarComponentes();
	}

	private void configurarComponentes() {
		painelSul.setLayout(new FlowLayout(FlowLayout.CENTER, 18, 20));

		botaoAdicionarFornecedor.setFocusable(false);
		botaoAdicionarFornecedor.addActionListener(e -> {
			new PaginaAdicionarFornecedor();
		});

		botaoAlterarFornecedor.setFocusable(false);
		botaoAlterarFornecedor.addActionListener(e -> {
			new PaginaAlterarFornecedor();
		});

		botaoRemoverFornecedor.setFocusable(false);
		botaoRemoverFornecedor.addActionListener(e -> {
			new PaginaRemoverFornecedor();
		});

		botaoImprimirTabela.setFocusable(false);
		botaoImprimirTabela.addActionListener(e -> {

		});

		botaoGerarRelatorio.setFocusable(false);
		botaoGerarRelatorio.addActionListener(e -> {
			new PaginaEscolherFormatoDoRelatorio("Fornecedores");
		});

		configurarModeloDeTabela();

		adicionarComponentes();
	}

	private static void configurarModeloDeTabela() {
		List<Fornecedor> fornecedores = ControlePaginaFornecedores.retornarLista();

		modeloDeTabela = new DefaultTableModel() {

			private static final long serialVersionUID = -758894717603843879L;

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				switch (columnIndex) {
				case 0:
					return fornecedores.get(rowIndex).getId();
				case 1:
					return fornecedores.get(rowIndex).getCpf_cnpj();
				case 2:
					return fornecedores.get(rowIndex).getIe();
				case 3:
					return fornecedores.get(rowIndex).getNome_fantasia();
				case 4:
					return fornecedores.get(rowIndex).getEmail();
				case 5:
					return fornecedores.get(rowIndex).getTelefoneId();
				case 6:
					return fornecedores.get(rowIndex).getEnderecoId();
				}
				return null;
			}

			@Override
			public int getRowCount() {
				return fornecedores.size();
			}

			@Override
			public int getColumnCount() {
				return 7;
			}

		};

		modeloDeTabela.addColumn("ID");
		modeloDeTabela.addColumn("CPF/CNPJ");
		modeloDeTabela.addColumn("IE");
		modeloDeTabela.addColumn("Nome Fantasia");
		modeloDeTabela.addColumn("Email");
		modeloDeTabela.addColumn("Telefone");
		modeloDeTabela.addColumn("Endereço");
	}

	private void adicionarComponentes() {
		painelSul.add(botaoAdicionarFornecedor);
		painelSul.add(botaoAlterarFornecedor);
		painelSul.add(botaoRemoverFornecedor);
		painelSul.add(botaoImprimirTabela);
		painelSul.add(botaoGerarRelatorio);
		add(painelCentral);
		add(BorderLayout.SOUTH, painelSul);
	}

	public static void recarregarTabela() {
		configurarModeloDeTabela();
		tabelaDeFornecedores.setModel(modeloDeTabela);
	}
}
