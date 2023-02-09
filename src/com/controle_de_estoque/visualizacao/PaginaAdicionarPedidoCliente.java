package com.controle_de_estoque.visualizacao;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.controle_de_estoque.controle.telas.ControlePaginaAdicionarPedidoCliente;
import com.controle_de_estoque.visualizacao.componentes_personalizados.JLabelPersonalizado;

public class PaginaAdicionarPedidoCliente extends JFrame {
	private static final long serialVersionUID = 5119700455571210188L;

	private JLabel labelCpfCnpjCliente, labelNfe;
	private static JTextField campoCpfCnpjCliente, campoNfe;
	private JButton botaoAdicionarPedidoCliente;
	private JScrollPane painelDeRolagemDosItensDoPedido;
	private static JPanel painelComOsItensDoPedido;

	public PaginaAdicionarPedidoCliente() {
		setTitle("Controle de Estoque");
		setSize(600, 400);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);

		inicializaComponentes();

		setVisible(true);
	}

	private void inicializaComponentes() {
		labelCpfCnpjCliente = new JLabel("Cliente");
		labelNfe = new JLabel("Nfe");

		campoCpfCnpjCliente = new JTextField();
		campoNfe = new JTextField();

		painelComOsItensDoPedido = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		painelDeRolagemDosItensDoPedido = new JScrollPane(painelComOsItensDoPedido,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		botaoAdicionarPedidoCliente = new JButton("Adicionar");

		configuraComponentes();
	}

	private void configuraComponentes() {
		labelCpfCnpjCliente.setBounds(230, 20, 100, 30);
		labelNfe.setBounds(230, 80, 100, 30);

		campoCpfCnpjCliente.setBounds(225, 45, 150, 25);
		campoNfe.setBounds(225, 105, 150, 25);

		painelDeRolagemDosItensDoPedido.setBounds(200, 150, 200, 100);
		painelComOsItensDoPedido.setPreferredSize(new Dimension(200, 5000));

		botaoAdicionarPedidoCliente.setBounds(250, 270, 100, 30);
		botaoAdicionarPedidoCliente.addActionListener(e -> {
			ControlePaginaAdicionarPedidoCliente.adicionarPedidoCliente();
		});
		botaoAdicionarPedidoCliente.setFocusable(false);

		adicionaComponentes();
	}

	private void adicionaComponentes() {
		add(labelCpfCnpjCliente);
		add(campoCpfCnpjCliente);
		add(labelNfe);
		add(campoNfe);
		adicionarComponentesNoPainelDeItens();

		add(painelDeRolagemDosItensDoPedido);
		add(botaoAdicionarPedidoCliente);
	}

	private void adicionarComponentesNoPainelDeItens() {
		painelComOsItensDoPedido.add(new JLabelPersonalizado("Nome", 90, 20));
		painelComOsItensDoPedido.add(new JLabel("Quantidade"));
		painelComOsItensDoPedido.add(new JTextField(10));
		painelComOsItensDoPedido.add(new JTextField(3));
		JButton botaoAdicionarItem = new JButton("Adicionar outro item");
		botaoAdicionarItem.addActionListener(e -> {
			ControlePaginaAdicionarPedidoCliente.alterarPainelDeItens();
		});
		painelComOsItensDoPedido.add(botaoAdicionarItem);
	}

	public static JPanel getPainel() {
		return painelComOsItensDoPedido;
	}

	public static String[] retornarInformacoesDoPedido() {
		String[] informacoesDoPediddo = { campoCpfCnpjCliente.getText(), campoNfe.getText() };
		return informacoesDoPediddo;
	}
}
