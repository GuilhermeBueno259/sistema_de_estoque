package com.controle_de_estoque.controle.telas;

import com.controle_de_estoque.model.dao.ProdutoDao;
import com.controle_de_estoque.model.entity.Produto;
import com.controle_de_estoque.visualizacao.PopUpErro;
import com.controle_de_estoque.visualizacao.PopUpInformacao;

public class ControlePaginaAlterarProduto {
	public static void alterarProduto(String id, String descricao, String categoria, float preco, int quantidade) {
		try {
			ProdutoDao produtoDao = new ProdutoDao();
			int idConvertido = Integer.parseInt(id);

			if (produtoDao.retornar(idConvertido) == null) {
				new PopUpInformacao("Produto inexistente", "O produto não existe");
			} else {
				if (produtoDao.alterar(new Produto(idConvertido, descricao, categoria, preco, quantidade))) {
					new PopUpInformacao("Sucesso", "Produto alterado com sucesso");
				} else {
					new PopUpInformacao("Falha", "Produto não pode ser alterado");
				}
			}
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}
}
