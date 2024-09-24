package br.ufop.trabalho.movimentacao;

import br.ufop.trabalho.entities.Data;

public abstract class Movimentacao {
	
	private String nome;
    private double valor;
    private int mes;
    private int ano;

    public Movimentacao(String nome, double valor, int mes, int ano) {
        this.nome = nome;
        this.valor = valor;
        this.mes = mes;
        this.ano = ano;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public abstract String getTipo();

	
}
