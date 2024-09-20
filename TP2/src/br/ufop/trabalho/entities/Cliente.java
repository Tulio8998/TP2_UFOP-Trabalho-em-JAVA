package br.ufop.trabalho.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Cliente extends Pessoa{

	private int codigo; 
	private String cpf;
	private LocalDate dataNacimento;
	private double multa;
	
	private ArrayList<Dependentes> dependentes;
	private ArrayList<Filme> filmes;

	public Cliente(String nome, String endereco, int codigo, String cpf, LocalDate dataNacimento) {
		super(nome, endereco);
		this.codigo = codigo;
		this.cpf = cpf;
		this.dataNacimento = dataNacimento;
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
	public LocalDate getDataNacimento() {
		return dataNacimento;
	}
	public void setDataNacimento(LocalDate dataNacimento) {
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
			System.out.println("Não é possivel adicionar mais de 3 dependentes.");
			return false;
		}
		
	}
	
	
	
	@Override
	public String toString(){
		return getNome();
	}
	
	

	
}
