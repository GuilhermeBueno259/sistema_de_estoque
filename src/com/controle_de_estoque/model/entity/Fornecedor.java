package com.controle_de_estoque.model.entity;

import java.util.Collections;
import java.util.List;

import com.controle_de_estoque.controle.ControleEntidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fornecedor {

	private int id;
	private String cpf_cnpj;
	private String ie;
	private String nome_fantasia;
	private String email;
	private int telefoneId;
	private int enderecoId;
	private List<Pedido> listaDePedidos;

	public Fornecedor() {
	}

	public Fornecedor(String cpf_cnpj, String ie, String nome_fantasia, String email, int telefoneId, int enderecoId) {
		this.cpf_cnpj = cpf_cnpj;
		this.ie = ie;
		this.nome_fantasia = nome_fantasia;
		this.email = email;
		this.telefoneId = telefoneId;
		this.enderecoId = enderecoId;
	}

	public Fornecedor(String cpf_cnpj, String ie, String nome_fantasia, String email, String numeroTelefone,
			String endereco) {
		this.cpf_cnpj = cpf_cnpj;
		this.ie = ie;
		this.nome_fantasia = nome_fantasia;
		this.email = email;
		this.telefoneId = ControleEntidades.retornarTelefoneId(numeroTelefone);
		this.enderecoId = ControleEntidades.retornarEnderecoId(endereco.split("-")[0]);
	}

	public Fornecedor(int id, String cpf_cnpj, String ie, String nome_fantasia, String email, int telefoneId,
			int enderecoId) {
		this.id = id;
		this.cpf_cnpj = cpf_cnpj;
		this.ie = ie;
		this.nome_fantasia = nome_fantasia;
		this.email = email;
		this.telefoneId = telefoneId;
		this.enderecoId = enderecoId;
	}

	public List<Pedido> getListaDePedidos() {
		return Collections.unmodifiableList(listaDePedidos);
	}
}