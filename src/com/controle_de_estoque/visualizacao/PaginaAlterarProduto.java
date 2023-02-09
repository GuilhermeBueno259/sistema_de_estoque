package com.controle_de_estoque.visualizacao;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PaginaAlterarProduto extends JFrame {
	private static final long serialVersionUID = 8164817364786472464L;

	private JLabel labelId, labelDescricao, labelCategoria, labelPreco, labelQuantidade;
	private JTextField campoId, campoDescricao, campoCategoria, campoPreco, campoQuantidade;
	private JButton botaoAlterarProduto;

	public PaginaAlterarProduto() {
		setSize(600, 400);
		setResizable(false);
		setTitle("Controle de Estoque");
		setLayout(null);
		setLocationRelativeTo(null);

		inicializarComponentes();

		setVisible(true);
	}

	private void inicializarComponentes() {
		labelId = new JLabel("Id");
		campoId = new JTextField();
		labelDescricao = new JLabel("Descrição");
		campoDescricao = new JTextField();
		labelCategoria = new JLabel("Categoria");
		campoCategoria = new JTextField();
		labelPreco = new JLabel("Preço");
		campoPreco = new JTextField();
		labelQuantidade = new JLabel("Quantidade");
		campoQuantidade = new JTextField();
		botaoAlterarProduto = new JButton("Alterar Produto");

		configurarComponentes();
	}

	private void configurarComponentes() {
		labelId.setBounds(new Rectangle(150, 30, 200, 30));
		campoId.setBounds(new Rectangle(250, 30, 200, 30));
		labelDescricao.setBounds(new Rectangle(150, 70, 200, 30));
		campoDescricao.setBounds(new Rectangle(250, 70, 200, 30));
		labelCategoria.setBounds(new Rectangle(150, 110, 200, 30));
		campoCategoria.setBounds(new Rectangle(250, 110, 200, 30));
		labelPreco.setBounds(new Rectangle(150, 150, 200, 30));
		campoPreco.setBounds(new Rectangle(250, 150, 200, 30));
		labelQuantidade.setBounds(new Rectangle(150, 190, 200, 30));
		campoQuantidade.setBounds(new Rectangle(250, 190, 200, 30));
		botaoAlterarProduto.setBounds(new Rectangle(200, 230, 200, 30));
		botaoAlterarProduto.addActionListener(e -> {
		});

		adicionarComponentes();
	}

	private void adicionarComponentes() {
		add(labelId);
		add(campoId);
		add(labelDescricao);
		add(campoDescricao);
		add(labelCategoria);
		add(campoCategoria);
		add(labelPreco);
		add(campoPreco);
		add(labelQuantidade);
		add(campoQuantidade);
		add(botaoAlterarProduto);
	}
}
