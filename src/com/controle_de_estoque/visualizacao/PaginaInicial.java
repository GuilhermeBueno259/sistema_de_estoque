package com.controle_de_estoque.visualizacao;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PaginaInicial extends JFrame {

	private static final long serialVersionUID = -5636515925541064804L;

	private JPanel painelEstoque, painelPedidosFornecedor, painelPedidosCliente, painelClientes, painelFornecedores;
	private JTabbedPane painelAbas;

	public PaginaInicial() {
		setTitle("Controle de Estoque");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(600, 400));
		setLocationRelativeTo(null);

		inicializarComponentes();

		setVisible(true);
	}

	private void inicializarComponentes() {
		painelEstoque = new PainelEstoque();
		painelPedidosFornecedor = new PainelPedidosFornecedor();
		painelPedidosCliente = new PainelPedidoCliente();
		painelClientes = new PainelClientes();
		painelFornecedores = new PainelFornecedores();
		painelAbas = new JTabbedPane();

		configurarComponentes();
	}

	private void configurarComponentes() {

		painelAbas.addTab("Estoque", painelEstoque);
		painelAbas.addTab("Pedidos Fornecedor", painelPedidosFornecedor);
		painelAbas.addTab("Pedidos Clientes", painelPedidosCliente);
		painelAbas.addTab("Clientes", painelClientes);
		painelAbas.addTab("Fornecedores", painelFornecedores);

		adicionarComponentes();
	}

	private void adicionarComponentes() {
		add(painelAbas);
	}

	public void fecharPaginaInicial() {
		this.dispose();
	}
}
