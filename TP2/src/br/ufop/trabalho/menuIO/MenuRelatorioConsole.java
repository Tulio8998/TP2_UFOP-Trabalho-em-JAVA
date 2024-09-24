package br.ufop.trabalho.menuIO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Cliente;
import br.ufop.trabalho.entities.Dependentes;
import br.ufop.trabalho.entities.Filme;

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
					gerarRelatorioClientes(controle.getClientes());
				break;
				
				case 2:
					do {
						System.out.println("\nDigite a opção:\n\t1 - Exibir o Relatório dos Filmes por gênero\n\t2 - Exibir o Relatório dos Filmes por ano "
								+ "de lançamento\n\t3 - Exibir o Relatório dos Filmes por ordem alfabética\n\t4 - Voltar\n");	
						System.out.print("Informe o que você deseja: ");
						op = Util.leInteiroConsole(input);
						switch(op) {
							case 1: 
								gerarRelatorioFilmesGenero(controle.getFilmes());
							break;
			
							case 2:
								gerarRelatoriosFilmesAnoLancamento(controle.getFilmes());
							break;
						
							case 3:
								gerarRelatorioAlfabetico(controle.getFilmes());
							break;
						
							case 4:
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
	
	private void gerarRelatorioClientes(List<Cliente>clientes) {
		
		System.out.println("----------------------------------------- Relatório de Clientes -----------------------------------------");
        System.out.println("--------------------------------------------------------------------------------------------------------");
		
        for(Cliente cliente : clientes) {
			String infoClientes =
					"Código de Cadastro: " + cliente.getCodigo() + " | " + "Nome do Cliente: " + cliente.getNome() + " | " + 
							"CPF: " + cliente.getCpf() + " | " + "Data de Nascimento:" + cliente.getDataNascimento() + " | " + 
								"Multa: R$ " + cliente.getMulta() + "| " ;
			System.out.println(infoClientes);      
			if(!cliente.getFilmes().isEmpty()) {
				System.out.println("\nFilmes alugados ");
				for(Filme filme: cliente.getFilmes()) {
					System.out.print(filme.getTitulo() + "\n");
				}
			} else {
				System.out.println("\nEsse usuário não alugou nenhum filme");
			}
			
			if(!cliente.getDependentes().isEmpty()) {
				System.out.println("\nDependentes desse cliente: ");
				for(Dependentes DP: cliente.getDependentes()) {
					System.out.print(DP.getNome() + "\n");
				}
			} else {
				System.out.println("\nEsse usuário não possuí a responsabilidade de nenhum dependente");
			}
	        System.out.println("--------------------------------------------------------------------------------------------------------");
        }
	}
	
	private void gerarRelatorioFilmesGenero(List<Filme> filmes) {
		System.out.println("----------------------------------------- Relatório de Filmes  -----------------------------------------");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        
        List<String> generos = new ArrayList<>();
        
        for(Filme filme : filmes) {
        	if(!generos.contains(filme.getGenero())) {
        		generos.add(filme.getGenero());
        	}
        }     
        for(String genero : generos) {
        	System.out.println("Gênero: " + genero);
        
        	boolean encontrarFPG = false;
        	for(Filme filme : filmes) {
        		if(filme.getGenero().equalsIgnoreCase(genero)) {
        			System.out.println("- " + filme.getTitulo() + " (" + filme.getAnoLancado() + ") ");
        			encontrarFPG = true;
        		}
        	}
   
        	if(!encontrarFPG) {
        		System.out.println("Nenhum filme encontrado para esse gênero");
        		break;
        	} else {
        		System.out.println("Total de Filmes encontrados para o gênero " + genero + ": " + 
        			(int) filmes.stream().filter(f -> f.getGenero().equalsIgnoreCase(genero)).count());
        	}
        
        	System.out.println("--------------------------------------------------------------------------------------------------------");
        }
	}
	
	private void gerarRelatoriosFilmesAnoLancamento(List<Filme> filmes) {
			System.out.println("----------------------------------------- Relatório de Filmes  -----------------------------------------");
		    System.out.println("--------------------------------------------------------------------------------------------------------");
		        
		    List<Integer> anolancamentos = new ArrayList<>();
		        
		    for(Filme filme : filmes) {
		    	if(!anolancamentos.contains(filme.getAnoLancado())) {
		        	anolancamentos.add(filme.getAnoLancado());
		        }
		    }
		        
		    for(Integer anolancamento : anolancamentos ) {
		        System.out.println("Ano de Lançamento: " + anolancamento);
		        
		        boolean encontrarAL = false;
		        for(Filme filme : filmes) {
		        	if(filme.getAnoLancado() == anolancamento) {
		        		System.out.println("- " + filme.getTitulo() + " (" + filme.getGenero() + ") ");
		        		encontrarAL = true;
		        	}
		        }
		   
		        if(!encontrarAL) {
		        	System.out.println("Nenhum filme encontrado para esse ano");
		        	break;
		        } else {
		        	System.out.println("Total de Filmes encontrados por ano de lançamento " + anolancamento + ": " + 
		        		(int) filmes.stream().filter(f -> f.getAnoLancado() == anolancamento).count());
		        }
		        
		       	System.out.println("--------------------------------------------------------------------------------------------------------");
		   }
	}
	
	private void gerarRelatorioAlfabetico(List<Filme> filmes) {
		System.out.println("----------------------------------------- Relatório de Filmes (Ordem Alfabética)  -----------------------------------------");
	    System.out.println("--------------------------------------------------------------------------------------------------------");
	    
	    Collections.sort(filmes, new Comparator<Filme>(){
	    	public int compare(Filme F1, Filme F2) {
	    		return F1.getTitulo().compareToIgnoreCase(F2.getTitulo());
	    	}
	    });
	    
	    for(Filme filme : filmes) {
	    	System.out.println("-> " + filme.getTitulo() + " (" + filme.getAnoLancado()+ ") ");
	    }
	}
}