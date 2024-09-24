package br.ufop.trabalho.movimentacao;


public class Entrada extends Movimentacao{

	

	public Entrada(String nome, double valor, int mes, int ano) {
		super(nome, valor, mes, ano);
	}

	@Override
	public String getTipo() {
		return "Entrada";
	}
	
}
