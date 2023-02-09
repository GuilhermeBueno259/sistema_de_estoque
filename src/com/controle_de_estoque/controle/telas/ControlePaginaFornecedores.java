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

import com.controle_de_estoque.model.dao.EnderecoDao;
import com.controle_de_estoque.model.dao.FornecedorDao;
import com.controle_de_estoque.model.entity.Endereco;
import com.controle_de_estoque.model.entity.Fornecedor;
import com.controle_de_estoque.visualizacao.PopUpErro;

public class ControlePaginaFornecedores {
	public static void imprimir() {
		try {

			criarArquivoParaImpressao();

			PrintService[] servicosDeImpressao = PrintServiceLookup
					.lookupPrintServices(DocFlavor.INPUT_STREAM.AUTOSENSE, null);

			DocFlavor tipoDeArquivo = DocFlavor.INPUT_STREAM.AUTOSENSE;

			Doc documento = new SimpleDoc(new FileInputStream(new File("C:\\Users\\User\\Documents\\fornecedores.txt")),
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
		List<Fornecedor> fornecedores = retornarLista();

		try {
			FileWriter escritor = new FileWriter("C:\\Users\\User\\Documents\\fornecedores.txt");

			for (Fornecedor fornecedor : fornecedores) {
				escritor.write(String.format("%d | %s | %s | %s | %s\n", fornecedor.getId(),
						fornecedor.getNome_fantasia(), fornecedor.getCpf_cnpj(), fornecedor.getEmail(),
						retorarEndereco(fornecedor.getEnderecoId())));
			}

			escritor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Fornecedor> retornarLista() {
		List<Fornecedor> lista = null;
		try {
			lista = new FornecedorDao().retornarLista();
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
		return lista;
	}

	private static String retorarEndereco(int id) {
		String retorno = "";

		try {
			EnderecoDao enderecoDao = new EnderecoDao();
			Endereco endereco = enderecoDao.retornar(id);

			retorno = String.format("%s | %s | %s", endereco.getRua(), endereco.getCidade(), endereco.getUf());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retorno;
	}
}
