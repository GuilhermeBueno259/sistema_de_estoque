package com.controle_de_estoque.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.controle_de_estoque.model.conexao.FabricaDeConexao;
import com.controle_de_estoque.model.entity.Cliente;

public class ClienteDao implements Dao<Cliente> {

	private Connection conexao;

	public ClienteDao() {
		conexao = FabricaDeConexao.getConexao();
	}

	@Override
	public boolean inserir(Cliente t) throws SQLException {
		String comando = "INSERT INTO cliente (Nome, CPF, Email, Endereco_id, Telefone_id) VALUES (?, ?, ?, ?, ?)";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setString(1, t.getNome());
		statement.setString(2, t.getCpf());
		statement.setString(3, t.getEmail());
		statement.setInt(4, t.getEnderecoId());
		statement.setInt(5, t.getTelefoneId());

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public boolean remover(int id) throws SQLException {
		String comando = "DELETE FROM cliente WHERE id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public boolean alterar(Cliente t) throws SQLException {
		String comando = "UPDATE cliente SET Nome = ?, CPF = ?, Email = ?, Endereco_id = ?, Telefone_id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setString(1, t.getNome());
		statement.setString(2, t.getCpf());
		statement.setString(3, t.getEmail());
		statement.setInt(4, t.getEnderecoId());
		statement.setInt(5, t.getTelefoneId());

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public Cliente retornar(int id) throws SQLException {
		String comando = "SELECT * FROM cliente WHERE Id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		ResultSet retorno = statement.executeQuery();

		Cliente cliente = new Cliente();

		if (retorno.next()) {
			cliente.setId(retorno.getInt("Id"));
			cliente.setNome(retorno.getString("Nome"));
			cliente.setCpf(retorno.getString("CPF"));
			cliente.setEmail(retorno.getString("Email"));
			cliente.setEnderecoId(retorno.getInt("Endereco_id"));
			cliente.setTelefoneId(retorno.getInt("Telefone_id"));
		}

		statement.close();

		return cliente;
	}

	@Override
	public List<Cliente> retornarLista() throws SQLException {
		List<Cliente> lista = new ArrayList<Cliente>();
		String comando = "SELECT * FROM cliente";

		PreparedStatement statement = conexao.prepareStatement(comando);

		ResultSet retorno = statement.executeQuery(comando);

		while (retorno.next()) {
			lista.add(new Cliente(retorno.getInt("Id"), retorno.getString("Nome"), retorno.getString("CPF"),
					retorno.getString("Email"), retorno.getInt("Endereco_id"), retorno.getInt("Telefone_id")));
		}

		statement.close();
		retorno.close();

		return lista;
	}

	public Cliente retornarPorCpf(String cpf) throws SQLException {
		String comando = "SELECT * FROM cliente WHERE CPF = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setString(1, cpf);

		ResultSet retorno = statement.executeQuery();

		Cliente cliente = new Cliente();

		if (retorno.next()) {
			cliente.setId(retorno.getInt("Id"));
			cliente.setNome(retorno.getString("Nome"));
			cliente.setCpf(retorno.getString("CPF"));
			cliente.setEmail(retorno.getString("Email"));
			cliente.setEnderecoId(retorno.getInt("Endereco_id"));
			cliente.setTelefoneId(retorno.getInt("Telefone_id"));
		}

		statement.close();
		retorno.close();

		return cliente;
	}
}
