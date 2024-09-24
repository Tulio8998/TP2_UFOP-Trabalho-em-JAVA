package br.ufop.trabalho.movimentacao;



public class Saida extends Movimentacao{

	public Saida(String nome, String descricao, double valor, int mes, int ano) {
		super(nome, descricao, valor, mes, ano);
	}

	@Override
	public String getTipo() {
		return "Saida";
	}
	
}
