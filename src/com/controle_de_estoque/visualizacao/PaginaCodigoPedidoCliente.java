package com.controle_de_estoque.visualizacao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PaginaCodigoPedidoCliente extends JFrame {
	private static final long serialVersionUID = 3860714694840120783L;

	private JLabel labelId;
	private JTextField campoId;
	private JButton botaoConfirmar;
	
	public PaginaCodigoPedidoCliente() {
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
		botaoConfirmar = new JButton("Confirmar");
		
		configurarComponentes();
	}

	private void configurarComponentes() {
		labelId.setBounds(80, 20, 100, 30);
		campoId.setBounds(75, 45, 150, 25);
		botaoConfirmar.setFocusable(false);
		botaoConfirmar.setBounds(100, 90, 100, 30);
		botaoConfirmar.addActionListener(e -> {
			new PaginaListarItensPedidoCliente(Integer.parseInt(campoId.getText()));
		});
		
		adicionarComponentes();
	}

	private void adicionarComponentes() {
		add(labelId);
		add(campoId);
		add(botaoConfirmar);
	}
}