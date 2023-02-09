package com.controle_de_estoque.controle.telas;

import com.controle_de_estoque.model.dao.ProdutoDao;
import com.controle_de_estoque.visualizacao.PopUpErro;
import com.controle_de_estoque.visualizacao.PopUpInformacao;

public class ControlePaginaRemoverProduto {
	public static void removerProduto(String id) {
		try {
			ProdutoDao produtoDao = new ProdutoDao();
			int idConvertido = Integer.parseInt(id);

			if (produtoDao.retornar(idConvertido) == null) {
				new PopUpInformacao("Produto inexistente", "O produto não existe");
			} else {
				if (produtoDao.remover(idConvertido)) {
					new PopUpInformacao("Produto removido", "O produto foi removido com sucesso");
				} else {
					new PopUpInformacao("Falha na operação", "Não foi possível remover o produto");
				}
			}
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}
}
