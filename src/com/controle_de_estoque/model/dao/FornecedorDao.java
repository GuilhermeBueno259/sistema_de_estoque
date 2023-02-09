package com.controle_de_estoque.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.controle_de_estoque.model.conexao.FabricaDeConexao;
import com.controle_de_estoque.model.entity.Fornecedor;

public class FornecedorDao implements Dao<Fornecedor> {
	private Connection conexao;

	public FornecedorDao() {
		conexao = FabricaDeConexao.getConexao();
	}

	@Override
	public boolean inserir(Fornecedor t) throws SQLException {
		String comando = "INSERT INTO fornecedor (CPF_CNPJ, IE, Fantasia_Nome, Email, Telefone_id, Endereco_id) VALUES (?, ?, ?, ?, ?, ?)";

		PreparedStatement statement = conexao.prepareStatement(comando);

		statement.setString(1, t.getCpf_cnpj());
		statement.setString(2, t.getIe());
		statement.setString(3, t.getNome_fantasia());
		statement.setString(4, t.getEmail());
		statement.setInt(5, t.getTelefoneId());
		statement.setInt(6, t.getEnderecoId());

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public boolean remover(int id) throws SQLException {
		String comando = "DELETE FROM fornecedor WHERE id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public boolean alterar(Fornecedor t) throws SQLException {
		String comando = "UPDATE fornecedor SET CPF_CNPJ = ?, IE = ?, Fantasia_Nome = ?, Email = ?, Telefone_id = ?, Endereco_id = ? WHERE id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setString(1, t.getCpf_cnpj());
		statement.setString(2, t.getIe());
		statement.setString(3, t.getNome_fantasia());
		statement.setString(4, t.getEmail());
		statement.setInt(5, t.getTelefoneId());
		statement.setInt(6, t.getEnderecoId());

		int retorno = statement.executeUpdate();

		statement.close();

		return retorno > 0;
	}

	@Override
	public Fornecedor retornar(int id) throws SQLException {
		String comando = "SELECT * FROM fornecedor WHERE id = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setInt(1, id);

		ResultSet retorno = statement.executeQuery();

		Fornecedor fornecedor = new Fornecedor();

		if (retorno.next()) {
			fornecedor.setId(retorno.getInt("Id"));
			fornecedor.setCpf_cnpj(retorno.getString("CPF_CNPJ"));
			fornecedor.setIe(retorno.getString("IE"));
			fornecedor.setNome_fantasia(retorno.getString("Fantasia_Nome"));
			fornecedor.setEmail(retorno.getString("Email"));
			fornecedor.setTelefoneId(retorno.getInt("Telefone_id"));
			fornecedor.setEnderecoId(retorno.getInt("Endereco_id"));
		}

		statement.close();
		retorno.close();

		return fornecedor;
	}

	@Override
	public List<Fornecedor> retornarLista() throws SQLException {
		String comando = "SELECT * FROM fornecedor";
		List<Fornecedor> lista = new ArrayList<Fornecedor>();

		PreparedStatement statement = conexao.prepareStatement(comando);
		ResultSet retorno = statement.executeQuery();

		while (retorno.next()) {
			lista.add(new Fornecedor(retorno.getInt("Id"), retorno.getString("CPF_CNPJ"), retorno.getString("IE"),
					retorno.getString("Fantasia_Nome"), retorno.getString("Email"), retorno.getInt("Telefone_id"),
					retorno.getInt("Endereco_id")));
		}

		statement.close();
		retorno.close();

		return lista;
	}

	public Fornecedor retornarPorCpfCnpj(String cpfCnpj) throws SQLException {

		String comando = "SELECT * FROM fornecedor WHERE `cpf_cnpj` = ?";

		PreparedStatement statement = conexao.prepareStatement(comando);
		statement.setString(1, cpfCnpj);

		ResultSet retorno = statement.executeQuery();

		Fornecedor fornecedor = new Fornecedor();

		if (retorno.next()) {
			fornecedor.setId(retorno.getInt("Id"));
			fornecedor.setCpf_cnpj(retorno.getString("CPF_CNPJ"));
			fornecedor.setIe(retorno.getString("IE"));
			fornecedor.setNome_fantasia(retorno.getString("Fantasia_Nome"));
			fornecedor.setEmail(retorno.getString("Email"));
			fornecedor.setTelefoneId(retorno.getInt("Telefone_id"));
			fornecedor.setEnderecoId(retorno.getInt("Endereco_id"));
		}

		statement.close();
		retorno.close();

		return fornecedor;
	}
}
