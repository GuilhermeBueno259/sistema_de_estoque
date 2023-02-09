package com.controle_de_estoque.visualizacao;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.controle_de_estoque.controle.telas.ControlePaginaAdicionarProduto;

public class PaginaAdicionarProduto extends JFrame {
	private static final long serialVersionUID = -7262201277563267997L;

	private JLabel labelDescricao, labelCategoria, labelPreco, labelQuantidade;
	private JTextField campoDescricao, campoCategoria, campoPreco, campoQuantidade;
	private JButton adicionarProduto;

	public PaginaAdicionarProduto() {
		setSize(600, 400);
		setResizable(false);
		setTitle("Controle de Estoque");
		setLayout(null);
		setLocationRelativeTo(null);

		inicializarComponentes();

		setVisible(true);
	}

	private void inicializarComponentes() {
		labelDescricao = new JLabel("Descrição");
		campoDescricao = new JTextField();
		labelCategoria = new JLabel("Categoria");
		campoCategoria = new JTextField();
		labelPreco = new JLabel("Preço");
		campoPreco = new JTextField();
		labelQuantidade = new JLabel("Quantidade");
		campoQuantidade = new JTextField();
		adicionarProduto = new JButton("Adicionar");

		configurarComponentes();
	}

	private void configurarComponentes() {
		labelDescricao.setBounds(new Rectangle(150, 30, 200, 30));
		campoDescricao.setBounds(new Rectangle(250, 30, 200, 30));
		labelCategoria.setBounds(new Rectangle(150, 70, 200, 30));
		campoCategoria.setBounds(new Rectangle(250, 70, 200, 30));
		labelPreco.setBounds(new Rectangle(150, 110, 200, 30));
		campoPreco.setBounds(new Rectangle(250, 110, 200, 30));
		labelQuantidade.setBounds(new Rectangle(150, 150, 200, 30));
		campoQuantidade.setBounds(new Rectangle(250, 150, 200, 30));
		adicionarProduto.setBounds(200, 190, 100, 30);
		adicionarProduto.addActionListener(e -> {
			ControlePaginaAdicionarProduto.adicionarProduto(campoDescricao.getText(), campoCategoria.getText(),
					Float.parseFloat(campoPreco.getText()), Integer.parseInt(campoQuantidade.getText()));
		});

		adicionarComponentes();
	}

	private void adicionarComponentes() {
		add(labelDescricao);
		add(campoDescricao);
		add(labelCategoria);
		add(campoCategoria);
		add(labelPreco);
		add(campoPreco);
		add(labelQuantidade);
		add(campoQuantidade);
		add(adicionarProduto);
	}
}
