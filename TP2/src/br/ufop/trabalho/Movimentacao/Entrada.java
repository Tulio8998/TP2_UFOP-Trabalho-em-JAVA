package br.ufop.trabalho.Movimentacao;

import br.ufop.trabalho.entities.Data;

public class Entrada extends Movimentacao{

	public Entrada(String nome, double valor, Data data) {
		super(nome, valor, data);
	}

	@Override
	public String getTipo() {
		return "Entrada";
	}
	
}
