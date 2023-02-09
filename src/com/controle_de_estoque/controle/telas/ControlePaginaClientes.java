package com.controle_de_estoque.controle.telas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
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

import com.controle_de_estoque.model.dao.ClienteDao;
import com.controle_de_estoque.model.dao.EnderecoDao;
import com.controle_de_estoque.model.dao.TelefoneDao;
import com.controle_de_estoque.model.entity.Cliente;
import com.controle_de_estoque.model.entity.Endereco;
import com.controle_de_estoque.model.entity.Telefone;
import com.controle_de_estoque.visualizacao.PopUpErro;

public class ControlePaginaClientes {
	public static void imprimir() {
		try {

			criarArquivoParaImpressao();

			PrintService[] servicosDeImpressao = PrintServiceLookup
					.lookupPrintServices(DocFlavor.INPUT_STREAM.AUTOSENSE, null);

			DocFlavor tipoDeArquivo = DocFlavor.INPUT_STREAM.AUTOSENSE;

			Doc documento = new SimpleDoc(new FileInputStream(new File("C:\\Users\\User\\Documents\\clientes.txt")),
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
		List<Cliente> clientes = retornarLista();

		try {
			FileWriter escritor = new FileWriter("C:\\Users\\User\\Documents\\clientes.txt");

			for (Cliente cliente : clientes) {
				escritor.write(String.format("%d | %s | %s | %s | %d | %d\n", cliente.getId(), cliente.getNome(),
						cliente.getCpf(), cliente.getEmail(), cliente.getEnderecoId(), cliente.getTelefoneId()));
			}

			escritor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Cliente> retornarLista() {
		List<Cliente> lista = null;
		try {
			lista = new ClienteDao().retornarLista();
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
		return lista;
	}

	public static String retornarEndereco(int id) {
		String stringEndereco = "";
		try {
			Endereco endereco = new EnderecoDao().retornar(id);

			stringEndereco = endereco.getRua();
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
		return stringEndereco;
	}

	public static String retornarTelefone(int id) {
		String stringTelefone = "";
		try {
			Telefone telefone = new TelefoneDao().retornar(id);

			stringTelefone = telefone.getNumero();
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
		return stringTelefone;
	}
}
