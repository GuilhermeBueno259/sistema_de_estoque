package com.controle_de_estoque.controle.telas;

import com.controle_de_estoque.model.dao.FornecedorDao;
import com.controle_de_estoque.model.entity.Fornecedor;
import com.controle_de_estoque.visualizacao.PopUpErro;

public class ControlePaginaAlterarFornecedor {
	public static void alterarFornecedor(Fornecedor fornecedor) {
		try {
			FornecedorDao fornecedorDao = new FornecedorDao();

			if (fornecedorDao.retornar(fornecedor.getId()) == null) {
				fornecedorDao.inserir(fornecedor);
			} else {
				fornecedorDao.alterar(fornecedor);
			}
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}
}
