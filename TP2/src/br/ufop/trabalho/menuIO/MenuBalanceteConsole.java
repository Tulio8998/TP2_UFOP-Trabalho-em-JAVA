package br.ufop.trabalho.menuIO;

import java.util.List;
import java.util.Scanner;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Funcionario;
import br.ufop.trabalho.movimentacao.Movimentacao;


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
			System.out.println("Infome o que você deseja:");
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
		while (continua ==true);
	}
	
	private void cadastrarEntrada() {
		input.nextLine();
        System.out.println("--- Cadastro de Entrada ---");
        System.out.println("Nome da entrada: ");
        String descricao = input.next();
        System.out.println("Valor da entrada: ");
        double valor = Util.leDoubleConsole(input);
        System.out.println("Mês (1-12): ");
        int mes = input.nextInt();
        if (mes >= 1 && mes <= 12 ) {
        	System.out.println("Ano: ");
            int ano = Util.leInteiroConsole(input);
            controle.cadastrarEntrada(descricao , valor, mes, ano);
            System.out.println("Entrada cadastrada com sucesso!");
        } else {
        	System.out.println("Mês invalido!");
        }
        
    }
	
	private void cadastrarSaida() {
	    input.nextLine();
	    String descricao = null; 
	    double valor = 0;
	    int mes, ano;
	    
	    System.out.println("\nEscolha o tipo de saída: ");
	    System.out.println("\t1 - Aluguel da Locadora. ");
	    System.out.println("\t2 - Compra de Filmes.");
	    System.out.println("\t3 - Pagamento de Funcionários.");
	    System.out.println("\t4 - Outro tipo.");

	    System.out.print("Informe o que você deseja: ");
	    int tipodeSaida = Util.leInteiroConsole(input);
	    
	    switch(tipodeSaida) {
	    	case 1:
	    		descricao = "Aluguel da Locadora";
	    	break;
	    	
	    	case 2:
	    		descricao = "Compra de Filmes";
	    	break;
	    	
	    	case 3:
	    		descricao = "Pagamento de Funcionários";
	    	break; 
	    	
	    	case 4:
	    		System.out.println("\nDescreva o outro tipo de saída que você deseja");
	    		descricao = input.nextLine();
	    	break; 
	    	
	    	default:
	    		System.out.println("Não entendemos o que você quis dizer, tente novamente!");
	    	break;
	    }
	    
	    System.out.println("Valor da saída: ");
        valor = Util.leDoubleConsole(input);
        System.out.println("Mês (1-12): ");
        mes = Util.leInteiroConsole(input);
        if (mes >= 1 && mes <= 12 ) {
        	System.out.println("Ano: ");
        	ano = Util.leInteiroConsole(input);
        	input.nextLine();
            controle.cadastrarSaida(descricao.toLowerCase(), valor, mes, ano);
            System.out.println("Saída cadastrada com sucesso!");
        } else {
        	System.out.println("Mês invalido!");
        }
         
        
	}
	
	private void buscarMovimentacoes() {
		input.nextLine();
        System.out.println("--- Busca de Movimentações ---");
        System.out.println("Digite o nome da movimentação: ");
        String nome = input.next();
        List<Movimentacao> movimentacoes = controle.buscarMovimentacaoPorNome(nome);
        if (!movimentacoes.isEmpty()) {
            System.out.println("Movimentações encontradas:");
            for (int i = 0; i < movimentacoes.size(); i++) {
                System.out.println((i + 1) + ". " + movimentacoes.get(i).getTipo() + " - " + movimentacoes.get(i).getNome() + " - R$ " + movimentacoes.get(i).getValor());
            }
        } else {
            System.out.println("Nenhuma movimentação encontrada com esse nome.");
        }
    }
	
	
	private void balancetePorMes() {
		input.nextLine();
        System.out.println("--- Balancete por Mês ---");
        System.out.println("Digite o mês (1-12): ");
        int mes = input.nextInt();
        
        controle.calcularBalancetePorMes(mes);
    }

    private void balancetePorAno() {
    	input.nextLine();
        System.out.println("--- Balancete por Ano ---");
        System.out.println("Digite o ano: ");
        int ano = input.nextInt();
        controle.calcularBalancetePorAno(ano);
    }
	
	private void imprimeListaFuncionarios() {
		System.out.println("******** LISTA DE FUNCIONARIOS CADASTRADOS *********");
		List<Funcionario> funcionarios = controle.getFuncionarios();
		for(Funcionario f : funcionarios) {
			System.out.println(f);
		}
		System.out.println("******** FIM DA LISTA DE CLIENTES  *********");
	}
	
}   