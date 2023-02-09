package com.controle_de_estoque.visualizacao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.controle_de_estoque.controle.telas.ControlePaginaClientes;
import com.controle_de_estoque.model.entity.Cliente;

public class PainelClientes extends JPanel {
	private static final long serialVersionUID = 3541010272430805268L;

	private JButton botaoAdicionarCliente, botaoAlterarCliente, botaoRemoverCliente, botaoImprimirTabela,
			botaoGerarRelatorio;
	private static JTable tabelaDeClientes;
	private JPanel painelSul;
	private JScrollPane painelCentral;
	private static DefaultTableModel modeloDeTabela;

	public PainelClientes() {
		setSize(600, 400);
		setLayout(new BorderLayout());

		inicializarComponentes();

		setVisible(true);
	}

	private void inicializarComponentes() {
		tabelaDeClientes = new JTable(modeloDeTabela);
		painelCentral = new JScrollPane(tabelaDeClientes);
		painelSul = new JPanel();
		painelSul.setLayout(new FlowLayout(FlowLayout.CENTER, 18, 20));

		botaoAdicionarCliente = new JButton("Adicionar");
		botaoAlterarCliente = new JButton("Alterar");
		botaoRemoverCliente = new JButton("Remover");
		botaoImprimirTabela = new JButton("Imprimir");
		botaoGerarRelatorio = new JButton("Gerar Relatório");

		configurarComponentes();
	}

	private void configurarComponentes() {
		configurarModeloDeTabela();

		botaoAdicionarCliente.setFocusable(false);
		botaoAdicionarCliente.addActionListener(e -> {
			new PaginaAdicionarCliente();
		});

		botaoAlterarCliente.setFocusable(false);
		botaoAlterarCliente.addActionListener(e -> {
			new PaginaAlterarCliente();
		});

		botaoRemoverCliente.setFocusable(false);
		botaoRemoverCliente.addActionListener(e -> {
			new PaginaRemoverCliente();
		});

		botaoImprimirTabela.setFocusable(false);
		botaoImprimirTabela.addActionListener(e -> {
			ControlePaginaClientes.imprimir();
		});

		botaoGerarRelatorio.setFocusable(false);
		botaoGerarRelatorio.addActionListener(e -> {
			new PaginaEscolherFormatoDoRelatorio("Clientes");
		});

		adicionarComponentes();
	}

	private void adicionarComponentes() {
		tabelaDeClientes.setModel(modeloDeTabela);
		painelSul.add(botaoAdicionarCliente);
		painelSul.add(botaoAlterarCliente);
		painelSul.add(botaoRemoverCliente);
		painelSul.add(botaoImprimirTabela);
		painelSul.add(botaoGerarRelatorio);

		add(BorderLayout.SOUTH, painelSul);
		add(painelCentral);
	}

	private static void configurarModeloDeTabela() {
		List<Cliente> clientes = ControlePaginaClientes.retornarLista();

		modeloDeTabela = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				switch (columnIndex) {
				case 0:
					return clientes.get(rowIndex).getId();
				case 1:
					return clientes.get(rowIndex).getNome();
				case 2:
					return clientes.get(rowIndex).getCpf();
				case 3:
					return clientes.get(rowIndex).getEmail();
				case 4:
					return ControlePaginaClientes.retornarEndereco(clientes.get(rowIndex).getEnderecoId());
				case 5:
					return ControlePaginaClientes.retornarTelefone(clientes.get(rowIndex).getTelefoneId());
				}
				return null;
			}

			@Override
			public int getRowCount() {
				return clientes.size();
			}

			@Override
			public int getColumnCount() {
				return 6;
			}

		};

		modeloDeTabela.addColumn("ID");
		modeloDeTabela.addColumn("Nome");
		modeloDeTabela.addColumn("CPF");
		modeloDeTabela.addColumn("Email");
		modeloDeTabela.addColumn("Endereço");
		modeloDeTabela.addColumn("Telefone");
	}

	public static void recarregarTabela() {
		configurarModeloDeTabela();
		tabelaDeClientes.setModel(modeloDeTabela);
	}
}
