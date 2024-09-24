package br.ufop.trabalho.entities;

import java.io.Serializable;

public class Data implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mes;
	private int dia; 
	private int ano; 

	public Data(int d, int m, int a) {
		setDia(d);
		setAno(a);
		setMes(m);
	}	
	
	private int checarMes(int mesTeste) {
		if (mesTeste > 0 && mesTeste <= 12)
			return mesTeste;
		else {
			System.out.printf("Mes invalido (%d) alterado para 1.", mesTeste);
			return 1; 
		} 
	}

	private int checarDia(int diaTeste) {
		int diasNoMes[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (diaTeste > 0 && diaTeste <= diasNoMes[mes])
			return diaTeste;
		if (mes == 2 && diaTeste == 29
				&& (ano % 400 == 0 || (ano % 4 == 0 && ano % 100 != 0)))
			return diaTeste;
		System.out.printf("Dia invalido (%d) alterado para 1.\n\n", diaTeste);
		return 1; 
	}
	
	public String toString() {
		return String.format("%d/%d/%d", mes, dia, ano);
	}
	
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = checarMes(mes);
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = checarDia(dia);
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	} 
} 