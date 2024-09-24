package br.ufop.trabalho.Movimentacao;

import br.ufop.trabalho.entities.Data;

public abstract class Movimentacao {
	
	private String nome;	
	private double valor;
	private Data data;
	
	public Movimentacao(String nome, double valor, Data data) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.data = data;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	
	public abstract String getTipo();
	 
}
