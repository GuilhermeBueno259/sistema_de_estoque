package com.controle_de_estoque.visualizacao;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.controle_de_estoque.controle.telas.ControleTelaLogin;

public class PaginaLogin extends JFrame {

	private static final long serialVersionUID = 1633578363189167752L;

	private JTextField campoLogin;
	private JPasswordField campoSenha;
	private JButton botaoLogin;

	public PaginaLogin() {
		setLayout(null);
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Controle de Estoque");

		inicializarComponentes();

		setVisible(true);
	}

	private void inicializarComponentes() {
		campoLogin = new JTextField();
		campoSenha = new JPasswordField();
		botaoLogin = new JButton("Login");

		configurarComponentes();
	}

	private void configurarComponentes() {
		campoLogin.setBounds(new Rectangle(300, 30, 200, 30));
		campoSenha.setBounds(new Rectangle(300, 100, 200, 30));
		botaoLogin.setBounds(new Rectangle(350, 150, 100, 30));
		botaoLogin.addActionListener(e -> {
			ControleTelaLogin.logar(campoLogin.getText(), campoSenha.getSelectedText());
		});

		adicionarComponentes();
	}

	private void adicionarComponentes() {
		add(campoLogin);
		add(campoSenha);
		add(botaoLogin);
	}
}
