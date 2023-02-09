package com.controle_de_estoque.model.entity;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {

	private int id;
	private String nome;
	private String cpf;
	private String email;
	private int enderecoId;
	private int telefoneId;
	private List<Pedido> listaDePedidos;

	public Cliente() {
	}

	public Cliente(int id, String nome, String cpf, String email, int enderecoId, int telefoneId) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.enderecoId = enderecoId;
		this.telefoneId = telefoneId;
	}

	public Cliente(String nome, String cpf, String email, int enderecoId, int telefoneId) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.enderecoId = enderecoId;
		this.telefoneId = telefoneId;
	}

	public Cliente(int id, String nome, String cpf, String email, String endereco, String telefone) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}

	public List<Pedido> getLista() {
		return Collections.unmodifiableList(listaDePedidos);
	}

	public boolean addPedido(Pedido pedido) {
		return listaDePedidos.add(pedido);
	}

	@Override
	public String toString() {
		return String.format("ID: %d\nNome: %s\nCPF: %s\nEmail: %s\nID Endereco: %d\nID Telefone: %d\n", id, nome, cpf,
				email, enderecoId, telefoneId);
	}
}
