package br.ufop.trabalho.controle;

import java.util.ArrayList;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.entities.Cliente;
import br.ufop.trabalho.entities.Filme;


/***
 * Classe de regra de negócios da aplicação. Esta classe deverá controlar todos os dados que serão armazenados no sistema (clientes, filmes e a parte financeira).
 *  Ela é a porta de entrada para acesso a todos os dados e deverá também fazer as verificações necessárias no momento dos cadastros. 
 * É necessário perceber a importância dessa classe que implementa todas as regras de negócio da aplicação. A classe Controle  poderá 
 * ser utilizada para qualquer tipo de interface gráfica inclusive com janelas.
 * 
 *   IMPORTANTE: ESTA CLASSE NAO DEVE TER ENTRADA E SAÍDA DE DADOS PARA O USUÁRIO
 * @author Filipe
 *
 */
public class Controle {
	//Array de clientes
	private ArrayList <Cliente> clientes;
	private ArrayList <Filme> filmes;
	
	private double valorLocacaoDiaria;
	private double valorMultaPorDia;
	private int quantidadeMaximaFilmesAlugados;
	
	public Controle(){
		clientes = new ArrayList<Cliente>();
		filmes = new ArrayList<Filme>();
	}
	
	public int addCliente(String nome, String end, int codigo){
		/**
		 * Para cada uma das verificações abaixo o método irá retornar um inteiro indicando um erro
		 * caso ele aconteça. Caso nenhum dado inválido seja digitado o cadastro será realizado
		 * e será retornado um inteiro indicando que o cadastro foi realizado corretamente
		 */
		//Verifica se os campos estão preenchidos
		if(Util.verificaListaStringPreenchida(nome, end) == false ){
			return Constantes.ERRO_CAMPO_VAZIO;
		}

		Cliente cliente = new Cliente(nome, end, codigo, end, null, codigo);
		this.clientes.add(cliente);
		
		return Constantes.RESULT_OK;
	}
	/**
	 * Método que retornar a quantidade de clientes cadastrados
	 */
	public int getQtdClientes(){
		return clientes.size();
	}
	/**
	 * Método para retornar um cliente em uma determinada posição. É importante que as classes de interface gráfica não tenham
	 * acesso a uma referncia do array utilizado para armazenar todos os clientes
	 * @param pos
	 * @return
	 */
	public Cliente getClienteNaPosicao(int pos){
		if(pos >=0 && pos < getQtdClientes()){
			return clientes.get(pos);
		}
		//Caso a posição solicitada não tenha cliente será retornado NULL
		return null;
	}
	
	
	
	public void informacoesDoSitema (double locacaoDiaria, double multaPorDia, int maxFilmesAlugados) {
        valorLocacaoDiaria = locacaoDiaria;
        valorMultaPorDia = multaPorDia;
        quantidadeMaximaFilmesAlugados = maxFilmesAlugados;
    }

	public double getValorLocacaoDiaria() {
		return valorLocacaoDiaria;
	}
	public double getValorMultaPorDia() {		
		return valorMultaPorDia;
	}
	public int getQuantidadeMaximaFilmesAlugados() {
		return quantidadeMaximaFilmesAlugados;
	}

	
  
	
}

