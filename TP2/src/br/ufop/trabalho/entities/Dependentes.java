package br.ufop.trabalho.entities;

import java.sql.Date;

public class Dependentes extends Pessoa{

	private String cpf;
	private Date dataNacimento;
	
	public Dependentes(String nome, String endereco, String cpf, Date dataNacimento) {
		super(nome, endereco);
		this.cpf = cpf;
		this.dataNacimento = dataNacimento;
	}
	
	public Dependentes(){}
	
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
	
	
	
}
