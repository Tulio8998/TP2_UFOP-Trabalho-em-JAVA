package br.ufop.trabalho.entities;


public class Dependentes extends Pessoa{

	private String cpf;
	private Data data;
	
	public Dependentes(String nome, String endereco, String cpf, Data data) {
		super(nome, endereco);
		this.cpf = cpf;
		this.data = data;
	}
	
	public Dependentes(){}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Data getDataNacimento() {
		return data;
	}
	public void setDataNacimento(Data data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return ""+ getNome();
	}
	
	
	
}
