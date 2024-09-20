package br.ufop.trabalho.entities;

import java.sql.Date;
import java.time.LocalDate;

public class Dependentes extends Pessoa{

	private String cpf;
	private LocalDate dataNacimento;
	
	public Dependentes(String nome, String endereco, String cpf, LocalDate data) {
		super(nome, endereco);
		this.cpf = cpf;
		this.dataNacimento = data;
	}
	
	public Dependentes(){}
	
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
	
	
	
}
