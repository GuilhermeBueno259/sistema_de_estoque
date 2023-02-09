package com.controle_de_estoque.model.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

	boolean inserir(T t) throws SQLException;

	boolean remover(int id) throws SQLException;

	boolean alterar(T t) throws SQLException;

	T retornar(int id) throws SQLException;

	List<T> retornarLista() throws SQLException;
}
