package br.ufop.trabalho.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Cliente extends Pessoa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo; 
	private Data data;
	private double multa;
	private ArrayList<String> tipoMidiaLocada;
	
	private ArrayList<Dependentes> dependentes;
	private ArrayList<Filme> filmes;

	public Cliente(String nome, String endereco, int codigo, String cpf, Data data) {
		super(nome, endereco, cpf);
		this.codigo = codigo;
		this.data = data;
		this.multa = 0;
		filmes = new ArrayList<Filme>();
		dependentes =  new ArrayList<Dependentes>();
		tipoMidiaLocada = new ArrayList<String>();
	}

	public Cliente(){
		super(); 
		filmes = new ArrayList<Filme>();
		dependentes =  new ArrayList<Dependentes>();
	}
	
	public ArrayList<String> getTipoMidiaLocada(){
		return tipoMidiaLocada;
	}
	
	public void setTipoMidiaLocada(ArrayList<String> tipoMidiaLocada){
		this.tipoMidiaLocada = tipoMidiaLocada;
	}
	
	public ArrayList<Dependentes> getDependentes() {
		return dependentes;
	}
	public void setDependentes(ArrayList<Dependentes> dependentes) {
		this.dependentes = dependentes;
	}
	
	public ArrayList<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(ArrayList<Filme> filmes){
		this.filmes = filmes;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
	
	public void setMulta(double valor) {
		if(valor>0) {
			this.multa += valor;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codigo, getCpf());
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
		return codigo == other.codigo && Objects.equals(getCpf(), other.getCpf());
	}
	
	public void zerarMulta() {
		this.multa = 0;
	}

	public boolean adicionarFilme(Filme filme) {
		if(filmes.size() < 5) {
			filmes.add(filme);
			return true;
		}
		return false;
	}
	
	public void removerFilme(Filme filme) {
		filmes.remove(filme);
	}
	
	public boolean adicionarDependentes(Dependentes dependente) {
		if (dependentes.size() < 3) {
			dependentes.add(dependente);
			return true;
		} else {
			return false;
		}
		
	}

	public void excluirDependente(Dependentes d){
		if (!dependentes.isEmpty()) {
			dependentes.remove(d);
		}
	}
	
	@Override
	public String toString() {
		return "Nome: " + getNome() + " | Código: " + codigo + " | CPF: " + getCpf() + " | Data: " + data + " | Multa: " + multa + " | Dependentes: "
				+ dependentes + " | Filmes: " + filmes;
	}
	
}