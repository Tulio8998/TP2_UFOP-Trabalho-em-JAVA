package br.ufop.trabalho.menuIO;

import java.util.Scanner;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.disco.Disco;

public class MenuConsole {
	
	private Controle controle;
	private Disco disco;
	private String arquivoClientes = "clientes.data";
    private String arquivoFilmes = "filmes.data";
    private String arquivoMovimentacoes = "movimentacoes.data";
    
	private MenuClienteConsole menuCliente;
	private MenuFilmesConsole menuFilmes;
	private MenuRelatorioConsole menuRelatorio;
	private MenuBalanceteConsole menuBalancete;
	private Scanner input;
	
	public MenuConsole(){
		controle = new Controle();
		input = new Scanner(System.in);
		
		disco = new Disco(controle);
		disco.carregarClientes(arquivoClientes);
		disco.carregarFilmes(arquivoFilmes);
		disco.carregarMovimentacoes(arquivoMovimentacoes);
		
		menuCliente = new MenuClienteConsole(controle, input);
		menuFilmes = new MenuFilmesConsole(controle, input);
		menuRelatorio = new MenuRelatorioConsole(controle, input);
		menuBalancete = new MenuBalanceteConsole(controle, input);
		
	}
	
	public void inicioExecucao() {
        boolean continua = true;
        do {
            continua = exibeMenuPrincipal();
        } while (continua == true);
        disco.salvarClientes(arquivoClientes);
        disco.salvarFilmes(arquivoFilmes);
        disco.salvarMovimentacoes(arquivoMovimentacoes);
        System.out.println("Obrigado por usar o sistema!");
    }
	
	private boolean exibeMenuPrincipal(){
		System.out.println("Seja bem vindo a Locadora de Filmes.\nEscolha uma das opções abaixo.\n\t1 - Filme\n\t2 - Clientes\n\t3 - Relatórios\n\t4 - Balancete\n\t5 - Sair ");
		System.out.print("Informe o que você deseja: ");
		int op = Util.leInteiroConsole(input);
		switch (op) {
		case 1:
			menuFilmes.exibeMenuFilmes();
			break;	
		case 2:
			menuCliente.exibeMenuClientes();		
			break;
		case 3:
			menuRelatorio.exibeMenuRelatorio();
			break;	
		case 4:
			menuBalancete.exibirMenuBalancete();
			break;	
		case 5:
			return false;		
		default:
				System.out.println("\nOpção Inválida");
		}
		return true;
	}
	
}