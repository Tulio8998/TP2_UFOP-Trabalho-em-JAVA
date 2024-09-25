package br.ufop.trabalho.menuIO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Constantes;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Cliente;
import br.ufop.trabalho.entities.Data;
import br.ufop.trabalho.entities.Dependentes;
import br.ufop.trabalho.entities.Filme;

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
			System.out.println("\nDigite a opção:\n\t1 - Cadastrar Cliente\n\t2 - Buscar clientes\n\t3 - imprime Lista de Clientes\n\t4 - Voltar\n");
			System.out.print("Informe o que você deseja: ");
			op = Util.leInteiroConsole(input);
			switch(op){
				case 1:
					leDadosCliente();
					break;
				case 2:
					if(controle.getQtdClientes()!=0)
					{
						leDadosBuscaCliente();
					}
					else{
						System.out.println("Não há clientes cadastrados!");
					}
					break;
				case 3:
					imprimeListaClientes();
					break;
				case 4:
					return;
				default:
					System.out.println("Opção Inválida!\n");
			}		
		}while(continua == true);
	}

	private void leDadosCliente(){
		input.nextLine();
        String nome, end, cpf;
        int codigo;
        LocalDate dataNascimento;
        System.out.print("Digite o nome do cliente: ");
        nome = Util.leStringConsole(input);
        System.out.print("Digite o endereco do cliente: ");
        end = Util.leStringConsole(input);
        System.out.print("Digite o codigo do cliente: ");
        codigo = Util.leInteiroConsole(input);
        input.nextLine();
        System.out.print("Digite o CPF do cliente: ");
        cpf = Util.leStringConsole(input);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print("Digite a data de nascimento do cliente (dd/MM/yyyy): ");
            String dataString = Util.leStringConsole(input);
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
	
	private void leDadosBuscaCliente(){
		int op = 0, codigo;
		String nome, dependente;
		boolean rodando=true;
		while(rodando){
			List<Cliente> resultado = new ArrayList<>();
			System.out.println("Como deseja buscar o cliente?");
			System.out.println("\t1 - Nome");
			System.out.println("\t2 - Codigo");
			System.out.println("\t3 - Nome do dependente");
			System.out.print("Informe o que você deseja: ");
			op = Util.leInteiroConsole(input);
			input.nextLine();
			switch(op){
				case 1:
					System.out.print("\nDigite o nome do cliente: ");
					nome = Util.leStringConsole(input);
					resultado = controle.buscaCliente(nome);
					exibirBuscaClientes(resultado);
					rodando=false;
					break;

				case 2:
					System.out.print("\nDigite o codigo: ");
					codigo = Util.leInteiroConsole(input);
					resultado = controle.buscaCliente(codigo);
					exibirBuscaClientes(resultado);
					rodando=false;
					break;

				case 3:
					System.out.print("\nDigite o nome de um dependente: ");
					dependente = Util.leStringConsole(input);
					resultado = controle.buscaDependentes(dependente);
					exibirBuscaClientes(resultado);
					rodando=false;
					break;

				default:
					System.out.println("Opção invalida!");
					break;
			}
		}
	}
	
	private void exibirBuscaClientes(List<Cliente> clientes){
		if(clientes.isEmpty()){
			System.out.println("Cliente inexistente.\n");
		}
		else{
			int count=1;
			System.out.println("\nClientes encontrados: ");
			for (Cliente c : clientes){
				System.out.println(count + " - " + c.getNome());
				count++;
			}
			count=1;
			System.out.print("\nEscolha um: ");
			int op = Util.leInteiroConsole(input);
			for(Cliente c : clientes){
				if(count==op){
					exibeOpcoesCliente(c);
				}
				count++;
			}
		}
	}

	private void exibeOpcoesCliente(Cliente cliente){
		controle.setCliente(cliente);
		int op = 0;
		imprimirCliente(cliente);
		boolean rodando=true;
		while(rodando){
			System.out.println("1 - Editar cliente");
			System.out.println("2 - Adicionar dependente");
			System.out.println("3 - Excluir dependente");
			System.out.println("4 - Locar filme");
			System.out.println("5 - Devolver filme");
			System.out.println("6 - Pagar multa");
			System.out.println("7 - Voltar");
			System.out.print("Informe o que você deseja: ");
			op = Util.leInteiroConsole(input);
			switch(op){
				case 1:
					System.out.print("Insira as novas informacoes: ");
					editarCliente(cliente);
					imprimirCliente(cliente);
					break;
				
				case 2:
					leDadosDependente();
					System.out.println("\nAtuais dependentes:");
					imprimeListaDependentes(cliente);
					break;
				
				case 3:
					removerDependente(cliente);
					System.out.println("\nAtuais dependentes: ");
					imprimeListaDependentes(cliente);
					break;

				case 4:
					if(cliente.getMulta()==0){
						if(cliente.getFilmes().size()<5){
							locarFilme(cliente);
						}
						else{
							System.out.println("O cliente atingiu o numero maximo de filmes locados!");
						}
					}
					else{
						System.out.println("O cliente possui debitos nao pagos. Pague as multas pendentes para poder locar mais filmes!");
					}
					break;

				case 5:
					if(!cliente.getFilmes().isEmpty()){
						devolverFilme(cliente);
					}
					else{
						System.out.println("O cliente nao possui filmes locados.");
					}
					break;

				case 6:
					if(cliente.getMulta()==0){
						System.out.println("Nao ha multa para ser paga.");
					}
					else{
						System.out.println("Digite o mes do pagamento da multa:");
						int mes;
						while(true){
							mes = Util.leInteiroConsole(input);
							if(mes<1 || mes>12){
								System.out.println("Digite um mes valido");
							}
							else{
								break;
							}
						}
						System.out.print("Digite o ano do pagamento da multa: ");
						int ano = Util.leInteiroConsole(input);
						controle.cadastrarEntrada("Pagamento de multa", "Cliente: "+cliente.getNome(), cliente.getMulta(), mes, ano);
						cliente.zerarMulta();
						System.out.println("Multa paga com sucesso!");
					}
					break;

				case 7:
					rodando=false;
					break;

				default:
					System.out.println("Opcao invalida!");
					break;
			}
		}
	}
	
	private void imprimirCliente(Cliente cliente){
		System.out.println("Dados do cliente:\n");
		System.out.println("Nome: " + cliente.getNome());
		System.out.println("Codigo: " + cliente.getCodigo());
		System.out.println("CPF: " + cliente.getCpf());
		System.out.println("Data de nascimento: " + cliente.getDataNascimento());
		System.out.println("Endereco: " + cliente.getEndereco());
		System.out.println("Filmes locados:\n");
		imprimirFilmesLocados(cliente);
		System.out.println("\nMulta: R$" + cliente.getMulta());
		System.out.println("Dependentes:"); 
		imprimeListaDependentes(cliente);
	}

	private void editarCliente(Cliente cliente){
		input.nextLine();
		String nome, end, cpf;
		int codigo;
		LocalDate dataNascimento;
		boolean rodando = true;
		while(rodando){
			System.out.print("Digite o nome do cliente: ");
			nome = Util.leStringConsole(input);

			System.out.print("Digite o endereco do cliente: ");
			end = Util.leStringConsole(input);

			System.out.print("Digite o codigo do cliente: ");		
			codigo = Util.leInteiroConsole(input);
			
			input.nextLine();
			System.out.print("Digite o CPF do cliente: ");
			cpf = Util.leStringConsole(input);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			while (true) {
				System.out.println("Digite a data de nascimento do cliente (dd/MM/yyyy): ");
				String dataString = Util.leStringConsole(input);
				try {
					dataNascimento = LocalDate.parse(dataString, formatter);
					break;
				} catch (DateTimeParseException e) {
					System.out.println("Formato de data inválido. Tente novamente.");
				}
			}
			Data data = new Data(dataNascimento.getDayOfMonth(), dataNascimento.getMonthValue(), dataNascimento.getYear());
			if(Util.verificaListaStringPreenchida(nome, end, cpf) == false ){
				System.out.println("Preencha todos os campos.");
			}
			else{
				Cliente teste = new Cliente(nome, end, codigo, cpf, null);
				if(controle.verificarClienteRepetido(teste)){
					System.out.println("Já existe um cliente com estes dados.");
				}
				else{
					cliente.setNome(nome);
					cliente.setEndereco(end);
					cliente.setCodigo(codigo);
					cliente.setCpf(cpf);
					cliente.setDataNascimento(data);
					rodando=false;
				}
			}
		}
	}
	
	private void leDadosDependente() {
	    boolean continua = true;
	    int contadorDependentes = 0;

	    do {
	        if (contadorDependentes < 3) {
	            System.out.println("Deseja cadastrar dependente? \n\t1 - Cadastrar dependente\n\t2 - Não cadastrar dependente\n");
	            System.out.print("Informe o que você deseja: ");
	            int resp = Util.leInteiroConsole(input);
	            switch (resp) {
	                case 1:
	                    input.nextLine();
	                    String nome, end, cpf;
	                    LocalDate dataNascimento;
	                    System.out.print("Digite o nome do dependente: ");
	                    nome = Util.leStringConsole(input);
	                    System.out.print("Digite o endereço do dependente: ");
	                    end = Util.leStringConsole(input);
	                    System.out.print("Digite o CPF do dependente: ");
	                    cpf = Util.leStringConsole(input);
	                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	                    while (true) {
	                        System.out.println("Digite a data de nascimento do dependente (dd/MM/yyyy): ");
	                        String dataString = Util.leStringConsole(input);
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
		                    case Constantes.ERRO_CLIENTE_NAO_SELECIONADO:
	                            msg = "Nenhum cliente selecionado.";
	                            break;
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
	
	private void removerDependente(Cliente cliente){
		if(cliente.getDependentes() != null){
			input.nextLine();
			System.out.println("Digite o nome do dependente que deseja remover: ");
			String nome = Util.leStringConsole(input);
			if(Util.verificaListaStringPreenchida(nome)!=false){
				boolean dependenteEncontrado=false;
				Dependentes ctrl = new Dependentes();
				for(Dependentes d : cliente.getDependentes()){
					if(d.getNome().equals(nome)){
						ctrl=d;
						dependenteEncontrado=true;
						break;
					}
				}
				if(dependenteEncontrado==true){
					cliente.excluirDependente(ctrl);
					System.out.println("Exclusão bem sucedida!");
				}
				else{
					System.out.println("Não existe dependente correspondente.");
				}
			}
			else{
				System.out.println("O campo deve estar preenchido!");
			}

		}
		else{
			System.out.println("Não existem dependentes deste cliente!");
		}
	}
	
	public void locarFilme(Cliente cliente){
		input.nextLine();
		System.out.print("Digite o nome do filme que deseja locar: ");
		String nome = Util.leStringConsole(input);
		if(controle.buscarFilme(nome).isEmpty()){
			System.out.println("Sem resultados disponíveis para este filme.");
		}
		else{
			Filme filme = new Filme();
			filme = controle.buscarFilme(nome).get(0);
			if(filme.getQuantidadeBluerays()==0 && filme.getQuantidadeDvds()==0){
				System.out.println("Desculpe. Todas copias deste filme ja estao locadas!");
			}
			else{
				System.out.println("Deseja locar um DVD ou Blu-ray?\n1 - DVD\n2 - Blu-ray");
				System.out.print("Tipo de locação: ");
				int op = Util.leInteiroConsole(input);
				switch(op){
					case 1:
						if(filme.getQuantidadeDvds()==0){
							System.out.println("Não existe DVD disponíveil para este filme");
						}
						else{
							System.out.print("Digite o mês de locacao: ");
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
							System.out.print("Digite o ano do pagamento da multa: ");
							int ano = Util.leInteiroConsole(input);

							System.out.println("\nCliente: " + cliente.getNome());
							System.out.println("Filme: " + filme.getTitulo());
							System.out.println("Valor: R$" + controle.getValorLocacaoDiaria(filme));
							System.out.println("Data: " + mes + "/" + ano);
							filme.setQuantidadeDvds(filme.getQuantidadeDvds()-1);
							cliente.getTipoMidiaLocada().add("DVD");
							cliente.getFilmes().add(filme);	
							controle.cadastrarEntrada("Pagamento de locacao", "Cliente: "+cliente.getNome()+" Filme: "+filme.getTitulo(), controle.getValorLocacaoDiaria(filme), mes, ano);
							System.out.println("Filme locado com sucesso!");
						}
						break;

					case 2:
						if(filme.getQuantidadeBluerays()==0){
							System.out.println("Não existe Blu-ray disponível para este filme");
						}
						else{
							System.out.println("Digite o mês de locacao: ");
							int mes;
							while(true){
								mes = Util.leInteiroConsole(input);
								if(mes<1 || mes>12){
									System.out.print("Digite um mês valido: ");
								}
								else{
									break;
								}
							}
							System.out.print("Digite o ano do pagamento da multa: ");
							int ano = Util.leInteiroConsole(input);

							System.out.println("\nCliente: " + cliente.getNome());
							System.out.println("Filme: " + filme.getTitulo());
							System.out.println("Valor: R$" + controle.getValorLocacaoDiaria(filme));
							System.out.println("Data: " + mes + "/" + ano);
							filme.setQuantidadeBluerays(filme.getQuantidadeBluerays()-1);
							cliente.getTipoMidiaLocada().add("Blu-ray");
							cliente.getFilmes().add(filme);	
							controle.cadastrarEntrada("Pagamento de locacao", "Cliente: "+cliente.getNome()+" Filme: "+filme.getTitulo(), controle.getValorLocacaoDiaria(filme), mes, ano);
							System.out.println("Filme locado com sucesso!");
						}
						break;

					default:
						System.out.println("Opcao invalida");
						break;
				}
			}
		}
	}

	private void devolverFilme(Cliente cliente){
		imprimirFilmesLocados(cliente);
		if(!cliente.getFilmes().isEmpty()){
			input.nextLine();
			System.out.print("Digite o nome do filme que deseja devolver: ");	
			String nome = Util.leStringConsole(input);
			int count = 0;
			for(Filme f : cliente.getFilmes()){
				if(nome.toLowerCase().equals(f.getTitulo().toLowerCase())){
					if(cliente.getTipoMidiaLocada().get(count).equals("DVD")){
						cliente.getTipoMidiaLocada().remove(count);
						cliente.getFilmes().remove(f);
						f.setQuantidadeDvds(f.getQuantidadeDvds()+1);
					}
					else{
						cliente.getTipoMidiaLocada().remove(count);
						cliente.getFilmes().remove(f);
						f.setQuantidadeBluerays(f.getQuantidadeBluerays()+1);
					}
					boolean rodando = true;
					while(rodando){						
						System.out.println("Filme removido com sucesso!\n");
						System.out.println("Houve atraso na devolução?\n1 - Sim\n2 - Nao");
						int op = Util.leInteiroConsole(input);
						switch(op){
							case 1:
								input.nextLine();
								System.out.println("Quantos dias de atraso?");
								System.out.print("Respota: ");
								op = Util.leInteiroConsole(input);
								controle.aplicarMulta(cliente, op);
								rodando = false;
								break;

							case 2:
								rodando=false;
								break;

							default:
								System.out.println("Digite uma opção valida.");
								break;
						}
					}
					return;
				}
				count++;
			}
			System.out.println("Você não possui filme correspondente locado!");
		}
	}
	
	private void imprimirFilmesLocados(Cliente cliente){
		if(!cliente.getFilmes().isEmpty() && !cliente.getTipoMidiaLocada().isEmpty()){
			int count=0;
			for(Filme f : cliente.getFilmes()){
				System.out.println(count+1 + " - " + f.getTitulo() + " (" + cliente.getTipoMidiaLocada().get(count) + ")");
				count++;
			}
		}
		else{
			System.out.println("Nenhum filme locado");
		}
	}
	
	private void imprimeListaClientes() {
		System.out.println("******** LISTA DE CLIENTES CADASTRADOS *********");
		for(int i = 0; i < controle.getQtdClientes(); i++){
			System.out.println(controle.getClienteNaPosicao(i).toString());
		}	
		System.out.println("******** FIM DA LISTA DE CLIENTES  *********");
	}
	
	private void imprimeListaDependentes(Cliente c){
		List<Dependentes> dependentes = new ArrayList<>();
		dependentes = c.getDependentes();
		int count = 1;
		if(!dependentes.isEmpty()){
			for(Dependentes d : dependentes){
				System.out.println(count + " - " + d.getNome());
				count++;
			}
			System.out.println("\n");
		}
		else{
			System.out.println("Nenhum dependente\n");
		}
	}	
	

}