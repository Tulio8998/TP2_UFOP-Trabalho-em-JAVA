package br.ufop.trabalho.controle;

import java.util.ArrayList;
import java.util.List;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.entities.Cliente;
import br.ufop.trabalho.entities.Data;
import br.ufop.trabalho.entities.Dependentes;
import br.ufop.trabalho.entities.Filme;


/***
 * Classe de regra de negócios da aplicação. Esta classe deverá controlar todos os dados que serão armazenados no sistema (clientes, filmes e a parte financeira).
 *  Ela é a porta de entrada para acesso a todos os dados e deverá também fazer as verificações necessárias no momento dos cadastros. 
 * É necessário perceber a importância dessa classe que implementa todas as regras de negócio da aplicação. A classe Controle  poderá 
 * ser utilizada para qualquer tipo de interface gráfica inclusive com janelas.
 * 
 *   IMPORTANTE: ESTA CLASSE NAO DEVE TER ENTRADA E SAÍDA DE DADOS PARA O USUÁRIO
 * @author Filipe
 *
 */
public class Controle {
	//Array de clientes
	private ArrayList <Cliente> clientes;
	private ArrayList <Filme> filmes;

	private double valorLocacaoDiaria;
	private double valorMultaPorDia;
	private int quantidadeMaximaFilmesAlugados;
	private Cliente cliente;
	
	public Controle(){
		clientes = new ArrayList<Cliente>();
		filmes = new ArrayList<Filme>();
	}
	
	public boolean verificarClienteRepetido(Cliente cliente) {
		for (Cliente c : clientes ) {
			if (c.equals(cliente)) {
                
                return true;
            }
        }
        return false;
	}
	
	public int cadastrarCliente(String nome, String end, int codigo, String cpf, Data data){
        cliente = new Cliente(nome, end, codigo, cpf, data);
        if(Util.verificaListaStringPreenchida(nome, end, cpf) == false ){
            return Constantes.ERRO_CAMPO_VAZIO;
        }
        if (verificarClienteRepetido(cliente)) {
            return Constantes.CLIENTE_REPETIDO;
        }

        this.clientes.add(cliente);
        //salvarClientes();
        return Constantes.RESULT_OK;
    }

	
	public boolean verificarDependenteRepetido(Dependentes dependente) {
		for (Dependentes d : cliente.getDependentes() ) {
			if (d.equals(dependente)) {
                
                return true;
            }
        }
        return false;
	}
    public int cadastrarDependente(String nome, String end, String cpf, Data data){
    	Dependentes dependente =  new Dependentes(nome, end, cpf, data);
        if(Util.verificaListaStringPreenchida(nome, end, cpf) == false ){
            return Constantes.ERRO_CAMPO_VAZIO;
        }        
         if (verificarDependenteRepetido(dependente)) {
        	 return Constantes.DEPENDENTE_REPETIDO;
        
    	} else if (cliente.getDependentes().size() >= 3) {
                return Constantes.ERROR_LIMITE_DEPENDENTE;
            } 
            cliente.adicionarDependentes(dependente);
            return Constantes.RESULT_OK;

    }
	
	public int getQtdClientes(){
		return clientes.size();
	}
	public int getQtdFilmes(){
		return filmes.size();
	}
	
	public Cliente getClienteNaPosicao(int pos){
		if(pos >=0 && pos < getQtdClientes()){
			return clientes.get(pos);
		}
		return null;
	}
	
	public List<Cliente> getCliente(){
		return clientes;
	}
	
	public Filme getFilmesNaPosicao(int pos){
		if(pos >=0 && pos < getQtdFilmes()){
			return filmes.get(pos);
		}
		return null;
	}
	
	public boolean verificarFilmeRepetido(Filme filme) {
		for (Filme f : filmes ) {
			if (f.equals(filme)) {
                
                return true;
            }
        }
        return false;
		
	}
	
	 public int cadastrarFilme(String titulo, int anoLancado, String genero, int quantidadeDvds, int quantidadeBluerays,
             String tipoFilme) {
		Filme novofilme = new Filme(titulo, anoLancado, genero, quantidadeDvds, quantidadeBluerays, tipoFilme);	
		if (!novofilme.setTipoFilme(tipoFilme)) {
			return Constantes.ERRO_TIPO_FILME;
		} 
		
		if (verificarFilmeRepetido(novofilme)) {
			return Constantes.FILME_REPETIDO;
		} else if (titulo.isBlank() || genero.isBlank() ||  tipoFilme.isBlank()) {
			return Constantes.ERRO_CAMPO_VAZIO;
		} else {
			this.filmes.add(novofilme);
			return Constantes.RESULT_OK;
		}
	}
	
	public List<Filme> buscarFilme(Object busca) {
		List<Filme> resultado =  new ArrayList<>();
		for(Filme f : filmes) {
			if(busca instanceof Integer) {
				int quantidade = (Integer) busca;
				if(f.getQuantidadeBluerays() == quantidade || f.getQuantidadeDvds() == quantidade) {
					resultado.add(f);
				}
			} else if (busca instanceof String) {
				String filme = (String) busca;
				if (f.getTitulo().equalsIgnoreCase(filme) || f.getGenero().equalsIgnoreCase(filme)) {
					resultado.add(f);
				}
			}
		}
		return resultado;
	
	}
	
	public List<Cliente> buscaCliente(Object busca) {
		List<Cliente> resultado = new ArrayList<>();
		for(Cliente c : clientes) {
			if(busca instanceof Integer) {
				int codigo = (Integer) busca;
				if(c.getCodigo() == codigo) {
					resultado.add(c);
				}
			} else if(busca instanceof String) {
				String cliente = (String) busca;
				if(c.getNome().equalsIgnoreCase(cliente)) {
					resultado.add(c);
				}

			}
		}

		return resultado;

		}
	
	public List<Filme> getFilmes(){
		return filmes;
	}
	
	public void exluirFilmes(Filme filme) {
			if(!filmes.isEmpty()) {
				filmes.remove(filme);
			}
	}
	 
	public void informacoesDoSistema (double locacaoDiaria, double multaPorDia, int maxFilmesAlugados) {
        valorLocacaoDiaria = locacaoDiaria;
        valorMultaPorDia = multaPorDia;
        quantidadeMaximaFilmesAlugados = maxFilmesAlugados;
    }

	public double getValorLocacaoDiaria() {
		return valorLocacaoDiaria;
	}
	public double getValorMultaPorDia() {		
		return valorMultaPorDia;
	}
	public int getQuantidadeMaximaFilmesAlugados() {
		return quantidadeMaximaFilmesAlugados;
	}
}