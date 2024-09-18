package br.ufop.trabalho.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.IOConsole.MenuClienteConsole;
import br.ufop.trabalho.IOConsole.MenuFilmesConsole;
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
	private MenuClienteConsole menuCliente;
	private MenuFilmesConsole menuFilmes;
	
	private Scanner input = new Scanner(System.in);;
	private double valorLocacaoDiaria;
	private double valorMultaPorDia;
	private int quantidadeMaximaFilmesAlugados;
	
	public Controle(){
		clientes = new ArrayList<Cliente>();
		filmes = new ArrayList<Filme>();
	}
	
	public int cadastrarCliente(String nome, String end, int codigo){
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
	public int getQtdFilmes(){
		return filmes.size();
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
	
	public Filme getFilmesNaPosicao(int pos){
		if(pos >=0 && pos < getQtdFilmes()){
			return filmes.get(pos);
		}
		//Caso a posição solicitada não tenha cliente será retornado NULL
		return null;
	}
	
	public boolean verificarFilmeRepetido(Filme novoFilme) {
		for (Filme f : filmes ) {
			if (f.equals(novoFilme)) {
                System.out.println("Esse filme já existe em seu cadastro.");
                return true;
            }
        }
        return false;
		
	}
	
	 public int cadastrarFilme(String nome, int anoLancado, String genero, int quantidadeDvds, int quantidadeBluerays,
             String tipoFilme) {
		Filme novofilme = new Filme(nome, anoLancado, genero, quantidadeDvds, quantidadeBluerays, tipoFilme);
		if (verificarFilmeRepetido(novofilme)) {
			return Constantes.ERRO_CAMPO_VAZIO;
		} else {
			this.filmes.add(novofilme);
			return Constantes.RESULT_OK;
		}
	}
	
	public List<Filme> buscarFilme(Object busca) {
		List<Filme> resultado =  new ArrayList<>();
		for(Filme f : filmes) {
			if(busca instanceof Integer) {
				int quantidade = (Integer) busca;
				if(f.getQuantidadeBluerays() == quantidade || f.getQuantidadeDvds() == quantidade) {
					resultado.add(f);
				}
			} else if (busca instanceof String) {
				String filme = (String) busca;
				if (f.getNome().equalsIgnoreCase(filme) || f.getGenero().equalsIgnoreCase(filme)) {
					resultado.add(f);
				}
			}
		}
		return resultado;
	
	}
	 
	public void modificarFilmes(Filme filme) {
		System.out.println("Ainda não fiz");
	}
	 
	public void informacoesDoSistema (double locacaoDiaria, double multaPorDia, int maxFilmesAlugados) {
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

