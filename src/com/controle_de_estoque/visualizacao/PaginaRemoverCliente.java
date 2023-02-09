package com.controle_de_estoque.visualizacao;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.controle_de_estoque.controle.telas.ControlePaginaRemoverCliente;

public class PaginaRemoverCliente extends JDialog {
	private static final long serialVersionUID = 6406968123402263834L;
	private JLabel labelId;
	private JTextField campoId;
	private JButton botaoRemoverCliente;

	public PaginaRemoverCliente() {
		setTitle("Controle de Estoque");
		setSize(300, 200);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		inicializaComponentes();

		setVisible(true);
	}

	private void inicializaComponentes() {
		labelId = new JLabel("ID");
		campoId = new JTextField();
		botaoRemoverCliente = new JButton("Remover");

		configuraComponentes();
	}

	private void configuraComponentes() {
		labelId.setBounds(20, 20, 30, 30);
		campoId.setBounds(70, 20, 150, 30);
		botaoRemoverCliente.setBounds(100, 70, 100, 30);
		botaoRemoverCliente.setFocusable(false);
		botaoRemoverCliente.addActionListener(e -> {
			ControlePaginaRemoverCliente.removerCliente(Integer.parseInt(campoId.getText()));
		});

		adicionarComponentes();
	}

	private void adicionarComponentes() {
		add(labelId);
		add(campoId);
		add(botaoRemoverCliente);
	}
}
