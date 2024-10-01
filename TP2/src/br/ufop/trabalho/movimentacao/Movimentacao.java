package br.ufop.trabalho.movimentacao;

import java.io.Serializable;
import java.util.Objects;

public abstract class Movimentacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public abstract String getTipo();

	@Override
	public int hashCode() {
		return Objects.hash(ano, descricao, mes, nome, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimentacao other = (Movimentacao) obj;
		return ano == other.ano && Objects.equals(descricao, other.descricao) && mes == other.mes
				&& Objects.equals(nome, other.nome)
				&& Double.doubleToLongBits(valor) == Double.doubleToLongBits(other.valor);
	}

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
