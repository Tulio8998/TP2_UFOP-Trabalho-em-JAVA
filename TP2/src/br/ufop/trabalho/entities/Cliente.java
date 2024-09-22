package br.ufop.trabalho.entities;

import java.util.ArrayList;
import java.util.Objects;

public class Cliente extends Pessoa{

	private int codigo; 
	private String cpf;
	private Data data;
	private double multa;
	
	private ArrayList<Dependentes> dependentes;
	private ArrayList<Filme> filmes;

	public Cliente(String nome, String endereco, int codigo, String cpf, Data data) {
		super(nome, endereco);
		this.codigo = codigo;
		this.cpf = cpf;
		this.data = data;
		this.multa = 0;
		filmes = new ArrayList<Filme>();
		dependentes =  new ArrayList<Dependentes>();
	}

	public Cliente(){}

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Data getDataNascimento() {
		return data;
	}
	public void setDataNascimento(Data data) {
		this.data = data;
	}
	public double getMulta() {
		return multa;
	}
	public void setMulta(double multa) {
		this.multa = multa;
	}
	public ArrayList<Filme> getFilmes() {
		return filmes;
	}
	public void setFilmes(ArrayList<Filme> filmes) {
		this.filmes = filmes;
	}
	public ArrayList<Dependentes> getDependentes() {
		return dependentes;
	}
	public void setDependentes(ArrayList<Dependentes> dependentes) {
		this.dependentes = dependentes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return codigo == other.codigo && Objects.equals(cpf, other.cpf);
	}

	public boolean adicionarDependentes(Dependentes dependente) {
		if (dependentes.size() < 3) {
			dependentes.add(dependente);
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public String toString() {
		return "Nome: " + getNome() +" | Código: " + codigo + " | CPF: " + cpf + " | Data: " + data + " | Multa: " + multa + " | Dependentes: "
				+ dependentes + " | Filmes: " + filmes;
	}
	
	
}