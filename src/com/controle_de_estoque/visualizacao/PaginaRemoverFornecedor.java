package com.controle_de_estoque.visualizacao;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.controle_de_estoque.controle.telas.ControlePaginaRemoverFornecedor;

public class PaginaRemoverFornecedor extends JDialog {
	private static final long serialVersionUID = -3990602639981738433L;

	private JLabel labelId;
	private JTextField campoId;
	private JButton botaoRemoverFornecedor;

	public PaginaRemoverFornecedor() {
		setSize(300, 200);
		setTitle("Controle de Estoque");
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);

		inicializarComponentes();

		setVisible(true);
	}

	private void inicializarComponentes() {
		labelId = new JLabel("ID");
		campoId = new JTextField();
		botaoRemoverFornecedor = new JButton("Remover");

		configurarComponentes();
	}

	private void configurarComponentes() {
		labelId.setBounds(80, 20, 100, 30);
		campoId.setBounds(75, 45, 150, 25);
		botaoRemoverFornecedor.setBounds(100, 80, 100, 30);
		botaoRemoverFornecedor.setFocusable(false);
		botaoRemoverFornecedor.addActionListener(e -> {
			ControlePaginaRemoverFornecedor.removerFornecedor(campoId.getText());
		});

		adicionarComponentes();
	}

	private void adicionarComponentes() {
		add(labelId);
		add(campoId);
		add(botaoRemoverFornecedor);
	}
}
