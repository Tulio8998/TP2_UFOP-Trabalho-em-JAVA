package br.ufop.trabalho.IOConsole;

import java.util.Scanner;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Constantes;
import br.ufop.trabalho.controle.Controle;

public class MenuFilmesConsole {
	private Controle controle;
	private Scanner input;
	
	public MenuFilmesConsole(Controle controle, Scanner input){
		this.controle = controle;
		this.input = input;
	}
	
	public void exibeMenuFilmes(){
		boolean continua = true;
		int op  = 0;
		do{	
			// A opção 5 não é necessária. Foi inserida apenas para teste.
			System.out.println("Digite a opção:\n\t1 - Cadastrar filme\n\t2 - Buscar filme\n\t5 - imprime Lista de filmes\n\t10 - Voltar\n");
			op = Util.leInteiroConsole(input);
			switch(op){
				case 1:
					leDadosCliente();
					break;
				case 2:
					System.out.println("Falta implementar!");
					break;
				case 5:
					//Esta opção não foi solicitada no enunciado. É apenas para testes
					imprimeListaClientes();
					break;
				case 10:
					return;
				default:
					System.out.println("Opção Inválida!");
			}		
		}while(continua == true);
	}

	/**
	 * this.nome = nome;
		this.anoLancado = anoLancado;
		this.genero = genero;
		this.quantidadeDvds = quantidadeDvds;
		this.quantidadeBluerays = quantidadeBluerays;
		this.tipoFilme = tipoFilme;
	 * Este método permitirá a entrada dos dados de um cliente. 
	 * 		MÉTODO INCOMPLETO. NÃO CADASTRA TODOS OS DADOS.
	 */
	private void leDadosCliente(){
		//Limpa o buffer já que leu um inteiro
		input.nextLine();
		String nome, genero, tipoFilme;  
		int anoLancado, quantidadeDvds, quantidadeBluerays;
		System.out.println("Digite o nome do filme: ");
		nome = input.nextLine();
		System.out.println("Digite o genero do filme: ");
		genero = input.nextLine();
		System.out.println("Digite o tipo do filme: ");
		tipoFilme = input.nextLine();
		System.out.println("Digite o ano lançado do filme: ");
		anoLancado = Util.leInteiroConsole(input);
		System.out.println("Digite a quantidade de DVD's do filme: ");
		quantidadeDvds = Util.leInteiroConsole(input);
		System.out.println("Digite a quantidade de Bluerays do filme: ");
		quantidadeBluerays = Util.leInteiroConsole(input);
		input.nextLine();
		int retorno = controle.cadastrarFilme(nome, anoLancado, genero, quantidadeDvds, quantidadeBluerays, tipoFilme);
		String msg = "";
		switch(retorno){
		//Verificação do retorno do método de adição de cliente
			case Constantes.ERRO_CAMPO_VAZIO:
					msg = "Todos os campos devem ser preenchidos!";
					break;
			case Constantes.RESULT_OK:
				msg = "Filme cadastrado com sucesso!";
				break;
		}
		System.out.println(msg);

	}
	

	private void imprimeListaClientes() {
		System.out.println("******** LISTA DE FILMES CADASTRADOS *********");
		for(int i = 0; i < controle.getQtdFilmes(); i++){
			//É preciso implementar o toString corretamente.
			System.out.println(controle.getFilmesNaPosicao(i).toString());
		}	
		System.out.println("******** FIM DA LISTA DE FILMES  *********");
	}
}
