package com.controle_de_estoque.visualizacao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.controle_de_estoque.controle.telas.ControlePaginaAlterarCliente;
import com.controle_de_estoque.model.entity.Cliente;

public class PaginaAlterarCliente extends JFrame {

	private static final long serialVersionUID = -3438906314222688827L;

	private JLabel labelId, labelNome, labelCpf, labelEmail, labelEndereco, labelTelefone;
	private JTextField campoId, campoNome, campoCpf, campoEmail, campoEndereco, campoTelefone;
	private JButton botaoAlterarCliente;

	public PaginaAlterarCliente() {
		setTitle("Controle de Estoque");
		setSize(600, 400);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);

		inicializaComponentes();

		setVisible(true);
	}

	private void inicializaComponentes() {
		labelId = new JLabel("ID");
		labelNome = new JLabel("Nome");
		labelCpf = new JLabel("CPF");
		labelEmail = new JLabel("Email");
		labelEndereco = new JLabel("Endereço");
		labelTelefone = new JLabel("Telefone");

		campoId = new JTextField();
		campoNome = new JTextField();
		campoCpf = new JTextField();
		campoEmail = new JTextField();
		campoEndereco = new JTextField();
		campoTelefone = new JTextField();

		botaoAlterarCliente = new JButton("Alterar");

		configuraComponentes();
	}

	private void configuraComponentes() {
		labelId.setBounds(225, 20, 100, 30);
		labelNome.setBounds(225, 70, 100, 30);
		labelCpf.setBounds(225, 120, 100, 30);
		labelEmail.setBounds(225, 170, 100, 30);
		labelEndereco.setBounds(225, 220, 100, 30);
		labelTelefone.setBounds(225, 270, 100, 30);

		campoId.setBounds(225, 45, 150, 25);
		campoNome.setBounds(225, 95, 150, 25);
		campoCpf.setBounds(225, 145, 150, 25);
		campoEmail.setBounds(225, 195, 150, 25);
		campoEndereco.setBounds(225, 245, 150, 25);
		campoTelefone.setBounds(225, 295, 150, 25);

		botaoAlterarCliente.setBounds(250, 330, 100, 30);
		botaoAlterarCliente.addActionListener(e -> {
			ControlePaginaAlterarCliente.verificarCliente(
					(new Cliente(Integer.parseInt(campoId.getText()), campoNome.getText(), campoCpf.getText(),
							campoEmail.getText(), campoEndereco.getText(), campoTelefone.getText())));
		});
		botaoAlterarCliente.setFocusable(false);

		adicionaComponentes();
	}

	private void adicionaComponentes() {
		add(labelId);
		add(campoId);
		add(labelNome);
		add(campoNome);
		add(labelCpf);
		add(campoCpf);
		add(labelEmail);
		add(campoEmail);
		add(labelEndereco);
		add(campoEndereco);
		add(labelTelefone);
		add(campoTelefone);
		add(botaoAlterarCliente);
	}
}
