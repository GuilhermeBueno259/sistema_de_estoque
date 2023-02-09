package com.controle_de_estoque.controle.telas;

import com.controle_de_estoque.model.dao.ProdutoDao;
import com.controle_de_estoque.model.entity.Produto;
import com.controle_de_estoque.visualizacao.PopUpErro;
import com.controle_de_estoque.visualizacao.PopUpInformacao;

public class ControlePaginaAdicionarProduto {
	public static void adicionarProduto(String descricao, String categoria, float preco, int quantidade) {
		try {
			ProdutoDao produtoDao = new ProdutoDao();

			if (produtoDao.retornaIdPorNome(descricao) == 0) {
				if (produtoDao.inserir(new Produto(descricao, categoria, preco, quantidade))) {
					new PopUpInformacao("Sucesso", "Produto inserido no estoque com sucesso");
				} else {
					new PopUpInformacao("Falha", "O produto n√£o pode ser inserido no estoque");
				}
			} else {
				new PopUpInformacao("Falha", "O produto j· existe no estoque");
			}
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}
}
