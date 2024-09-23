package br.ufop.trabalho.entities;

import java.io.Serializable;

/**
 * Classe para armazenar os dados de uma Pessoa. Como o sistema deverá controlar clientes e funcionários os dados comuns serão
 * armazenadona superClassePessoa.
 * @author Filipe
 *
 */
public class Pessoa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome, endereco, cpf;

	public Pessoa(String nome, String endereco, String cpf) {
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
	}
	public Pessoa(){}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	@Override
	public String toString() {
		return nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	
}