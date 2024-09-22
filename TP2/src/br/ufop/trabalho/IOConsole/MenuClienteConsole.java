package br.ufop.trabalho.IOConsole;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Constantes;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Data;

public class MenuClienteConsole {
	private Controle controle;
	private Scanner input;
	
	public MenuClienteConsole(Controle controle, Scanner input){
		this.controle = controle;
		this.input = input;
	}
	
	public void exibeMenuClientes(){
		boolean continua = true;
		int op  = 0;
		do{	
			// A opção 5 não é necessária. Foi inserida apenas para teste.
			System.out.println("\nDigite a opção:\n\t1 - Cadastrar Cliente\n\t2 - Buscar clientes\n\t3 - imprime Lista de Clientes\n\t4 - Voltar\n");
			System.out.print("Informe o que você deseja: ");
			op = Util.leInteiroConsole(input);
			switch(op){
				case 1:
					leDadosCliente();
					break;
				case 2:
					System.out.println("Falta implementar!");
					break;
				case 3:
					//Esta opção não foi solicitada no enunciado. É apenas para testes
					imprimeListaClientes();
					break;
				case 4:
					return;
				default:
					System.out.println("Opção Inválida!\n");
			}		
		}while(continua == true);
	}

	/**
	 * Este método permitirá a entrada dos dados de um cliente. 
	 * 		MÉTODO INCOMPLETO. NÃO CADASTRA TODOS OS DADOS.
	 */
	private void leDadosCliente(){
		//Limpa o buffer já que leu um inteiro
		input.nextLine();
        String nome, end, cpf;
        int codigo;
        LocalDate dataNascimento;
        System.out.println("Digite o nome do cliente");
        nome = input.nextLine();
        System.out.println("Digite o endereco do cliente");
        end = input.nextLine();
        System.out.println("Digite o codigo do cliente");
        codigo = Util.leInteiroConsole(input);
        input.nextLine();
        System.out.println("Digite o CPF do cliente");
        cpf = input.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.println("Digite a data de nascimento do cliente (dd/MM/yyyy)");
            String dataString = input.nextLine();
            try {
                dataNascimento = LocalDate.parse(dataString, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Tente novamente.");
            }
        } 
        Data data = new Data(dataNascimento.getDayOfMonth(), dataNascimento.getMonthValue(), dataNascimento.getYear());
        int retorno = controle.cadastrarCliente(nome, end, codigo, cpf, data);
	    
		String msg = "";
		switch(retorno){
		//Verificação do retorno do método de adição de cliente
			case Constantes.CLIENTE_REPETIDO:
				msg = "Já exite um cliente com esse cadastro";
				break;
			case Constantes.ERRO_CAMPO_VAZIO:
				msg = "Todos os campos devem ser preenchidos!";
				break;
			case Constantes.RESULT_OK:
				leDadosDependente(); 
				msg = "Cliente cadastrado com sucesso!";
				break;
		}
		System.out.println(msg);

	}
	
	private void leDadosDependente() {
	    boolean continua = true;
	    int contadorDependentes = 0; // Contador para controlar o número de dependentes cadastrados

	    do {
	        if (contadorDependentes < 3) {
	            System.out.println("Deseja cadastrar dependente? \n\t1 - Cadastrar dependente\n\t2 - Não cadastrar dependente\n");
	            int resp = input.nextInt();
	            switch (resp) {
	                case 1:
	                    input.nextLine();
	                    String nome, end, cpf;
	                    LocalDate dataNascimento;
	                    System.out.println("Digite o nome do dependente");
	                    nome = input.nextLine();
	                    System.out.println("Digite o endereço do dependente");
	                    end = input.nextLine();
	                    System.out.println("Digite o CPF do dependente");
	                    cpf = input.nextLine();
	                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	                    while (true) {
	                        System.out.println("Digite a data de nascimento do dependente (dd/MM/yyyy)");
	                        String dataString = input.nextLine();
	                        try {
	                            dataNascimento = LocalDate.parse(dataString, formatter);
	                            break;
	                        } catch (DateTimeParseException e) {
	                            System.out.println("Formato de data inválido. Tente novamente.");
	                        }
	                    }
	                    Data data = new Data(dataNascimento.getDayOfMonth(), dataNascimento.getMonthValue(), dataNascimento.getYear());
	                    int retorno = controle.cadastrarDependente(nome, end, cpf, data);
	                    String msg = "";
	                    switch (retorno) {
	                        case Constantes.DEPENDENTE_REPETIDO:
	                            msg = "Esse dependente já está cadastrado para este cliente";
	                            break;
	                        case Constantes.ERROR_LIMITE_DEPENDENTE:
	                            return;
	                        case Constantes.ERRO_CAMPO_VAZIO:
	                            msg = "Todos os campos devem ser preenchidos!";
	                            break;
	                        case Constantes.RESULT_OK:
	                            contadorDependentes++;
	                            msg = "Dependente cadastrado com sucesso!";
	                            break;
	                    }
	                    System.out.println(msg);
	                    break;
	                case 2:
	                    return;
	                default:
	                    System.out.println("Opção Inválida!");
	            }
	        } else {
	            System.out.println("Número máximo de dependentes (3) já cadastrado para este cliente.");
	            return;
	        }
	    } while (continua);
	}
	
	private void imprimeListaClientes() {
		System.out.println("******** LISTA DE CLIENTES CADASTRADOS *********");
		for(int i = 0; i < controle.getQtdClientes(); i++){
			//É preciso implementar o toString corretamente.
			System.out.println(controle.getClienteNaPosicao(i).toString());
		}	
		System.out.println("******** FIM DA LISTA DE CLIENTES  *********");
	}


}