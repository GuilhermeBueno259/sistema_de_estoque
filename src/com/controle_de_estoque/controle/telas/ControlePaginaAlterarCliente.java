package com.controle_de_estoque.controle.telas;

import com.controle_de_estoque.model.dao.ClienteDao;
import com.controle_de_estoque.model.entity.Cliente;
import com.controle_de_estoque.visualizacao.PopUpErro;

public class ControlePaginaAlterarCliente {
	public static void verificarCliente(Cliente cliente) {
		try {
			ClienteDao clienteDao = new ClienteDao();

			if (clienteDao.retornar(cliente.getId()) != null) {
				clienteDao.alterar(cliente);
			} else {
				clienteDao.inserir(cliente);
			}
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}
}
