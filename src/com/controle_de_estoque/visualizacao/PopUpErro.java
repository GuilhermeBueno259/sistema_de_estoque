package com.controle_de_estoque.visualizacao;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;

public class PopUpErro extends JDialog {
	private static final long serialVersionUID = 7949583325423507309L;

	private JTextArea campoMensagemDeErro;
	private JButton botaoConfirmar;

	public PopUpErro(String titulo, String mensagem) {
		setTitle(titulo);
		setSize(300, 200);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		inicializarComponentes(mensagem);

		setVisible(true);
	}

	private void inicializarComponentes(String mensagem) {
		campoMensagemDeErro = new JTextArea(mensagem);
		botaoConfirmar = new JButton("OK");

		configurarComponentes();
	}

	private void configurarComponentes() {
		campoMensagemDeErro.setBounds(20, 20, 240, 80);
		campoMensagemDeErro.setEditable(false);
		campoMensagemDeErro.setBackground(new Color(238, 238, 238));
		campoMensagemDeErro.setLineWrap(true);

		botaoConfirmar.setBounds(110, 100, 80, 30);
		botaoConfirmar.setFocusable(false);
		botaoConfirmar.addActionListener(e -> dispose());

		adicionarComponentes();
	}

	private void adicionarComponentes() {
		add(campoMensagemDeErro);
		add(botaoConfirmar);
	}
}
