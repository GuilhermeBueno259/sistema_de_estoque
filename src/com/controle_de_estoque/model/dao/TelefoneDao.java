package com.controle_de_estoque.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.controle_de_estoque.model.conexao.FabricaDeConexao;
import com.controle_de_estoque.model.entity.Telefone;

public class TelefoneDao implements Dao<Telefone> {

	Connection conexao;

	public TelefoneDao() {
		conexao = FabricaDeConexao.getConexao();
	}

	@Override
	public boolean inserir(Telefone t) throws SQLException {
		String comando = "INSERT INTO telefone (Numero, Tipo) VALUES (?, ?)";

		PreparedStatement statement = conexao.prepareStatement(comando);

		statement.setString(1, t.getNumero());
		statement.setString(2, t.getTipo());

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public boolean remover(int id) throws SQLException {
		String comando = "DELETE FROM telefone WHERE id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public boolean alterar(Telefone t) throws SQLException {
		String comando = "UPDATE telefone SET Numero = ?, Tipo = ? WHERE id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setString(1, t.getNumero());
		statement.setString(2, t.getTipo());
		statement.setInt(3, t.getId());

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public Telefone retornar(int id) throws SQLException {
		String comando = "SELECT * FROM telefone WHERE id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		ResultSet retorno = statement.executeQuery();

		Telefone telefone = new Telefone();

		if (retorno.next()) {
			telefone.setId(id);
			telefone.setNumero(retorno.getString("Numero"));
			telefone.setTipo(retorno.getString("Tipo"));
		}

		statement.close();
		retorno.close();

		return telefone;
	}

	@Override
	public List<Telefone> retornarLista() throws SQLException {
		String comando = "SELECT * FROM Telefone";
		List<Telefone> lista = new ArrayList<Telefone>();

		PreparedStatement statement = conexao.prepareStatement(comando);
		ResultSet retorno = statement.executeQuery();

		while (retorno.next()) {
			lista.add(new Telefone(retorno.getInt("Id"), retorno.getString("Numero"), retorno.getString("Tipo")));
		}

		statement.close();
		retorno.close();

		return lista;
	}

	public Telefone retornarPorNumero(String numero) throws SQLException {

		String comando = "SELECT * FROM telefone WHERE telefone = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setString(1, numero);

		ResultSet retorno = statement.executeQuery();

		Telefone telefone = new Telefone();

		if (retorno.next()) {
			telefone.setId(retorno.getInt("Id"));
			telefone.setNumero(numero);
			telefone.setTipo(retorno.getString("Tipo"));
		}

		statement.close();
		retorno.close();

		return telefone;
	}
}
