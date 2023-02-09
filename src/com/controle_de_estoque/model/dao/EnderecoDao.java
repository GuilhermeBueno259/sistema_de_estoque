package com.controle_de_estoque.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.controle_de_estoque.model.conexao.FabricaDeConexao;
import com.controle_de_estoque.model.entity.Endereco;

public class EnderecoDao implements Dao<Endereco> {

	Connection conexao = FabricaDeConexao.getConexao();

	@Override
	public boolean inserir(Endereco t) throws SQLException {
		String comando = "INSERT INTO endereco (Rua, UF, Cidade) VALUES (?, ?, ?)";

		PreparedStatement statement = conexao.prepareStatement(comando);

		statement.setString(1, t.getRua());
		statement.setString(2, t.getUf());
		statement.setString(3, t.getCidade());

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public boolean remover(int id) throws SQLException {
		String comando = "DELETE FROM endereco WHERE id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public boolean alterar(Endereco t) throws SQLException {
		String comando = "UPDATE fornecedor SET Rua = ?, UF = ?, Cidade = ? WHERE id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setString(1, t.getRua());
		statement.setString(2, t.getUf());
		statement.setString(3, t.getCidade());
		statement.setInt(4, t.getId());

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public Endereco retornar(int id) throws SQLException {
		String comando = "SELECT * FROM endereco WHERE id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		ResultSet retorno = statement.executeQuery();

		Endereco endereco = new Endereco();

		if (retorno.next()) {
			endereco.setId(retorno.getInt("Id"));
			endereco.setRua(retorno.getString("Rua"));
			endereco.setCidade(retorno.getString("UF"));
			endereco.setUf(retorno.getString("Cidade"));
		}

		statement.close();
		retorno.close();

		return endereco;
	}

	@Override
	public List<Endereco> retornarLista() throws SQLException {
		String comando = "SELECT * FROM fornecedor";
		List<Endereco> lista = new ArrayList<Endereco>();

		PreparedStatement statement = conexao.prepareStatement(comando);
		ResultSet retorno = statement.executeQuery();

		while (retorno.next()) {
			lista.add(new Endereco(retorno.getString("Rua"), retorno.getString("UF"), retorno.getString("Cidade")));
		}

		statement.close();
		retorno.close();

		return lista;
	}

	public Endereco retornarEnderecoPorRua(String rua) throws SQLException {
		String comando = "SELECT * FROM endereco WHERE rua = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setString(1, rua);
		ResultSet retorno = statement.executeQuery();

		Endereco endereco = new Endereco();

		if (retorno.next()) {
			endereco.setId(retorno.getInt("Id"));
			endereco.setRua(retorno.getString("Rua"));
			endereco.setCidade(retorno.getString("Cidade"));
			endereco.setUf(retorno.getString("UF"));
		}

		statement.close();
		retorno.close();

		return endereco;
	}
}