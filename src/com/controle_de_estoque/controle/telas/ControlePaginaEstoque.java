package com.controle_de_estoque.controle.telas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import com.controle_de_estoque.model.dao.ProdutoDao;
import com.controle_de_estoque.model.entity.Produto;
import com.controle_de_estoque.visualizacao.PopUpErro;

public class ControlePaginaEstoque {
	public static void imprimir() {
		try {

			criarArquivoParaImpressao();

			PrintService[] servicosDeImpressao = PrintServiceLookup
					.lookupPrintServices(DocFlavor.INPUT_STREAM.AUTOSENSE, null);

			DocFlavor tipoDeArquivo = DocFlavor.INPUT_STREAM.AUTOSENSE;

			Doc documento = new SimpleDoc(new FileInputStream(new File("C:\\Users\\User\\Documents\\estoque.txt")),
					tipoDeArquivo, null);

			PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();

			PrintService servicoDeImpressao = ServiceUI.printDialog(null, 300, 200, servicosDeImpressao,
					PrintServiceLookup.lookupDefaultPrintService(), tipoDeArquivo, printRequestAttributeSet);

			if (servicoDeImpressao != null) {
				DocPrintJob docPrintJob = servicoDeImpressao.createPrintJob();
				// Mandar imprimir
				docPrintJob.print(documento, printRequestAttributeSet);
			}

		} catch (Exception e) {
			e.printStackTrace();
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}

	private static void criarArquivoParaImpressao() {
		List<Produto> estoque = retornarListaDeProdutos();

		try {
			FileWriter escritor = new FileWriter("C:\\Users\\User\\Documents\\estoque.txt");

			for (Produto produto : estoque) {
				escritor.write(String.format("%d | %s | %s | %d | R$%.2f\n", produto.getId(), produto.getDescricao(),
						produto.getCategoria(), produto.getQuantidade(), produto.getPreco()));
			}

			escritor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Produto> retornarListaDeProdutos() {
		List<Produto> lista = null;
		try {
			lista = new ProdutoDao().retornarLista();
		} catch (SQLException e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
		return lista;
	}
}
