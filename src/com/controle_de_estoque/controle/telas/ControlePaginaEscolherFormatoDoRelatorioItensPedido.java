package com.controle_de_estoque.controle.telas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFileChooser;

import com.controle_de_estoque.model.dao.ClienteDao;
import com.controle_de_estoque.model.dao.FornecedorDao;
import com.controle_de_estoque.model.dao.ItemPedidoClienteDao;
import com.controle_de_estoque.model.dao.ItemPedidoFornecedorDao;
import com.controle_de_estoque.model.dao.PedidoClienteDao;
import com.controle_de_estoque.model.dao.PedidoFornecedorDao;
import com.controle_de_estoque.model.dao.ProdutoDao;
import com.controle_de_estoque.model.entity.ItemPedidoCliente;
import com.controle_de_estoque.model.entity.ItemPedidoFornecedor;
import com.controle_de_estoque.visualizacao.PopUpErro;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

public class ControlePaginaEscolherFormatoDoRelatorioItensPedido {
	public static void gerarRelatorioEmPdf(String fonteDeDados, int idPedido) {
		try {
			criarArquivoCsv(fonteDeDados, idPedido);

			JRDataSource ds = new JRCsvDataSource("C:\\Users\\User\\Desktop\\meuArquivo.csv");

			File arquivoModelo = new File("C:\\Users\\User\\JaspersoftWorkspace\\MyReports\\Relatório "
					.concat(fonteDeDados).concat(".jasper"));

			if (!arquivoModelo.exists()) {
				JasperCompileManager.compileReportToFile("C:\\Users\\User\\JaspersoftWorkspace\\MyReports\\Relatório "
						.concat(fonteDeDados).concat(".jrxml"));
			}

			JasperPrint imprimir = JasperFillManager
					.fillReport("C:\\Users\\User\\JaspersoftWorkspace\\MyReports\\Relatório ".concat(fonteDeDados)
							.concat(".jasper"), null, ds);

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

	public static void gerarRelatorioEmPlanilha(String fonteDeDados, int idPedido) {
		try {
			criarArquivoCsv(fonteDeDados, idPedido);

			JRDataSource ds = new JRCsvDataSource("C:\\Users\\User\\Desktop\\meuArquivo.csv");

			File arquivoModelo = new File("C:\\Users\\User\\JaspersoftWorkspace\\MyReports\\Relatório "
					.concat(fonteDeDados).concat(".jasper"));

			if (!arquivoModelo.exists()) {
				JasperCompileManager.compileReportToFile("C:\\Users\\User\\JaspersoftWorkspace\\MyReports\\Relatório "
						.concat(fonteDeDados).concat(".jrxml"));
			}

			JasperPrint imprimir = JasperFillManager
					.fillReport("C:\\Users\\User\\JaspersoftWorkspace\\MyReports\\Relatório ".concat(fonteDeDados)
							.concat(".jasper"), null, ds);

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

	public static void gerarRelatorioEmWord(String fonteDeDados, int idPedido) {
		try {
			criarArquivoCsv(fonteDeDados, idPedido);

			JRDataSource ds = new JRCsvDataSource("C:\\Users\\User\\Desktop\\meuArquivo.csv");

			File arquivoModelo = new File("C:\\Users\\User\\JaspersoftWorkspace\\MyReports\\Relatório "
					.concat(fonteDeDados).concat(".jasper"));

			if (!arquivoModelo.exists()) {
				JasperCompileManager.compileReportToFile("C:\\Users\\User\\JaspersoftWorkspace\\MyReports\\Relatório "
						.concat(fonteDeDados).concat(".jrxml"));
			}

			JasperPrint imprimir = JasperFillManager
					.fillReport("C:\\Users\\User\\JaspersoftWorkspace\\MyReports\\Relatório ".concat(fonteDeDados)
							.concat(".jasper"), null, ds);

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

	private static void criarArquivoCsv(String fonteDeDados, int idPedido) {
		try {
			FileWriter escritorDeArquivos = new FileWriter("C:\\Users\\User\\Desktop\\dadosRelatorio.csv");

			if (fonteDeDados.contains("Cliente")) {
				List<ItemPedidoCliente> lista = new ItemPedidoClienteDao().retornarLista(idPedido);

				for (ItemPedidoCliente itemPedidoCliente : lista) {
					String nomeProduto = new ProdutoDao().retornar(itemPedidoCliente.getProdutoId()).getDescricao();
					String cliente = new ClienteDao().retornar(new PedidoClienteDao().retornar(idPedido).getClienteId())
							.getNome();

					escritorDeArquivos.write(String.format("%d;%s;%d;%s;\n", itemPedidoCliente.getId(), nomeProduto,
							itemPedidoCliente.getQuantidade(), cliente));
				}
			} else {
				List<ItemPedidoFornecedor> lista = new ItemPedidoFornecedorDao().retornarLista(idPedido);

				for (ItemPedidoFornecedor itemPedidoFornecedor : lista) {
					String nomeProduto = new ProdutoDao().retornar(itemPedidoFornecedor.getProdutoId()).getDescricao();
					String cliente = new FornecedorDao()
							.retornar(new PedidoFornecedorDao().retornar(idPedido).getFornecedorId())
							.getNome_fantasia();

					escritorDeArquivos.write(String.format("%d;%s;%d;%s;\n", itemPedidoFornecedor.getId(), nomeProduto,
							itemPedidoFornecedor.getQuantidade(), cliente));
				}
			}
			
			escritorDeArquivos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
