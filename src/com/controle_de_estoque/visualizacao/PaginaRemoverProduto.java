package com.controle_de_estoque.visualizacao;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.controle_de_estoque.controle.telas.ControlePaginaRemoverProduto;

public class PaginaRemoverProduto extends JDialog {
	private static final long serialVersionUID = 5025280087825336230L;

	private JLabel labelId;
	private JTextField campoId;
	private JButton botaoRemoverProduto;

	public PaginaRemoverProduto() {
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
		botaoRemoverProduto = new JButton("Remover");

		configuraComponentes();
	}

	private void configuraComponentes() {
		labelId.setBounds(20, 20, 30, 30);
		campoId.setBounds(70, 20, 150, 30);
		botaoRemoverProduto.setBounds(100, 70, 100, 30);
		botaoRemoverProduto.setFocusable(false);
		botaoRemoverProduto.addActionListener(e -> {
			ControlePaginaRemoverProduto.removerProduto(campoId.getText());
		});

		adicionarComponentes();
	}

	private void adicionarComponentes() {
		add(labelId);
		add(campoId);
		add(botaoRemoverProduto);
	}
}
