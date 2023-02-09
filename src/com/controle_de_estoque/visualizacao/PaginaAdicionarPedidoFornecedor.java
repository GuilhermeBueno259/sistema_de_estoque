package com.controle_de_estoque.visualizacao;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.controle_de_estoque.controle.telas.ControlePaginaAdicionarPedidoFornecedor;
import com.controle_de_estoque.visualizacao.componentes_personalizados.JLabelPersonalizado;

public class PaginaAdicionarPedidoFornecedor extends JFrame {
	private static final long serialVersionUID = 5470106992506241258L;

	private JLabel labelCpfCnpjFornecedor, labelNfe;
	private static JTextField campoCpfCnpjFornecedor, campoNfe;
	private JButton botaoAdicionarPedidoFornecedor;
	private JScrollPane painelDeRolagemDosItensDoPedido;
	private static JPanel painelComOsItensDoPedido;

	public PaginaAdicionarPedidoFornecedor() {
		setTitle("Controle de Estoque");
		setSize(600, 400);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);

		inicializaComponentes();

		setVisible(true);
	}

	private void inicializaComponentes() {
		labelCpfCnpjFornecedor = new JLabel("Fornecedor");
		labelNfe = new JLabel("Nfe");

		campoCpfCnpjFornecedor = new JTextField();
		campoNfe = new JTextField();

		painelComOsItensDoPedido = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		painelDeRolagemDosItensDoPedido = new JScrollPane(painelComOsItensDoPedido,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		botaoAdicionarPedidoFornecedor = new JButton("Adicionar");

		configuraComponentes();
	}

	private void configuraComponentes() {
		labelCpfCnpjFornecedor.setBounds(230, 20, 100, 30);
		labelNfe.setBounds(230, 80, 100, 30);

		campoCpfCnpjFornecedor.setBounds(225, 45, 150, 25);
		campoNfe.setBounds(225, 105, 150, 25);

		painelDeRolagemDosItensDoPedido.setBounds(200, 150, 200, 100);
		painelComOsItensDoPedido.setPreferredSize(new Dimension(200, 5000));

		botaoAdicionarPedidoFornecedor.setBounds(250, 270, 100, 30);
		botaoAdicionarPedidoFornecedor.addActionListener(e -> {
			ControlePaginaAdicionarPedidoFornecedor.adicionarPedido();
		});
		botaoAdicionarPedidoFornecedor.setFocusable(false);

		adicionaComponentes();
	}

	private void adicionaComponentes() {
		add(labelCpfCnpjFornecedor);
		add(campoCpfCnpjFornecedor);
		add(labelNfe);
		add(campoNfe);

		adicionarComponentesNoPainelDeItens();

		add(painelDeRolagemDosItensDoPedido);
		add(botaoAdicionarPedidoFornecedor);
	}

	private void adicionarComponentesNoPainelDeItens() {
		painelComOsItensDoPedido.add(new JLabelPersonalizado("Nome", 90, 20));
		painelComOsItensDoPedido.add(new JLabel("Quantidade"));
		painelComOsItensDoPedido.add(new JTextField(10));
		painelComOsItensDoPedido.add(new JTextField(3));
		JButton botaoAdicionarItem = new JButton("Adicionar outro item");
		botaoAdicionarItem.addActionListener(e -> {
			ControlePaginaAdicionarPedidoFornecedor.alterarInterfaceDaListaDeItensDoPedido();
		});
	}

	public static JPanel getPainel() {
		return painelComOsItensDoPedido;
	}

	public static String[] retornarInformacoesDoPedido() {
		String[] informacoesPedido = { campoCpfCnpjFornecedor.getText(), campoNfe.getText() };

		return informacoesPedido;
	}
}
