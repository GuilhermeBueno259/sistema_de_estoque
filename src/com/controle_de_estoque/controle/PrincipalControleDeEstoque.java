package com.controle_de_estoque.controle;

import com.controle_de_estoque.visualizacao.PaginaLogin;

public class PrincipalControleDeEstoque {
	private static PaginaLogin paginaLogin = null;

	public static void main(String[] args) {
		paginaLogin = new PaginaLogin();
	}

	public static void fecharPaginaLogin() {
		paginaLogin.dispose();
	}
}
