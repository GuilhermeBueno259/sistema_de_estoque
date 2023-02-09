package com.controle_de_estoque.visualizacao;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.controle_de_estoque.controle.telas.ControlePaginaAlterarPedidoCliente;
import com.controle_de_estoque.controle.telas.ControlePaginaAlterarPedidoFornecedor;
import com.controle_de_estoque.visualizacao.componentes_personalizados.JLabelPersonalizado;

public class PaginaAlterarPedidoCliente extends JFrame {
	private static final long serialVersionUID = -4190501190007843437L;

	private JLabel labelId, labelNfe;
	private JTextField campoId, campoNfe;
	private JButton botaoAdicionarPedidoFornecedor;
	private JScrollPane painelDeRolagemDaListaDeItens;
	private static JPanel painelComAListaDeItens;

	public PaginaAlterarPedidoCliente() {
		setTitle("Controle de Estoque");
		setSize(600, 400);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);

		inicializaComponentes();

		setVisible(true);
	}

	private void inicializaComponentes() {
		labelId = new JLabel("ID");
		labelNfe = new JLabel("Nfe");

		campoId = new JTextField();
		campoNfe = new JTextField();

		painelComAListaDeItens = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		painelDeRolagemDaListaDeItens = new JScrollPane(painelComAListaDeItens,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		botaoAdicionarPedidoFornecedor = new JButton("Alterar");

		configuraComponentes();
	}

	private void configuraComponentes() {
		labelId.setBounds(230, 20, 100, 30);
		campoId.setBounds(225, 45, 150, 25);

		labelNfe.setBounds(230, 80, 100, 30);
		campoNfe.setBounds(225, 105, 150, 25);

		painelDeRolagemDaListaDeItens.setBounds(200, 150, 200, 100);
		painelComAListaDeItens.setPreferredSize(new Dimension(200, 5000));

		botaoAdicionarPedidoFornecedor.setBounds(250, 270, 100, 30);
		botaoAdicionarPedidoFornecedor.addActionListener(e -> {
			ControlePaginaAlterarPedidoCliente.alterarPedido(Integer.parseInt(campoId.getText()), campoNfe.getText());
		});
		botaoAdicionarPedidoFornecedor.setFocusable(false);

		adicionaComponentes();
	}

	private void adicionaComponentes() {
		add(labelId);
		add(campoId);
		add(labelNfe);
		add(campoNfe);

		adicionarComponentesNoPainelComAListaDeItens();

		add(painelDeRolagemDaListaDeItens);
		add(botaoAdicionarPedidoFornecedor);
	}

	private void adicionarComponentesNoPainelComAListaDeItens() {
		painelComAListaDeItens.add(new JLabelPersonalizado("Nome", 90, 20));
		painelComAListaDeItens.add(new JLabel("Quantidade"));
		painelComAListaDeItens.add(new JTextField(10));
		painelComAListaDeItens.add(new JTextField(3));
		JButton botaoAdicionarItem = new JButton("Adicionar outro item");
		botaoAdicionarItem.addActionListener(e -> {
			ControlePaginaAlterarPedidoFornecedor.alterarPainelDaListaDePedidos();
		});
		painelComAListaDeItens.add(botaoAdicionarItem);
	}

	public static JPanel getPainel() {
		return painelComAListaDeItens;
	}
}
