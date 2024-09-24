package br.ufop.trabalho.IOConsole;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.Movimentacao.Entrada;
import br.ufop.trabalho.Movimentacao.Movimentacao;
import br.ufop.trabalho.Movimentacao.Saida;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Data;

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
					+ " - Balance por mês \n\t5 - Balance por ano \n\t6 - Imprimir lista funcionarios \n\t7 - Voltar");
			System.out.println("Infome o que você deseja: ");
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
		String nome;
		double valor;
		LocalDate dataNascimento;
        System.out.println("Nome da entrada:");
        nome = input.nextLine();
        System.out.println("Valor da entrada:");
        valor = input.nextDouble();
        input.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.println("Digite a data (dd/MM/yyyy)");
            String dataString = input.nextLine();
            try {
                dataNascimento = LocalDate.parse(dataString, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Tente novamente.");
            }
        }
        Data data = new Data(dataNascimento.getMonthValue(), dataNascimento.getYear());
        controle.cadastrarMovimentacao(new Entrada(nome, valor, data));
        System.out.println("Entrada registrada!");
    }
	
	private void cadastrarSaida() {
	    input.nextLine();  // Limpa o buffer
	    String nome;
	    double valor;
	    LocalDate dataMovimentacao;
	    
	    System.out.println("Nome da saída:");
	    nome = input.nextLine();
	    System.out.println("Valor da saída:");
	    valor = input.nextDouble();
	    input.nextLine();  // Limpa o buffer

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    while (true) {
	        System.out.println("Digite a data (dd/MM/yyyy):");
	        String dataString = input.nextLine();
	        try {
	            dataMovimentacao = LocalDate.parse(dataString, formatter);
	            break;
	        } catch (DateTimeParseException e) {
	            System.out.println("Formato de data inválido. Tente novamente.");
	        }  
	    }
	    Data data = new Data(dataMovimentacao.getMonthValue(), dataMovimentacao.getYear());
	    controle.cadastrarMovimentacao(new Saida(nome, valor, data));  // Usando 'Saida' corretamente
	    System.out.println("Saída registrada!");
	}
	
	private void buscarMovimentacoes() {
		String nome;
        System.out.println("Nome da movimentação:");
        nome = input.nextLine();
        var resultados = controle.buscarMovimentacaorNome(nome);
        if (resultados.isEmpty()) {
            System.out.println("Nenhuma movimentação encontrada.");
        } else {
            for (Movimentacao mov : resultados) {
                System.out.println(mov.getNome() + ": " + mov.getTipo() + " de R$ " + mov.getValor() + " em " + mov.getData());
            }
        }
    }
	
	private void balancetePorMes() {
		input.nextLine();
	    int mes, ano;
	    while (true) {
	        try {
	            System.out.println("Digite o mês (1-12):");
	            mes = Integer.parseInt(input.nextLine());
	            if (mes < 1 || mes > 12) {
	                System.out.println("Mês inválido. Tente novamente.");
	                continue;
	            }
	            System.out.println("Digite o ano (yyyy):");
	            ano = Integer.parseInt(input.nextLine());
	            break;
	        } catch (NumberFormatException e) {
	            System.out.println("Entrada inválida. Tente novamente.");
	        }
	    }

	    Data data = new Data(mes, ano);
	    double saldo = controle.calcularBalancete(data);
	    
	    System.out.println("Entradas: R$ " + controle.calcularEntradas(data));
	    System.out.println("Saídas: R$ " + controle.calcularSaidas(data));
	    System.out.println("Saldo do mês: R$ " + saldo);
	}
	
	private void balancetePorAno() {
		input.nextLine();
	    int ano;
	    while (true) {
	        try {
	            System.out.println("Digite o ano (yyyy):");
	            ano = Integer.parseInt(input.nextLine());
	            break;
	        } catch (NumberFormatException e) {
	            System.out.println("Ano inválido. Tente novamente.");
	        }
	    }
	    
	    Data data = new Data(0, ano);
	    double saldo = controle.calcularBalancete(data);
	    System.out.println("Saldo do ano: R$ " + saldo);
	}
	
	private void imprimeListaFuncionarios() {
		System.out.println("******** LISTA DE FUNCIONARIOS CADASTRADOS *********");
		for(int i = 0; i < controle.getQtdFuncionarios(); i++){
			//É preciso implementar o toString corretamente.
			System.out.println(controle.getFuncionarioNaPosicao(i).toString());
		}	
		System.out.println("******** FIM DA LISTA DE CLIENTES  *********");
	}
	
}   

