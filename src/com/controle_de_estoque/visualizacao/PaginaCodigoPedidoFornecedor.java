package com.controle_de_estoque.visualizacao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PaginaCodigoPedidoFornecedor extends JFrame {
	private static final long serialVersionUID = -1902766696406716691L;

	private JLabel labelId;
	private JTextField campoId;
	private JButton botaoConfirmar;

	public PaginaCodigoPedidoFornecedor() {
		setTitle("Controle de Estoque");
		setSize(300, 200);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);

		inicializarComponentes();

		setVisible(true);
	}

	private void inicializarComponentes() {
		labelId = new JLabel("ID");
		campoId = new JTextField();
		botaoConfirmar = new JButton("Listar");

		configurarComponentes();
	}

	private void configurarComponentes() {
		labelId.setBounds(80, 20, 100, 30);
		campoId.setBounds(75, 55, 150, 25);
		botaoConfirmar.setBounds(100, 100, 100, 30);
		botaoConfirmar.setFocusable(false);
		botaoConfirmar.addActionListener(e -> {
			new PaginaListarItensPedidoFornecedor(Integer.parseInt(campoId.getText()));
		});

		adicionarComponentes();
	}

	private void adicionarComponentes() {
		add(labelId);
		add(campoId);
		add(botaoConfirmar);
	}
}
