package br.ufop.trabalho.entities;

import java.io.Serializable;
import java.util.Objects;

public class Dependentes extends Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	private Data data;
	
	public Dependentes(String nome, String endereco, String cpf, Data data) {
		super(nome, endereco, cpf);
		this.data = data;
	}
	
	public Dependentes(){}
	
	public Data getDataNacimento() {
		return data;
	}
	public void setDataNacimento(Data data) {
		this.data = data;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getCpf());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dependentes other = (Dependentes) obj;
		return Objects.equals(getCpf(), other.getCpf());
	}

	@Override
	public String toString() {
		return ""+ getNome();
	}
	
	
	
}