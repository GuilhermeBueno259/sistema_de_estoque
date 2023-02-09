package com.controle_de_estoque.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.controle_de_estoque.model.conexao.FabricaDeConexao;
import com.controle_de_estoque.model.entity.ItemPedidoCliente;

public class ItemPedidoClienteDao implements Dao<ItemPedidoCliente> {

	Connection conexao;

	public ItemPedidoClienteDao() {
		conexao = FabricaDeConexao.getConexao();
	}

	@Override
	public boolean inserir(ItemPedidoCliente t) throws SQLException {
		String comando = "INSERT INTO itempedidocliente (quantidade, pedidocliente_id, produto_id) VALUES (?, ?, ?)";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, t.getQuantidade());
		statement.setInt(2, t.getPedidoClienteId());
		statement.setInt(3, t.getProdutoId());

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public boolean remover(int id) throws SQLException {
		String comando = "DELETE FROM itempedidocliente WHERE id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		int retorno = statement.executeUpdate();

		return retorno > 0;
	}

	@Override
	public boolean alterar(ItemPedidoCliente t) throws SQLException {
		String comando = "UPDATE itempedidocliente SET quantidade = ?, pedidocliente_id = ?, produto_id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, t.getQuantidade());
		statement.setInt(4, t.getPedidoClienteId());
		statement.setInt(2, t.getProdutoId());

		int retorno = statement.executeUpdate();

		return retorno > 0;
	}

	@Override
	public ItemPedidoCliente retornar(int id) throws SQLException {
		String comando = "SELECT * FROM itempedidocliente WHERE Id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		ResultSet retorno = statement.executeQuery();

		ItemPedidoCliente item = new ItemPedidoCliente();

		if (retorno.next()) {
			item.setId(id);
			item.setProdutoId(retorno.getInt("produto_id"));
			item.setQuantidade(retorno.getInt("quantidade"));
			item.setPedidoClienteId(retorno.getInt("pedidocliente_id"));
		}

		return item;
	}

	public List<ItemPedidoCliente> retornarLista(int id) throws SQLException {
		List<ItemPedidoCliente> lista = new ArrayList<ItemPedidoCliente>();
		String comando = "SELECT * FROM itempedidocliente WHERE Pedidocliente_id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		ResultSet retorno = statement.executeQuery(comando);

		while (retorno.next()) {
			lista.add(new ItemPedidoCliente(retorno.getInt("id"), retorno.getInt("quantidade"),
					retorno.getInt("pedidocliente_id"), retorno.getInt("produto_id")));
		}

		statement.close();
		retorno.close();

		return lista;
	}

	public boolean removerPorIdDoPedido(int id) throws SQLException {
		String comando = "DELETE FROM itempedidocliente WHERE Pedidocliente_id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public List<ItemPedidoCliente> retornarLista() throws SQLException {
		return null;
	}
}
