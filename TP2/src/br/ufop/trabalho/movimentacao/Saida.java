package br.ufop.trabalho.movimentacao;



public class Saida extends Movimentacao{

	

	public Saida(String nome, double valor, int mes, int ano) {
		super(nome, valor, mes, ano);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getTipo() {
		return "Entrada";
	}
	
}
