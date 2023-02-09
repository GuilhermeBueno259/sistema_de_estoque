package com.controle_de_estoque.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.controle_de_estoque.model.conexao.FabricaDeConexao;
import com.controle_de_estoque.model.entity.PedidoFornecedor;

public class PedidoFornecedorDao implements Dao<PedidoFornecedor> {

	Connection conexao;

	public PedidoFornecedorDao() {
		conexao = FabricaDeConexao.getConexao();
	}

	@Override
	public boolean inserir(PedidoFornecedor t) throws SQLException {
		String comando = "INSERT INTO pedidofornecedor (PrecoTotal, Data, nf_e, Fornecedor_id) VALUES (?, ?, ?, ?)";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setFloat(1, t.getPrecoTotal());
		statement.setDate(2, t.getData());
		statement.setString(3, t.getNfE());
		statement.setInt(4, t.getFornecedorId());

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public boolean remover(int id) throws SQLException {
		String comando = "DELETE FROM pedidofornecedor WHERE id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public boolean alterar(PedidoFornecedor t) throws SQLException {
		String comando = "UPDATE pedidofornecedor SET precototal = ?, data = ?, nf_e = ?, Fornecedor_id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setFloat(1, t.getPrecoTotal());
		statement.setDate(2, t.getData());
		statement.setString(3, t.getNfE());
		statement.setInt(4, t.getFornecedorId());

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public PedidoFornecedor retornar(int id) throws SQLException {
		String comando = "SELECT * FROM pedidofornecedor WHERE Id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		ResultSet retorno = statement.executeQuery();

		PedidoFornecedor pedido = new PedidoFornecedor();

		if (retorno.next()) {
			pedido.setId(id);
			pedido.setPrecoTotal(retorno.getFloat("PrecoTotal"));
			pedido.setData(retorno.getDate("data"));
			pedido.setNfE(retorno.getString("nf_e"));
			pedido.setFornecedorId(retorno.getInt("Fornecedor_id"));
		}

		statement.close();
		retorno.close();

		return pedido;
	}

	@Override
	public List<PedidoFornecedor> retornarLista() throws SQLException {
		List<PedidoFornecedor> lista = new ArrayList<PedidoFornecedor>();
		String comando = "SELECT * FROM pedidofornecedor";

		PreparedStatement statement = conexao.prepareStatement(comando);

		ResultSet retorno = statement.executeQuery(comando);

		while (retorno.next()) {
			lista.add(new PedidoFornecedor(retorno.getInt("id"), retorno.getFloat("PrecoTotal"),
					retorno.getDate("data"), retorno.getString("nf_e"), retorno.getInt("Fornecedor_id")));
		}

		statement.close();
		retorno.close();

		return lista;
	}

	public int retornarIdPorData(Date data) throws SQLException {
		String comando = "SELECT * FROM pedidofornecedor WHERE data = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setDate(1, data);

		ResultSet retorno = statement.executeQuery();

		int idRetornado = 0;

		if (retorno.next()) {
			idRetornado = retorno.getInt("id");
		}

		statement.close();
		retorno.close();

		return idRetornado;
	}

	public PedidoFornecedor retornarPorNfe(String nfe) throws SQLException {
		String comando = "SELECT * FROM pedidofornecedor WHERE nfe = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setString(1, nfe);

		ResultSet retorno = statement.executeQuery();

		PedidoFornecedor pedido = new PedidoFornecedor();

		if (retorno.next()) {
			pedido.setId(retorno.getInt("Id"));
			pedido.setPrecoTotal(retorno.getFloat("PrecoTotal"));
			pedido.setData(retorno.getDate("Data"));
			pedido.setNfE(retorno.getString("nf_e"));
			pedido.setFornecedorId(retorno.getInt("Fornecedor_id"));
		}

		statement.close();
		retorno.close();

		return pedido;
	}
}
