package br.ufop.trabalho.entities;

import java.sql.Date;
import java.util.ArrayList;

public class Cliente extends Pessoa{

	private int codigo; 
	private String cpf;
	private Date dataNacimento;
	private double multa;
	
	private ArrayList<Dependentes> dependentes;
	private ArrayList<Filme> filmes;

	public Cliente(String nome, String endereco, int codigo, String cpf, Date dataNacimento, double multa) {
		super(nome, endereco);
		this.codigo = codigo;
		this.cpf = cpf;
		this.dataNacimento = dataNacimento;
		this.multa = multa;
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
	public Date getDataNacimento() {
		return dataNacimento;
	}
	public void setDataNacimento(Date dataNacimento) {
		this.dataNacimento = dataNacimento;
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

	public boolean adicionarDependentes(Dependentes dependente) {
		
		if (dependentes.size() < 3) {
			dependentes.add(dependente);
			return true;
		} else {
			System.out.println("Não é possivel adicionar mais de 3 dependentes.");
			return false;
		}
		
	}
	
	
	
	@Override
	public String toString(){
		return getNome();
	}
	
	

	
}
