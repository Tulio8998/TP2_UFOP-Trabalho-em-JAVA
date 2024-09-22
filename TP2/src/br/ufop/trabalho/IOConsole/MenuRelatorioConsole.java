package br.ufop.trabalho.IOConsole;

import java.util.Scanner;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Relatorio;

public class MenuRelatorioConsole {
	private Controle controle;
	private Scanner input;
	
	public MenuRelatorioConsole(Controle controle, Scanner input) {
		this.controle = controle;
		this.input = input;
	}
	
	public void exibeMenuRelatorio() {
		boolean continua = true;
		boolean rodando = true;
				
		int op = 0;
		do {
			System.out.println("\nDigite a opção: \n\t1 - Exibe o Relatório de Clientes\n\t2 - Exibe o Relatório de filme\n\t3 - Voltar\n");
			System.out.print("Informe o que você deseja: ");
			op = Util.leInteiroConsole(input);
			switch(op) {
				case 1:
					Relatorio.gerarRelatorioClientes(controle.getCliente());
				break;
				
				case 2:
					do {
						System.out.println("\nDigite a opção:\n\t21 - Exibir o Relatório dos Filmes por gênero\n\t22 - Exibir o Relatório dos Filmes por ano "
								+ "de lançamento\n\t23 - Exibir o Relatório dos Filmes por ordem alfabética\n\t24 - Voltar\n");	
						System.out.print("Informe o que você deseja: ");
						op = Util.leInteiroConsole(input);
						switch(op) {
							case 21: 
								Relatorio.gerarRelatorioFilmesGenero(controle.getFilmes());
							break;
			
							case 22:
								Relatorio.gerarRelatoriosFilmesAnoLancamento(controle.getFilmes());
							break;
						
							case 23:
								Relatorio.gerarRelatorioAlfabetico(controle.getFilmes());
							break;
						
							case 24:
								System.out.println("Você está saindo dos Relatórios de Filme\n");
								rodando = false;
							break;
						
							default:
								System.out.println("Não entendemos o que você queria fazer, tente novamente!\n");
							break;
						}
					}
					while(rodando == true);
				break;
				
				case 3:
					System.out.println("Você está saindo da área de Relatórios...\n");
					continua = false;
				break;
				
				default:
					System.out.println("Não entendemos o que você queria fazer, tente novamente!\n");
				break;
			}
		}
		while(continua == true);
	}
}