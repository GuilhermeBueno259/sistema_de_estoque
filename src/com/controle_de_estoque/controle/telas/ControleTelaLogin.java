package com.controle_de_estoque.controle.telas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.controle_de_estoque.controle.PrincipalControleDeEstoque;
import com.controle_de_estoque.model.conexao.FabricaDeConexao;
import com.controle_de_estoque.visualizacao.PaginaInicial;

public class ControleTelaLogin {
	public static void logar(String email, String senha) {

		Connection conexao = FabricaDeConexao.getConexao();

		String comando = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
		
		try {

			PreparedStatement statement = conexao.prepareStatement(comando);
			statement.setString(1, email);
			statement.setString(2, senha);

			ResultSet resultado = statement.executeQuery();

			if (resultado != null) {
				PrincipalControleDeEstoque.fecharPaginaLogin();
				
				new PaginaInicial();
			} else {
				System.out.println("Usuario/Senha incorreto");
			}

		} catch (SQLException e) {
			System.out.println("Erro ao recuperar usuarios no banco de dados");
		}

	}
}
