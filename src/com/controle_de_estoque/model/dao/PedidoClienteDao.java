package com.controle_de_estoque.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.controle_de_estoque.model.conexao.FabricaDeConexao;
import com.controle_de_estoque.model.entity.PedidoCliente;

public class PedidoClienteDao implements Dao<PedidoCliente> {

	Connection conexao;

	public PedidoClienteDao() {
		conexao = FabricaDeConexao.getConexao();
	}

	@Override
	public boolean inserir(PedidoCliente t) throws SQLException {
		String comando = "INSERT INTO pedidocliente (PrecoTotal, Data, nf_e, Cliente_id) VALUES (?, ?, ?, ?)";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setFloat(1, t.getPrecoTotal());
		statement.setDate(2, t.getData());
		statement.setString(3, t.getNfE());
		statement.setInt(4, t.getClienteId());

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public boolean remover(int id) throws SQLException {
		String comando = "DELETE FROM pedidocliente WHERE id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public boolean alterar(PedidoCliente t) throws SQLException {
		String comando = "UPDATE pedidocliente SET precototal = ?, data = ?, nf_e = ?, Cliente_id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setFloat(1, t.getPrecoTotal());
		statement.setDate(2, t.getData());
		statement.setString(3, t.getNfE());
		statement.setInt(4, t.getClienteId());

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public PedidoCliente retornar(int id) throws SQLException {
		String comando = "SELECT * FROM pedidocliente WHERE Id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		ResultSet retorno = statement.executeQuery();

		PedidoCliente pedido = new PedidoCliente();

		if (retorno.next()) {
			pedido.setId(id);
			pedido.setPrecoTotal(retorno.getFloat("PrecoTotal"));
			pedido.setData(retorno.getDate("data"));
			pedido.setNfE(retorno.getString("nf_e"));
			pedido.setClienteId(retorno.getInt("Cliente_id"));
		}

		return pedido;
	}

	@Override
	public List<PedidoCliente> retornarLista() throws SQLException {
		List<PedidoCliente> lista = new ArrayList<PedidoCliente>();
		String comando = "SELECT * FROM pedidocliente";

		PreparedStatement statement = conexao.prepareStatement(comando);

		ResultSet retorno = statement.executeQuery(comando);

		while (retorno.next()) {
			lista.add(new PedidoCliente(retorno.getInt("id"), retorno.getFloat("PrecoTotal"), retorno.getDate("data"),
					retorno.getString("nf_e"), retorno.getInt("Cliente_id")));
		}

		statement.close();
		retorno.close();

		return lista;
	}

	public int retornarIdPorData(Date data) throws SQLException {
		String comando = "SELECT id FROM pedidocliente WHERE data = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setDate(1, data);

		ResultSet retorno = statement.executeQuery();

		int variavelTemporaria = 0;

		if (retorno.next()) {
			variavelTemporaria = retorno.getInt("id");
		}
		return variavelTemporaria;
	}
}
