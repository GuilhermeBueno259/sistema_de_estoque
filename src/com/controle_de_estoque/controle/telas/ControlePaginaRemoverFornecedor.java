package com.controle_de_estoque.controle.telas;

import com.controle_de_estoque.model.dao.FornecedorDao;
import com.controle_de_estoque.visualizacao.PopUpErro;
import com.controle_de_estoque.visualizacao.PopUpInformacao;

public class ControlePaginaRemoverFornecedor {
	public static void removerFornecedor(String id) {
		try {
			FornecedorDao fornecedorDao = new FornecedorDao();
			int idConvertido = Integer.parseInt(id);

			if (fornecedorDao.retornar(idConvertido) == null) {
				new PopUpInformacao("Fornecedor inexistente", "Este fornecedor não existe");
			} else {
				fornecedorDao.remover(idConvertido);
			}
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}
}
