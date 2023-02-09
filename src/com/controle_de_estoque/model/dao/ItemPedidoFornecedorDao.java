package com.controle_de_estoque.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.controle_de_estoque.model.conexao.FabricaDeConexao;
import com.controle_de_estoque.model.entity.ItemPedidoFornecedor;

public class ItemPedidoFornecedorDao implements Dao<ItemPedidoFornecedor> {

	Connection conexao;

	public ItemPedidoFornecedorDao() {
		conexao = FabricaDeConexao.getConexao();
	}

	@Override
	public boolean inserir(ItemPedidoFornecedor t) throws SQLException {
		String comando = "INSERT INTO item_pedidofornecedor (quantidade, pedidofornecedor_id, produto_id) VALUES (?, ?, ?)";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, t.getQuantidade());
		statement.setInt(2, t.getPedidoFornecedorId());
		statement.setInt(3, t.getProdutoId());

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public boolean remover(int id) throws SQLException {
		String comando = "DELETE FROM item_pedidofornecedor WHERE id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public boolean alterar(ItemPedidoFornecedor t) throws SQLException {
		String comando = "UPDATE item_pedidofornecedor SET quantidade = ?, pedidofornecedor_id = ?, produto_id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, t.getQuantidade());
		statement.setInt(2, t.getPedidoFornecedorId());
		statement.setInt(3, t.getProdutoId());

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public ItemPedidoFornecedor retornar(int id) throws SQLException {
		String comando = "SELECT * FROM item_pedidofornecedor WHERE Id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		ResultSet retorno = statement.executeQuery();

		ItemPedidoFornecedor item = new ItemPedidoFornecedor();

		if (retorno.next()) {
			item.setId(id);
			item.setProdutoId(retorno.getInt("produto_id"));
			item.setQuantidade(retorno.getInt("quantidade"));
			item.setPedidoFornecedorId(retorno.getInt("pedidocliente_id"));
		}

		statement.close();
		retorno.close();

		return item;
	}

	public List<ItemPedidoFornecedor> retornarLista(int idPedidoFornecedor) throws SQLException {
		List<ItemPedidoFornecedor> lista = new ArrayList<ItemPedidoFornecedor>();
		String comando = "SELECT * FROM item_pedidofornecedor WHERE Pedidofornecedor_id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, idPedidoFornecedor);

		ResultSet retorno = statement.executeQuery(comando);

		while (retorno.next()) {
			lista.add(new ItemPedidoFornecedor(retorno.getInt("id"), retorno.getInt("quantidade"),
					retorno.getInt("pedidofornecedor_id"), retorno.getInt("produto_id")));
		}

		statement.close();
		retorno.close();

		return lista;
	}

	public boolean removerPorIdDoPedido(int id) throws SQLException {
		String comando = "DELETE FROM item_pedidofornecedor WHERE Pedidofornecedor_Id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public List<ItemPedidoFornecedor> retornarLista() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
