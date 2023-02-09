package com.controle_de_estoque.controle.telas;

import java.io.File;

import javax.swing.JFileChooser;

import com.controle_de_estoque.model.conexao.FabricaDeConexao;
import com.controle_de_estoque.visualizacao.PopUpErro;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;


public class ControlePaginaEscolherFormatoDoRelatorio {
	public static void gerarRelatorioEmPdf(String fonteDeDados) {
		try {
			File arquivoModelo = new File("C:\\Users\\User\\JaspersoftWorkspace\\MyReports\\Relatório "
					.concat(fonteDeDados).concat(".jasper"));

			if (!arquivoModelo.exists()) {
				JasperCompileManager.compileReportToFile("C:\\Users\\User\\JaspersoftWorkspace\\MyReports\\Relatório "
						.concat(fonteDeDados).concat(".jrxml"));
			}

			JasperPrint imprimir = JasperFillManager
					.fillReport("C:\\Users\\User\\JaspersoftWorkspace\\MyReports\\Relatório ".concat(fonteDeDados)
							.concat(".jasper"), null, FabricaDeConexao.getConexao());

			JRPdfExporter gerenciadorDeExportacaoPdf = new JRPdfExporter();

			String caminho = mostrarSelecionadorDeCaminho();

			if (!caminho.equals("")) {
				gerenciadorDeExportacaoPdf.setExporterInput(new SimpleExporterInput(imprimir));
				gerenciadorDeExportacaoPdf
						.setExporterOutput(new SimpleOutputStreamExporterOutput(caminho.concat(".pdf")));

				gerenciadorDeExportacaoPdf.exportReport();
			}
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}

	public static void gerarRelatorioEmPlanilha(String fonteDeDados) {
		try {
			File arquivoModelo = new File("C:\\Users\\User\\JaspersoftWorkspace\\MyReports\\Relatório "
					.concat(fonteDeDados).concat(".jasper"));

			if (!arquivoModelo.exists()) {
				JasperCompileManager.compileReportToFile("C:\\Users\\User\\JaspersoftWorkspace\\MyReports\\Relatório "
						.concat(fonteDeDados).concat(".jrxml"));
			}

			JasperPrint imprimir = JasperFillManager
					.fillReport("C:\\Users\\User\\JaspersoftWorkspace\\MyReports\\Relatório ".concat(fonteDeDados)
							.concat(".jasper"), null, FabricaDeConexao.getConexao());

			JRCsvExporter gerenciadorDeExportacaoCsv = new JRCsvExporter();

			String caminho = mostrarSelecionadorDeCaminho();

			if (!caminho.equals("")) {
				gerenciadorDeExportacaoCsv.setExporterInput(new SimpleExporterInput(imprimir));
				gerenciadorDeExportacaoCsv.setExporterOutput(new SimpleWriterExporterOutput(caminho.concat(".csv")));

				gerenciadorDeExportacaoCsv.exportReport();
			}
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}

	public static void gerarRelatorioEmWord(String fonteDeDados) {
		try {
			File arquivoModelo = new File("C:\\Users\\User\\JaspersoftWorkspace\\MyReports\\Relatório "
					.concat(fonteDeDados).concat(".jasper"));

			if (!arquivoModelo.exists()) {
				JasperCompileManager.compileReportToFile("C:\\Users\\User\\JaspersoftWorkspace\\MyReports\\Relatório "
						.concat(fonteDeDados).concat(".jrxml"));
			}

			JasperPrint imprimir = JasperFillManager
					.fillReport("C:\\Users\\User\\JaspersoftWorkspace\\MyReports\\Relatório ".concat(fonteDeDados)
							.concat(".jasper"), null, FabricaDeConexao.getConexao());

			JRDocxExporter gerenciadorDeExportacaoDocx = new JRDocxExporter();

			String caminho = mostrarSelecionadorDeCaminho();
			if (!caminho.equals("")) {
				gerenciadorDeExportacaoDocx.setExporterInput(new SimpleExporterInput(imprimir));
				gerenciadorDeExportacaoDocx
						.setExporterOutput(new SimpleOutputStreamExporterOutput(caminho.concat(".docx")));

				gerenciadorDeExportacaoDocx.exportReport();
			}

		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}

	private static String mostrarSelecionadorDeCaminho() {
		JFileChooser selecionadorDeCaminho = new JFileChooser();
		int retorno = selecionadorDeCaminho.showSaveDialog(null);

		return retorno == JFileChooser.APPROVE_OPTION ? selecionadorDeCaminho.getSelectedFile().toString() : "";
	}
}
