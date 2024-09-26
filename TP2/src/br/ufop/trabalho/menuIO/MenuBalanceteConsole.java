package br.ufop.trabalho.menuIO;

import java.util.List;
import java.util.Scanner;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Funcionario;
import br.ufop.trabalho.movimentacao.Entrada;
import br.ufop.trabalho.movimentacao.Movimentacao;
import br.ufop.trabalho.movimentacao.Saida;


public class MenuBalanceteConsole{
	private Controle controle;
	private Scanner input;
	
	public MenuBalanceteConsole(Controle controle, Scanner input) {
		this.controle = controle;
		this.input = input;
	}
	
	public void exibirMenuBalancete() {
		boolean continua = true;	
		int op = 0;
		do {
			System.out.println("\nDigite a opção: \n\t1 - Cadastar entradas\n\t2 - Cadastrar Saídas\n\t3 - Busca movimentações\n\t4"
					+ " - Balancete por mês \n\t5 - Balancete por ano \n\t6 - Imprimir lista funcionarios \n\t7 - Voltar");
			System.out.print("Infome o que você deseja: ");
			op = Util.leInteiroConsole(input);
			switch(op) {
				case 1:
					cadastrarEntrada();
				break;

				case 2:
					cadastrarSaida();
				break;
				
				case 3:
					buscarMovimentacoes();
				break;
				
				case 4:
					balancetePorMes();
				break;
				
				case 5:
					balancetePorAno();
				break;
				
				case 6:
					imprimeListaFuncionarios();
				break;
				
				case 7:
					System.out.println("Você está saindo do Menu de Balancete...\n");
					continua = false;
				break;
				
				default:
					System.out.println("Não entendemos o que você queria fazer, tente novamente!\n");
				break;
			}
			
		}
		while(continua ==true);
	}
	
	private void cadastrarEntrada() {
		input.nextLine();
        System.out.println("--- Cadastro de Entrada ---");
        System.out.print("Nome da entrada: ");
        String nome = input.nextLine();
        System.out.print("Descrição da entrada: ");
        String descricao = input.nextLine();
        System.out.print("Valor da entrada: ");
        double valor = Util.leDoubleConsole(input);
        System.out.print("Mês (1-12): ");
        int mes;
		while(true){
			mes = Util.leInteiroConsole(input);
			if(mes<1 || mes>12){
				System.out.print("Digite um mês valido");
			}
			else{
				break;
			}
		}
        System.out.print("Ano: ");
		int ano;
		while(true){
			ano = Util.leInteiroConsole(input);
			if (String.valueOf(ano).length() == 4) { 
		        break;
		    } else {
		        System.out.print("Digite um ano válido (com 4 dígitos): ");
		    }
		}
        controle.cadastrarEntrada(nome, descricao , valor, mes, ano);
        Entrada entrada = new Entrada(nome, descricao, valor, mes, ano);
        System.out.println(entrada.toString());
        System.out.println("Entrada cadastrada com sucesso!");
        
    }
	
	private void cadastrarSaida() {
	    input.nextLine();	
        System.out.println("--- Cadastro de Saída ---");
	    System.out.print("\nInforme o nome da saída efetuada: ");
	    String nome = input.nextLine();
	    System.out.print("\nDê uma descrição para a saída efetuada: ");
	    String descricao = input.nextLine();	    
	    System.out.print("\nValor da saída: ");
	    double valor = Util.leDoubleConsole(input);      
        System.out.print("\nMês (1-12): ");
        int mes = Util.leInteiroConsole(input); 
        while(true){
			mes = Util.leInteiroConsole(input);
			if(mes<1 || mes>12){
				System.out.print("Digite um mês valido");
			}
			else{
				break;
			}
		}
        System.out.print("\nAno: ");
		int ano;
		while(true){
			ano = Util.leInteiroConsole(input);
			if (String.valueOf(ano).length() == 4) { 
		        break;
		    } else {
		        System.out.print("Digite um ano válido (com 4 dígitos): ");
		    }
		}
        input.nextLine();
        controle.cadastrarSaida(nome, descricao, valor, mes, ano);            
        Saida saida = new Saida(nome, descricao, valor, mes, ano);
        System.out.println(saida.toString());          
        System.out.println("Saída cadastrada com sucesso!");
        
	}
	
	private void buscarMovimentacoes() {
        input.nextLine();
        System.out.println("--- Busca de Movimentações ---");
        System.out.print("Digite o nome da movimentação: ");
        String nome = input.nextLine();
        List<Movimentacao> movimentacoes = controle.buscarMovimentacaoPorNome(nome.toLowerCase());
        if (!movimentacoes.isEmpty()) {
            System.out.println("Movimentações encontradas:");
            for (int i = 0; i < movimentacoes.size(); i++) {
                System.out.println((i + 1) + " - " + movimentacoes.get(i).getTipo() + " | " + movimentacoes.get(i).getNome() + " | " + movimentacoes.get(i).getDescricao() + " | R$ " + movimentacoes.get(i).getValor());
            }
        } else {
            System.out.println("Nenhuma movimentação encontrada com esse nome.");
        }
    }
	
	
	private void balancetePorMes() {
		input.nextLine();
		int mes;
        System.out.println("--- Balancete por Mês ---");
        System.out.print("Digite o mês (1-12): ");
        while(true){
			mes = Util.leInteiroConsole(input);
			if(mes<1 || mes>12){
				System.out.print("Digite um mês valido: ");
			}
			else{
				break;
			}
		}
        controle.calcularBalancetePorMes(mes);
    }

    private void balancetePorAno() {
    	input.nextLine();
    	int ano;
        System.out.println("--- Balancete por Ano ---");
        System.out.print("Digite o ano: ");
        System.out.print("\nAno: ");
        while(true){
			ano = Util.leInteiroConsole(input);
			if (String.valueOf(ano).length() == 4) { 
		        break;
		    } else {
		        System.out.print("Digite um ano válido (com 4 dígitos): ");
		    }
		}
        controle.calcularBalancetePorAno(ano);
    }
	
	private void imprimeListaFuncionarios() {
		System.out.println("******** LISTA DE FUNCIONARIOS CADASTRADOS *********");
		List<Funcionario> funcionarios = controle.getFuncionarios();
		for(Funcionario f : funcionarios) {
			System.out.println(f);
		}
		System.out.println("******** FIM DA LISTA DE FUNCIONARIOS  *********");
	}
	
}   