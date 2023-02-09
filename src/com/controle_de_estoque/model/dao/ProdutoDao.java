package com.controle_de_estoque.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.controle_de_estoque.model.conexao.FabricaDeConexao;
import com.controle_de_estoque.model.entity.Produto;

public class ProdutoDao implements Dao<Produto> {

	Connection conexao;

	public ProdutoDao() {
		conexao = FabricaDeConexao.getConexao();
	}

	@Override
	public boolean inserir(Produto t) throws SQLException {
		String comando = "INSERT INTO produto (Descricao, Categoria, Preco, Quantidade) VALUES (?, ?, ?, ?)";

		PreparedStatement statement = conexao.prepareStatement(comando);

		statement.setString(1, t.getDescricao());
		statement.setString(2, t.getCategoria());
		statement.setFloat(3, t.getPreco());
		statement.setInt(4, t.getQuantidade());

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public boolean remover(int id) throws SQLException {
		String comando = "DELETE FROM produto WHERE id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public boolean alterar(Produto t) throws SQLException {
		String comando = "UPDATE produto SET Descricao = ?, Categoria = ?, Preco = ?, Quantidade = ? WHERE id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setString(1, t.getDescricao());
		statement.setString(2, t.getCategoria());
		statement.setFloat(3, t.getPreco());
		statement.setInt(4, t.getQuantidade());
		statement.setInt(5, t.getId());

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public Produto retornar(int id) throws SQLException {
		String comando = "SELECT * FROM produto WHERE id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		ResultSet retorno = statement.executeQuery();

		Produto produto = new Produto();

		if (retorno.next()) {
			produto.setId(id);
			produto.setDescricao(retorno.getString("Descricao"));
			produto.setCategoria(retorno.getString("Categoria"));
			produto.setPreco(retorno.getFloat("Preco"));
			produto.setQuantidade(retorno.getInt("Quantidade"));
		}

		statement.close();
		retorno.close();

		return produto;
	}

	@Override
	public List<Produto> retornarLista() throws SQLException {
		String comando = "SELECT * FROM Produto";
		List<Produto> lista = new ArrayList<Produto>();

		PreparedStatement statement = conexao.prepareStatement(comando);
		ResultSet retorno = statement.executeQuery();

		while (retorno.next()) {
			lista.add(new Produto(retorno.getInt("Id") ,retorno.getString("Descricao"), retorno.getString("Categoria"),
					retorno.getFloat("Preco"), retorno.getInt("Quantidade")));
		}

		statement.close();
		retorno.close();

		return lista;
	}

	public int retornaIdPorNome(String nome) throws SQLException {
		String comando = "SELECT id FROM produto WHERE descricao = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setString(1, nome);

		ResultSet retorno = statement.executeQuery();

		int variavelTemporaria = 0;

		if (retorno.next()) {
			variavelTemporaria = retorno.getInt("id");
		}

		return variavelTemporaria;
	}

	public float retornarPrecoPorId(int id) throws SQLException {
		String comando = "SELECT preco FROM produto WHERE id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		ResultSet retorno = statement.executeQuery();

		float variavelTemporaria = 0F;

		if (!retorno.next()) {
			variavelTemporaria = retorno.getFloat("preco");
		}
		return variavelTemporaria;
	}
}
