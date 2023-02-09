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
import com.controle_de_estoque.model.dao.PedidoClienteDao;
import com.controle_de_estoque.model.entity.PedidoCliente;
import com.controle_de_estoque.visualizacao.PopUpErro;

public class ControlePaginaPedidoCliente {
	public static void imprimir() {
		try {

			criarArquivoParaImpressao();

			PrintService[] servicosDeImpressao = PrintServiceLookup
					.lookupPrintServices(DocFlavor.INPUT_STREAM.AUTOSENSE, null);

			DocFlavor tipoDeArquivo = DocFlavor.INPUT_STREAM.AUTOSENSE;

			Doc documento = new SimpleDoc(
					new FileInputStream(new File("C:\\Users\\User\\Documents\\pedidos saída.txt")), tipoDeArquivo,
					null);

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
		List<PedidoCliente> pedidosSaida = retornarLista();

		try {
			FileWriter escritor = new FileWriter("C:\\Users\\User\\Documents\\pedidos saída.txt");

			for (PedidoCliente pedidoCliente : pedidosSaida) {
				escritor.write(String.format("%d | %s | %s | %s | R$%.2f\n", pedidoCliente.getId(),
						pedidoCliente.getNfE(), retornarNomeCliente(pedidoCliente.getClienteId()),
						pedidoCliente.getData().toString(), pedidoCliente.getPrecoTotal()));
			}

			escritor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<PedidoCliente> retornarLista() {
		List<PedidoCliente> lista = null;
		try {
			lista = new PedidoClienteDao().retornarLista();
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
		return lista;
	}

	public static String retornarNomeCliente(int idCliente) {
		String nomeCliente = "";
		try {
			nomeCliente = new ClienteDao().retornar(idCliente).getNome();
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
		return nomeCliente;
	}
}
