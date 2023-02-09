package com.controle_de_estoque.visualizacao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.controle_de_estoque.controle.telas.ControlePaginaRemoverPedidoCliente;

public class PaginaRemoverPedidoCliente extends JFrame {
	private static final long serialVersionUID = -1461306435964727341L;

	private JLabel labelId;
	private JTextField campoId;
	private JButton botaoRemoverPedidoCliente;

	public PaginaRemoverPedidoCliente() {
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
		botaoRemoverPedidoCliente = new JButton("Remover");

		configuraComponentes();
	}

	private void configuraComponentes() {
		labelId.setBounds(80, 20, 100, 30);
		campoId.setBounds(75, 45, 150, 25);

		botaoRemoverPedidoCliente.setBounds(100, 80, 100, 30);
		botaoRemoverPedidoCliente.setFocusable(false);
		botaoRemoverPedidoCliente.addActionListener(e -> {
			ControlePaginaRemoverPedidoCliente.removerPedido(Integer.parseInt(campoId.getText()));
		});

		adicionaComponentes();
	}

	private void adicionaComponentes() {
		add(labelId);
		add(campoId);
		add(botaoRemoverPedidoCliente);
	}
}
