package com.controle_de_estoque.visualizacao;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.controle_de_estoque.controle.telas.ControlePaginaAlterarPedidoFornecedor;
import com.controle_de_estoque.visualizacao.componentes_personalizados.JLabelPersonalizado;

public class PaginaAlterarPedidoFornecedor extends JFrame {

	private static final long serialVersionUID = 5470106992506241258L;

	private JLabel labelId, labelNfe;
	private JTextField campoId, campoNfe;
	private JButton botaoAlterarPedidoFornecedor;
	private JScrollPane painelDeRolagemDosItensDoPedido;
	private static JPanel painelComOsItensDoPedido;

	public PaginaAlterarPedidoFornecedor() {
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

		painelComOsItensDoPedido = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		painelDeRolagemDosItensDoPedido = new JScrollPane(painelComOsItensDoPedido,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		botaoAlterarPedidoFornecedor = new JButton("Alterar");

		configuraComponentes();
	}

	private void configuraComponentes() {
		labelId.setBounds(230, 20, 100, 30);
		campoId.setBounds(225, 45, 150, 25);

		labelNfe.setBounds(230, 80, 100, 30);
		campoNfe.setBounds(225, 105, 150, 25);

		painelDeRolagemDosItensDoPedido.setBounds(200, 150, 200, 100);
		painelComOsItensDoPedido.setPreferredSize(new Dimension(200, 5000));

		botaoAlterarPedidoFornecedor.setBounds(250, 270, 100, 30);
		botaoAlterarPedidoFornecedor.addActionListener(e -> {
			ControlePaginaAlterarPedidoFornecedor.alterarPedido(Integer.parseInt(campoId.getText()),
					campoNfe.getText());
		});
		botaoAlterarPedidoFornecedor.setFocusable(false);

		adicionaComponentes();
	}

	private void adicionaComponentes() {
		add(labelId);
		add(campoId);

		add(labelNfe);
		add(campoNfe);

		adicionaComponentesNoPainelDeItens();

		add(painelDeRolagemDosItensDoPedido);

		add(botaoAlterarPedidoFornecedor);
	}

	private void adicionaComponentesNoPainelDeItens() {
		painelComOsItensDoPedido.add(new JLabelPersonalizado("Nome", 90, 20));
		painelComOsItensDoPedido.add(new JLabel("Quantidade"));
		painelComOsItensDoPedido.add(new JTextField(10));
		painelComOsItensDoPedido.add(new JTextField(3));
		JButton botaoAdicionarItem = new JButton("Adicionar outro item");
		botaoAdicionarItem.addActionListener(e -> {
			ControlePaginaAlterarPedidoFornecedor.alterarPainelDaListaDePedidos();
		});
		painelComOsItensDoPedido.add(botaoAdicionarItem);
	}

	public static JPanel getPainel() {
		return painelComOsItensDoPedido;
	}
}
