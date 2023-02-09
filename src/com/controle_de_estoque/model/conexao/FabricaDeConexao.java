package com.controle_de_estoque.model.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexao {
	static Connection conexao = null;


	private FabricaDeConexao() {
	}

	private static void iniciarConexao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/controle_de_estoque", "root",
					"1234");
		} catch (ClassNotFoundException e) {
			System.out.println("Arquivo de conexão não encontrado");
		} catch (SQLException e) {
			System.out.println("Erro na conexão com o Banco de Dados");
		}
	}

	public static Connection getConexao() {
		FabricaDeConexao.iniciarConexao();
		return conexao;
	}
}
