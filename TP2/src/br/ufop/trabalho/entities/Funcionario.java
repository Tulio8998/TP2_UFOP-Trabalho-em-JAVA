package br.ufop.trabalho.entities;

import java.io.Serializable;

public class Funcionario extends Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
    private double salario;
    private int codigoadmissao;

    public Funcionario(String nome, String endereco, String cpf, double salario, int codigoadmissao) {
        super(nome, endereco, cpf);
        this.codigoadmissao = codigoadmissao;
        this.salario = salario;
    }

    public Funcionario() {
        super();
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        if(salario>0) {
            this.salario = salario;
        }
        else {
            this.salario = 0;
        }
    }

    public int getCodigoadmissao() {
        return codigoadmissao;
    }

    public void setCodigoadmissao(int codigoadmissao) {
        this.codigoadmissao = codigoadmissao;
    }

    @Override
    public String toString() {
        return "Funcionário: " + getNome() + " | CPF: " + getCpf() + "| Codigo de Admissão: " + codigoadmissao + "| Salário: " + salario;
    }
}