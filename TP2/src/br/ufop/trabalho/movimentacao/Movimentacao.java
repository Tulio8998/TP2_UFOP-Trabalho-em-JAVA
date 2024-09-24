package br.ufop.trabalho.movimentacao;

import br.ufop.trabalho.entities.Data;

public abstract class Movimentacao {
	
	private String nome;
	private String descricao;
    private double valor;
    private int mes;
    private int ano;

    public Movimentacao(String nome,String descricao, double valor, int mes, int ano) {
        this.nome = nome;
        this.descricao =  descricao;
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
    
    public String getDescricao() {
		return descricao;
	}

	public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return "\nMovimentacao efetuada" + 
                "\n| Nome: " + nome +
                "\n| Descricao: " + descricao + 
                "\n| Tipo da Movimentação: " + getTipo() +
                "\n| Valor: " + valor + 
                "\n| Mes: " + mes + 
                "\n| Ano: " + ano;
    }
    
}
