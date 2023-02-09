package com.controle_de_estoque.controle;

import com.controle_de_estoque.model.dao.EnderecoDao;
import com.controle_de_estoque.model.dao.TelefoneDao;
import com.controle_de_estoque.visualizacao.PopUpErro;

public class ControleEntidades {

	public static int retornarTelefoneId(String numeroTelefone) {
		int id = 0;
		try {
			id = new TelefoneDao().retornarPorNumero(numeroTelefone).getId();
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
		return id;
	}

	public static int retornarEnderecoId(String rua) {
		int id = 0;
		try {
			id = new EnderecoDao().retornarEnderecoPorRua(rua).getId();
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
		return id;
	}
}
