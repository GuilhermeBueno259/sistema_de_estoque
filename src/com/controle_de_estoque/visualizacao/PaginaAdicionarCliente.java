package com.controle_de_estoque.visualizacao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.controle_de_estoque.controle.telas.ControlePaginaAdicionarCliente;

public class PaginaAdicionarCliente extends JFrame {

	private static final long serialVersionUID = 7789152147042224052L;

	private JPanel painel;
	private JLabel labelNome, labelCpf, labelEmail, labelEndereco, labelTelefone;
	private JTextField campoNome, campoCpf, campoEmail, campoEndereco, campoTelefone;
	private JButton botaoAdicionarCliente;

	public PaginaAdicionarCliente() {
		painel = new JPanel();
		painel.setLayout(null);
		add(painel);

		setTitle("Controle de Estoque");
		setSize(600, 400);
		setResizable(false);
		setLocationRelativeTo(null);

		inicializaComponentes();

		setVisible(true);
	}

	private void inicializaComponentes() {
		labelNome = new JLabel("Nome");
		labelCpf = new JLabel("CPF");
		labelEmail = new JLabel("Email");
		labelEndereco = new JLabel("Endereço");
		labelTelefone = new JLabel("Telefone");

		campoNome = new JTextField();
		campoCpf = new JTextField();
		campoEmail = new JTextField();
		campoEndereco = new JTextField();
		campoTelefone = new JTextField();

		botaoAdicionarCliente = new JButton("Adicionar");

		configuraComponentes();
	}

	private void configuraComponentes() {
		labelNome.setBounds(225, 20, 100, 30);
		labelCpf.setBounds(225, 70, 100, 30);
		labelEmail.setBounds(225, 120, 100, 30);
		labelEndereco.setBounds(225, 170, 100, 30);
		labelTelefone.setBounds(225, 220, 100, 30);

		campoNome.setBounds(225, 45, 150, 25);
		campoCpf.setBounds(225, 95, 150, 25);
		campoEmail.setBounds(225, 145, 150, 25);
		campoEndereco.setBounds(225, 195, 150, 25);
		campoTelefone.setBounds(225, 245, 150, 25);

		botaoAdicionarCliente.setBounds(250, 290, 100, 30);
		botaoAdicionarCliente.addActionListener(e -> {
			ControlePaginaAdicionarCliente.adicionarCliente(campoNome.getText(), campoCpf.getText(),
					campoEmail.getText(), campoEndereco.getText(), campoTelefone.getText());
		});
		botaoAdicionarCliente.setFocusable(false);

		adicionaComponentes();
	}

	private void adicionaComponentes() {
		painel.add(labelNome);
		painel.add(campoNome);
		painel.add(labelCpf);
		painel.add(campoCpf);
		painel.add(labelEmail);
		painel.add(campoEmail);
		painel.add(labelEndereco);
		painel.add(campoEndereco);
		painel.add(labelTelefone);
		painel.add(campoTelefone);
		painel.add(botaoAdicionarCliente);
	}

}
