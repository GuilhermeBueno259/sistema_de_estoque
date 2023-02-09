package com.controle_de_estoque.controle.telas;

import com.controle_de_estoque.model.dao.ClienteDao;
import com.controle_de_estoque.model.dao.EnderecoDao;
import com.controle_de_estoque.model.dao.TelefoneDao;
import com.controle_de_estoque.model.entity.Cliente;
import com.controle_de_estoque.visualizacao.PopUpErro;
import com.controle_de_estoque.visualizacao.PopUpInformacao;

public class ControlePaginaRemoverCliente {

	public static void removerCliente(int id) {
		try {
			ClienteDao clienteDao = new ClienteDao();
			EnderecoDao enderecoDao = new EnderecoDao();
			TelefoneDao telefoneDao = new TelefoneDao();

			Cliente cliente = new ClienteDao().retornar(id);

			if (clienteDao.remover(id) && enderecoDao.remover(cliente.getEnderecoId())
					&& telefoneDao.remover(cliente.getTelefoneId())) {
				new PopUpInformacao("Sucesso", "Cliente foi deletado com sucesso");
			} else {
				new PopUpInformacao("Erro", "Não foi possível remover o cliente");
			}
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}
}
