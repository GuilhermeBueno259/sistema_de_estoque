package com.controle_de_estoque.controle.telas;

import java.sql.SQLException;

import com.controle_de_estoque.model.dao.ClienteDao;
import com.controle_de_estoque.model.dao.EnderecoDao;
import com.controle_de_estoque.model.dao.TelefoneDao;
import com.controle_de_estoque.model.entity.Cliente;
import com.controle_de_estoque.model.entity.Endereco;
import com.controle_de_estoque.model.entity.Telefone;
import com.controle_de_estoque.visualizacao.PopUpErro;

public class ControlePaginaAdicionarCliente {
	private static Telefone telefone;
	private static Endereco endereco;

	public static void adicionarCliente(String nome, String cpf, String email, String stringEndereco,
			String numeroDeTelefone) {
		try {
			verificarEndereco(stringEndereco.split(" - "));

			verificarTelefone(numeroDeTelefone);

			new ClienteDao().inserir(new Cliente(nome, cpf, email, endereco.getId(), telefone.getId()));
		} catch (Exception e) {
			new PopUpErro("Erro", String.format("Erro: %s", e.getMessage()));
		}
	}

	private static void verificarTelefone(String numero) throws SQLException {
		TelefoneDao telefoneDao = new TelefoneDao();

		Telefone telefoneRetorno = telefoneDao.retornarPorNumero(numero);

		if (telefoneRetorno == null) {
			Telefone novoTelefone = new Telefone(numero, "Celular");
			telefoneDao.inserir(novoTelefone);
			telefone = novoTelefone;
		} else {
			telefone = telefoneRetorno;
		}
	}

	private static void verificarEndereco(String[] informacoesDoEndereco) throws SQLException {
		EnderecoDao enderecoDao = new EnderecoDao();

		Endereco enderecoRetorno = enderecoDao.retornarEnderecoPorRua(informacoesDoEndereco[0]);

		if (enderecoRetorno == null) {
			Endereco novoEndereco = new Endereco(informacoesDoEndereco[0], informacoesDoEndereco[1],
					informacoesDoEndereco[2]);
			enderecoDao.inserir(novoEndereco);
			endereco = novoEndereco;
		} else {
			endereco = enderecoRetorno;
		}
	}
}
