package br.ufop.trabalho.movimentacao;


public class Entrada extends Movimentacao{

	

	public Entrada(String nome, String descricao, double valor, int mes, int ano) {
		super(nome, descricao, valor, mes, ano);
	}

	@Override
	public String getTipo() {
		return "Entrada";
	}
	
}
