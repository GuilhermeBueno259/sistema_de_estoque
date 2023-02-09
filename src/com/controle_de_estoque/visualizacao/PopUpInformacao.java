package com.controle_de_estoque.visualizacao;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;

public class PopUpInformacao extends JDialog {

	private static final long serialVersionUID = 829058347966513917L;

	private JTextArea textoMensagem;
	private JButton botaoConfirmarVisualizacao;

	public PopUpInformacao(String titulo, String mensagem) {
		setTitle(titulo);
		setLocationRelativeTo(null);
		setType(Type.POPUP);
		setSize(300, 200);
		setResizable(false);
		setLayout(null);

		inicializarComponentes(mensagem);

		setVisible(true);
	}

	private void inicializarComponentes(String mensagem) {
		textoMensagem = new JTextArea(mensagem);
		botaoConfirmarVisualizacao = new JButton("OK");

		configuraComponentes();
	}

	private void configuraComponentes() {
		textoMensagem.setEditable(false);
		textoMensagem.setBackground(new Color(238, 238, 238));
		textoMensagem.setBounds(20, 20, 260, 90);

		botaoConfirmarVisualizacao.addActionListener(e -> dispose());
		botaoConfirmarVisualizacao.setBounds(115, 110, 70, 30);

		adicionarComponentes();
	}

	private void adicionarComponentes() {
		add(textoMensagem);
		add(botaoConfirmarVisualizacao);
	}
}
