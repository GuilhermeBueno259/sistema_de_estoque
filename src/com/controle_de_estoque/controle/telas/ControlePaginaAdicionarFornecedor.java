package com.controle_de_estoque.controle.telas;

import com.controle_de_estoque.model.dao.FornecedorDao;
import com.controle_de_estoque.model.entity.Fornecedor;
import com.controle_de_estoque.visualizacao.PopUpErro;
import com.controle_de_estoque.visualizacao.PopUpInformacao;

public class ControlePaginaAdicionarFornecedor {
	public static void adicionarFornecedor(Fornecedor fornecedor) {
		FornecedorDao fornecedorDao = new FornecedorDao();

		try {
			if (fornecedorDao.retornarPorCpfCnpj(fornecedor.getCpf_cnpj()) != null) {
				new PopUpInformacao("Fornecedor Existente", "Este forencedor já existe no banco de dados");
			} else {
				fornecedorDao.inserir(fornecedor);
			}
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}
}
