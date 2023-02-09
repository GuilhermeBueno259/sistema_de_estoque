package com.controle_de_estoque.visualizacao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.controle_de_estoque.controle.telas.ControlePaginaEscolherFormatoDoRelatorio;

public class PaginaEscolherFormatoDoRelatorio extends JFrame {
	private static final long serialVersionUID = 3100406306866290078L;

	private JLabel labelEscolher;
	private JButton botaoGerarPdf, botaoGerarDocx, botaoGerarCsv;
	private String fonteDeDados;

	public PaginaEscolherFormatoDoRelatorio(String fonteDeDados) {
		this.fonteDeDados = fonteDeDados;

		setTitle("Controle de Estoque");
		setSize(300, 260);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);

		inicializarComponentes();

		setVisible(true);
	}

	private void inicializarComponentes() {
		labelEscolher = new JLabel("Gerar relatório em qual formato?");
		botaoGerarPdf = new JButton("PDF");
		botaoGerarDocx = new JButton("Word");
		botaoGerarCsv = new JButton("CSV (Excel)");

		configurarComponentes();
	}

	private void configurarComponentes() {
		labelEscolher.setBounds(50, 20, 200, 30);
		botaoGerarPdf.setBounds(100, 70, 100, 30);
		botaoGerarPdf.setFocusable(false);
		botaoGerarPdf.addActionListener(e -> {
			ControlePaginaEscolherFormatoDoRelatorio.gerarRelatorioEmPdf(fonteDeDados);
		});
		botaoGerarDocx.setBounds(100, 120, 100, 30);
		botaoGerarDocx.setFocusable(false);
		botaoGerarDocx.addActionListener(e -> {
			ControlePaginaEscolherFormatoDoRelatorio.gerarRelatorioEmWord(fonteDeDados);
		});
		botaoGerarCsv.setBounds(100, 170, 100, 30);
		botaoGerarCsv.setFocusable(false);
		botaoGerarCsv.addActionListener(e -> {
			ControlePaginaEscolherFormatoDoRelatorio.gerarRelatorioEmPlanilha(fonteDeDados);
		});

		adicionarComponentes();
	}

	private void adicionarComponentes() {
		add(labelEscolher);
		add(botaoGerarCsv);
		add(botaoGerarDocx);
		add(botaoGerarPdf);
	}
}
