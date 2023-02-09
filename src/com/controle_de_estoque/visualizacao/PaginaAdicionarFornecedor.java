package com.controle_de_estoque.visualizacao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.controle_de_estoque.controle.telas.ControlePaginaAdicionarFornecedor;
import com.controle_de_estoque.model.entity.Fornecedor;

public class PaginaAdicionarFornecedor extends JFrame {
	private static final long serialVersionUID = 4517304985488468368L;

	private JLabel labelCpfCnpj, labelIe, labelFantasiaNome, labelEmail, labelTelefone, labelEndereco;
	private JTextField campoCpfCnpj, campoIe, campoFantasiaNome, campoEmail, campoTelefone, campoEndereco;
	private JButton botaoAdicionar;

	public PaginaAdicionarFornecedor() {
		setTitle("Controle de Estoque");
		setSize(600, 450);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);

		inicializaComponentes();

		setVisible(true);
	}

	private void inicializaComponentes() {
		labelCpfCnpj = new JLabel("CPF/CNPJ");
		labelIe = new JLabel("IE");
		labelFantasiaNome = new JLabel("Fantasia/Nome");
		labelEmail = new JLabel("E-mail");
		labelTelefone = new JLabel("Telefone");
		labelEndereco = new JLabel("Endereço");

		campoCpfCnpj = new JTextField();
		campoIe = new JTextField();
		campoFantasiaNome = new JTextField();
		campoEmail = new JTextField();
		campoTelefone = new JTextField();
		campoEndereco = new JTextField();

		botaoAdicionar = new JButton("Adicionar");

		configuraComponentes();
	}

	private void configuraComponentes() {
		labelCpfCnpj.setBounds(230, 10, 100, 30);
		campoCpfCnpj.setBounds(225, 35, 150, 25);

		labelIe.setBounds(230, 60, 100, 30);
		campoIe.setBounds(225, 85, 150, 25);

		labelFantasiaNome.setBounds(230, 120, 100, 30);
		campoFantasiaNome.setBounds(225, 145, 150, 25);

		labelEmail.setBounds(230, 180, 100, 30);
		campoEmail.setBounds(225, 205, 150, 25);

		labelTelefone.setBounds(230, 240, 100, 30);
		campoTelefone.setBounds(225, 265, 150, 25);

		labelEndereco.setBounds(230, 300, 100, 30);
		campoEndereco.setBounds(225, 325, 150, 25);

		botaoAdicionar.setBounds(250, 360, 100, 30);
		botaoAdicionar.setFocusable(false);
		botaoAdicionar.addActionListener(e -> {
			ControlePaginaAdicionarFornecedor.adicionarFornecedor(
					new Fornecedor(campoCpfCnpj.getText(), campoIe.getText(), campoFantasiaNome.getText(),
							campoEmail.getText(), campoTelefone.getText(), campoEndereco.getText()));
		});

		adicionaComponentes();
	}

	private void adicionaComponentes() {
		add(labelCpfCnpj);
		add(campoCpfCnpj);
		add(labelIe);
		add(campoIe);
		add(labelFantasiaNome);
		add(campoFantasiaNome);
		add(labelEmail);
		add(campoEmail);
		add(labelTelefone);
		add(campoTelefone);
		add(labelEndereco);
		add(campoEndereco);
		add(botaoAdicionar);
	}
}
