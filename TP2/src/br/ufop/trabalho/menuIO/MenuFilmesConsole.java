package br.ufop.trabalho.menuIO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Constantes;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Cliente;
import br.ufop.trabalho.entities.Dependentes;
import br.ufop.trabalho.entities.Filme;

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
			System.out.print("\nDigite a opção:\n\t1 - Cadastrar filme\n\t2 - Buscar filme\n\t3- Voltar\n");
			System.out.print("Informe o que você deseja: ");
			op = Util.leInteiroConsole(input);
			System.out.println();
			switch(op){
				case 1:
					leDadosFilmes();
				break;
				
				case 2:
					leDadosBuscaFilmes();
				break;
				
				case 3:
					System.out.println("Você está saindo da área de Filmes...\n");
					continua = false;
				break;
				
				default:
					System.out.println("Opção Inválida!\n");
				break;
			}		
		}while(continua == true);
	}

	private void leDadosFilmes(){
		input.nextLine();
		String titulo, genero, tipoFilme;  
		int anoLancado, quantidadeDvds, quantidadeBluerays;
		System.out.print("Digite o nome do filme: ");
		titulo = Util.leStringConsole(input);
		System.out.print("Digite o genero do filme: ");
		genero = Util.leStringConsole(input);
		System.out.print("Digite o tipo do filme: ");
		tipoFilme = Util.leStringConsole(input);
		System.out.print("Digite o ano lançado do filme: ");
		anoLancado = Util.leInteiroConsole(input);
		System.out.print("Digite a quantidade de DVD's do filme: ");
		quantidadeDvds = Util.leInteiroConsole(input);
		System.out.print("Digite a quantidade de Bluerays do filme: ");
		quantidadeBluerays = Util.leInteiroConsole(input);
		input.nextLine();
		int retorno = controle.cadastrarFilme(titulo, anoLancado, genero, quantidadeDvds, quantidadeBluerays, tipoFilme);
		String msg = "";
		switch(retorno){
			case Constantes.ERRO_TIPO_FILME:
				msg = "\nTipo de filme inválido. Deve ser lançamento, novo ou antigo.";
				break;
			case Constantes.FILME_REPETIDO:
				msg = "\nEsse filme já existe em seu cadastro!";
				break;
			case Constantes.ERRO_CAMPO_VAZIO:
					msg = "\nTodos os campos devem ser preenchidos!";
					break;
			case Constantes.RESULT_OK:
				msg = "\nFilme cadastrado com sucesso!";
				break;
		}
		System.out.println(msg);
		
	}
	
	private void leDadosBuscaFilmes() {
		input.nextLine();
		boolean continua = true;
		int op  = 0, disponibilidade;
		String titulo, genero = null;
		do{	
			System.out.print("\nDigite a opção de busca:\n\t1 - Buscar filme por nome\n\t2 - Buscar filme por genero\n\t3 - Buscar filme por disponibilidade\n\t4 - Voltar\n");
			System.out.print("Informe o que você deseja: ");
			op = Util.leInteiroConsole(input);
			List<Filme> resultado = new ArrayList<>();
			input.nextLine();
			switch(op){
				case 1:
					System.out.print("Nome: ");
					titulo = Util.leStringConsole(input);
					resultado = controle.buscarFilme(titulo);
					break;
				case 2:
					System.out.print("Genero: ");
					genero = Util.leStringConsole(input);
					resultado = controle.buscarFilme(genero);
					break;
				case 3:
					System.out.print("Disponibilidade: ");
					disponibilidade = Util.leInteiroConsole(input);
					resultado = controle.buscarFilme(disponibilidade);
					break;
				case 4:
					return;
				default:
					System.out.println("Opção Inválida!");
			}
			exibirFilmes(resultado);
			return;
		}while(continua == true);
	}
		
	private void exibirFilmes(List<Filme> filmes) {
	    int i = 1, op = 0;
	    List<Filme> resultado = new ArrayList<>();
	    if (filmes.isEmpty()) {
	        System.out.println("Não existe esse filme em seu cadastro.");
	    } else {
	    	System.out.println();
	        for (Filme f : filmes) {
	            System.out.println(i + " - Nome: " + f.getTitulo() +
	                    " | Gênero: " + f.getGenero() +
	                    " | Tipo: " + f.getTipoFilme() +
	                    " | Ano: " + f.getAnoLancado() +
	                    " | DVD's disponíveis: " + f.getQuantidadeDvds() +
	                    " | Blu-rays disponíveis: " + f.getQuantidadeBluerays());
	            i++;
	            resultado.add(f);
	        }
	    }
	    if (!filmes.isEmpty()) {
	        System.out.print("\nEscolha o n° do filme que deseja modificar: ");
	        op = Util.leInteiroConsole(input);
	        if (op >= 1 && op <= filmes.size()) {
	            Filme escolhaFilme = resultado.get(op - 1);
	            modificarFilmes(escolhaFilme);
	        } else {
	            System.out.println("Opção de filme inválida.");
	        }
	    }
	}

	public void modificarFilmes(Filme filme) {
	    input.nextLine();
	    boolean continua = true;
	    int op = 0, escolhaCliente = 0, tipo = 0, ano, mes;

	    do {    
	        System.out.println("\nDigite a opção:\n\t1 - Editar filme\n\t2 - Excluir filme\n\t3 - Locar para cliente\n\t4 - Voltar\n");
	        System.out.print("Informe o que você deseja: ");
	        op = Util.leInteiroConsole(input);
	        System.out.println();
	        switch (op) {
	            case 1:
	                input.nextLine();
	                String titulo, genero, tipoFilme;  
	                int anoLancado, quantidadeDvds, quantidadeBluerays;

	                System.out.print("Digite o nome do filme: ");
	                titulo = Util.leStringConsole(input);
	                System.out.print("Digite o gênero do filme: ");
	                genero = Util.leStringConsole(input);
	                System.out.print("Digite o tipo do filme: ");
	                tipoFilme = Util.leStringConsole(input);
	                System.out.print("Digite o ano de lançamento do filme: ");
	                anoLancado = Util.leInteiroConsole(input);
	                System.out.print("Digite a quantidade de DVD's do filme: ");
	                quantidadeDvds = Util.leInteiroConsole(input);
	                System.out.print("Digite a quantidade de Blu-rays do filme: ");
	                quantidadeBluerays = Util.leInteiroConsole(input);

	                if (titulo.isBlank() || genero.isBlank() || tipoFilme.isBlank()) {
	                    System.out.println("Todos os campos devem ser preenchidos!");
	                } else {
	                    Filme atualizarFilme = new Filme(titulo, anoLancado, genero, quantidadeDvds, quantidadeBluerays, tipoFilme);
	                    if (!atualizarFilme.setTipoFilme(tipoFilme)) {
	                        System.out.println("\nTipo de filme inválido. Deve ser Lançamento, Novo ou Antigo.");
	                    } else if (controle.verificarFilmeRepetido(atualizarFilme)) {
	                        System.out.println("Esse filme já existe em seu cadastro!");
	                    } else {
	                        filme.setTitulo(titulo);
	                        filme.setGenero(genero);
	                        filme.setTipoFilme(tipoFilme);
	                        filme.setAnoLancado(anoLancado);
	                        filme.setQuantidadeDvds(quantidadeDvds);
	                        filme.setQuantidadeBluerays(quantidadeBluerays);
	                        System.out.println("\nFilme atualizado com sucesso!");
	                    }
	                }
	                break;

	            case 2:
	                controle.exluirFilmes(filme);
	                System.out.println("Filme removido com sucesso!");
	                return;

	            case 3:
	                System.out.println("Deseja locar um DVD ou Blu-ray?\n\t1 - DVD\n\t2 - Blu-ray");
	                System.out.print("Tipo de locação: ");
	                tipo = Util.leInteiroConsole(input);
	                input.nextLine();
	                if(tipo == 1 || tipo ==2) {
	                } else {
	                	System.out.println("Opção invalida.");
	                	return;
	                }

	                List<Cliente> clientes = controle.getClientes();
	                System.out.print("Digite o nome do cliente ou dependente para locar o filme: ");
	                String nome = Util.leStringConsole(input);
	                
	                System.out.print("Digite o mês de locação: ");
	                while (true) {
	                    mes = Util.leInteiroConsole(input);
	                    if (mes < 1 || mes > 12) {
	                        System.out.print("Digite um mês válido: ");
	                    } else {
	                        break;
	                    }
	                }
	                System.out.print("Digite o ano: ");
	                while(true){
	        			ano = Util.leInteiroConsole(input);
	        			if (String.valueOf(ano).length() == 4) { 
	        		        break;
	        		    } else {
	        		        System.out.print("Digite um ano válido (com 4 dígitos): ");
	        		    }
	        		}

	                List<Cliente> clientesEncontrados = new ArrayList<>();
	                Cliente clienteEscolhido = null;
	                
	                for (Cliente c : clientes) {  
	                    if (c.getNome().equalsIgnoreCase(nome)) {
	                        clientesEncontrados.add(c);
	                    } else {
	                        for (Dependentes d : c.getDependentes()) {
	                            if (d.getNome().equalsIgnoreCase(nome)) {
	                                clientesEncontrados.add(c);
	                                break;
	                            }
	                        }
	                    }
	                }

	                if (clientesEncontrados.isEmpty()) {
	                    System.out.println("Cliente não encontrado.");
	                } else if (clientesEncontrados.size() == 1) {
	                    Cliente cliente = clientesEncontrados.get(0);
	                    if (!controle.podeAlugarFilme(cliente)) {
	                        System.out.println("O cliente atingiu o número máximo de filmes locados!");
	                    } else if (!controle.locarFilmeCliente(cliente, filme, tipo)) {
	                        System.out.println("Filme indisponível no momento.");
	                    } else {
	                        controle.cadastrarEntrada("Pagamento de locação", "Cliente: " + cliente.getNome() + " Filme: " + filme.getTitulo(), controle.getValorLocacaoDiaria(filme), mes, ano);
	                        System.out.println("Filme locado com sucesso!");
	                    }
	                } else {
	                    System.out.println("Foram encontrados múltiplos clientes com esse nome:");
	                    for (int i = 0; i < clientesEncontrados.size(); i++) {
	                        Cliente cliente = clientesEncontrados.get(i);
	                        System.out.println((i + 1) + " - Nome: " + cliente.getNome() + " | Código: " + cliente.getCodigo() + " | CPF: " + cliente.getCpf());
	                    }
	                    System.out.print("Escolha o número do cliente que deseja locar o filme: ");
	                    escolhaCliente = Util.leInteiroConsole(input) - 1;

	                    if (escolhaCliente >= 0 && escolhaCliente < clientesEncontrados.size()) {
	                        clienteEscolhido = clientesEncontrados.get(escolhaCliente);
	                        if (!controle.podeAlugarFilme(clienteEscolhido)) {
	                            System.out.println("O cliente atingiu o número máximo de filmes locados!");
	                        } else if (!controle.locarFilmeCliente(clienteEscolhido, filme, tipo)) {
	                            System.out.println("Filme indisponível no momento.");
	                        } else {
	                            controle.cadastrarEntrada("Pagamento de locação", "Cliente: " + clienteEscolhido.getNome() + " Filme: " + filme.getTitulo(), controle.getValorLocacaoDiaria(filme), mes, ano);
	                            System.out.println("Filme locado com sucesso!");
	                        }
	                    } else {
	                        System.out.println("Opção inválida.");
	                    }
	                }
	                break;

	            case 4:
	                return;

	            default:
	                System.out.println("Opção inválida!");
	        }
	    } while (continua);
	}
	

	private void imprimeListaFilmes() {
		System.out.println("******** LISTA DE FILMES CADASTRADOS *********");
		List<Filme> filmes = controle.getFilmes();
	    boolean filmesDisponiveis = false;

	    for (Filme f : filmes) {
	        if (f.getQuantidadeBluerays() > 0 || f.getQuantidadeDvds() > 0) {
	        	System.out.println(f.toString());
	            filmesDisponiveis = true;
	        }
	    }

	    if (!filmesDisponiveis) {
	        System.out.println("Nenhum filme disponível no momento.");
	    }
		System.out.println("******** FIM DA LISTA DE FILMES  *********");
	}
}