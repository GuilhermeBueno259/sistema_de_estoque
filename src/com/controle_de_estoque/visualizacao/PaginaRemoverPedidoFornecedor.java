package com.controle_de_estoque.visualizacao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.controle_de_estoque.controle.telas.ControlePaginaRemoverPedidoFornecedor;

public class PaginaRemoverPedidoFornecedor extends JFrame {
	private static final long serialVersionUID = 7650572383551692302L;

	private JLabel labelId;
	private JTextField campoId;
	private JButton botaoRemoverPedidoFornecedor;

	public PaginaRemoverPedidoFornecedor() {
		setTitle("Controle de Estoque");
		setSize(300, 200);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);

		inicializaComponentes();

		setVisible(true);
	}

	private void inicializaComponentes() {
		labelId = new JLabel("ID");
		campoId = new JTextField();

		botaoRemoverPedidoFornecedor = new JButton("Remover");

		configuraComponentes();
	}

	private void configuraComponentes() {
		labelId.setBounds(80, 20, 100, 30);
		campoId.setBounds(75, 45, 150, 25);
		botaoRemoverPedidoFornecedor.setBounds(100, 80, 100, 30);
		botaoRemoverPedidoFornecedor.setFocusable(false);
		botaoRemoverPedidoFornecedor.addActionListener(e -> {
			ControlePaginaRemoverPedidoFornecedor.removerPedido(Integer.parseInt(campoId.getText()));
		});

		adicionaComponentes();
	}

	private void adicionaComponentes() {
		add(labelId);
		add(campoId);
		add(botaoRemoverPedidoFornecedor);
	}
}
