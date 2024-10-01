
package br.ufop.trabalho.controle;

import java.util.ArrayList;
import java.util.List;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.movimentacao.Entrada;
import br.ufop.trabalho.movimentacao.Movimentacao;
import br.ufop.trabalho.movimentacao.Saida;
import br.ufop.trabalho.entities.Cliente;
import br.ufop.trabalho.entities.Data;
import br.ufop.trabalho.entities.Dependentes;
import br.ufop.trabalho.entities.Filme;
import br.ufop.trabalho.entities.Funcionario;


public class Controle {

	private ArrayList <Cliente> clientes;
	private ArrayList <Filme> filmes;
	private ArrayList<Movimentacao> movimentacoes;
	private ArrayList <Funcionario> funcionarios;

	private double valorMultaPorDia;
	private int quantidadeMaximaFilmesAlugados;
	private Cliente cliente;
	
	public Controle(){
		clientes = new ArrayList<Cliente>();
		filmes = new ArrayList<Filme>();
		movimentacoes = new ArrayList<Movimentacao>();
		funcionarios = new ArrayList<Funcionario>();
		this.valorMultaPorDia = 5.0;
		this.quantidadeMaximaFilmesAlugados = 5;
		funcionariosFixos();
	}
	
	private void funcionariosFixos() {
        Funcionario funcionario1 = new Funcionario("Túlio", "Nova Era", "123.123.123-12", 10000, 01);
        Funcionario funcionario2 = new Funcionario("Chrystian", "João Monlevade", "234.234.234-23", 5000, 02);
        Funcionario funcionario3 = new Funcionario("Davi", "João Monlevade", "345.345.345-34", 5000, 03);
        
        funcionarios.add(funcionario1);
        funcionarios.add(funcionario2);
        funcionarios.add(funcionario3);
    }
	
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public ArrayList<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public ArrayList<Movimentacao> getMovimentacaos() {
		return movimentacoes;
	}

	public void setMovimentacaos(ArrayList<Movimentacao> movimentacaos) {
		this.movimentacoes = movimentacaos;
	}
	
	public List<Filme> getFilmes(){
		return filmes;
	}

	public void setFilmes(ArrayList<Filme> filmes) {
		this.filmes = filmes;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public double getValorMultaPorDia() {		
		return valorMultaPorDia;
	}
	
	public void setValorMultaPorDia(double valorMultaPorDia) {
		if(valorMultaPorDia>0) {
			this.valorMultaPorDia = valorMultaPorDia;
		}
	}
	
    public void setLimiteFilmesPorCliente(int limite) {
        if (limite > 0) {
            this.quantidadeMaximaFilmesAlugados = limite;
        }
    }

    public int getQtdClientes(){
		return clientes.size();
	}
	
	public int getQtdFuncionarios(){
		return funcionarios.size();
	}
	
	public int getQtdFilmes(){
		return filmes.size();
	}
	
	public int getQuantidadeMaximaFilmesAlugados() {
		return quantidadeMaximaFilmesAlugados;
	}
    
    public boolean podeAlugarFilme(Cliente cliente) {
        return cliente.getFilmes().size() <= quantidadeMaximaFilmesAlugados;
    }
    
    public void aplicarMulta(Cliente cliente, int diasAtraso) {
        if (diasAtraso > 0) {
            double multa = diasAtraso * valorMultaPorDia;
            cliente.setMulta(multa);
        }        
    }
    
    public double getValorLocacaoDiaria(Filme filme) {
		if(filme.getTipoFilme().equals("antigo")){
			return 5;
		}
		else if(filme.getTipoFilme().equals("novo")){
			return 7.5;
		}
		else{
			return 10;
		}
	}
      
    public Cliente getClienteNaPosicao(int pos){
		if(pos >=0 && pos < getQtdClientes()){
			return clientes.get(pos);
		}
		return null;
	}
	
	public Funcionario getFuncionarioNaPosicao(int pos){
		if(pos >=0 && pos < getQtdFuncionarios()){
			return funcionarios.get(pos);
		}
		return null;
	}
	
	public Filme getFilmesNaPosicao(int pos){
		if(pos >= 0 && pos < getQtdFilmes()){
			return filmes.get(pos);
		}
		return null;
	}
    
	public boolean verificarClienteRepetido(Cliente cliente) {
		for (Cliente c : clientes ) {
			if (c.equals(cliente)) {             
                return true;
            }
        }
        return false;
	}
	
	public boolean verificarDependenteRepetido(Dependentes dependente) {
		for (Dependentes d : cliente.getDependentes() ) {
			if (d.equals(dependente)) {                
                return true;
            }
        }
        return false;
	}
	
	public boolean verificarFilmeRepetido(Filme filme) {
		for (Filme f : filmes ) {
			if (f.equals(filme)) {                
                return true;
            }
        }
        return false;
		
	}
	
	public void cadastrarEntrada(String nome, String descricao, double valor, int mes, int ano) {
        this.movimentacoes.add(new Entrada(nome, descricao, valor, mes, ano));
    }

    public void cadastrarSaida(String nome, String descricao, double valor, int mes, int ano) {
        this.movimentacoes.add(new Saida(nome, descricao, valor, mes, ano));
    }
	
	public int cadastrarCliente(String nome, String end, int codigo, String cpf, Data data){
        cliente = new Cliente(nome, end, codigo, cpf, data);
        if(Util.verificaListaStringPreenchida(nome, end, cpf) == false ){
            return Constantes.ERRO_CAMPO_VAZIO;
        }
        if(verificarClienteRepetido(cliente)) {
            return Constantes.CLIENTE_REPETIDO;
        }
        this.clientes.add(cliente);
        return Constantes.RESULT_OK;
    }
	
	public int cadastrarDependente(String nome, String end, String cpf, Data data) {
	    if(cliente == null) {
	        return Constantes.ERRO_CLIENTE_NAO_SELECIONADO;
	    }

	    Dependentes dependente = new Dependentes(nome, end, cpf, data);
	    if(Util.verificaListaStringPreenchida(nome, end, cpf) == false) {
	        return Constantes.ERRO_CAMPO_VAZIO;
	    }
	    if(verificarDependenteRepetido(dependente)) {
	        return Constantes.DEPENDENTE_REPETIDO;
	    } else if (cliente.getDependentes().size() >= 3) {
	        return Constantes.ERROR_LIMITE_DEPENDENTE;
	    }
	    cliente.adicionarDependentes(dependente);
	    return Constantes.RESULT_OK;
	}
	
	 public int cadastrarFilme(String titulo, int anoLancado, String genero, int quantidadeDvds, int quantidadeBluerays,
             String tipoFilme) {
		Filme novofilme = new Filme(titulo, anoLancado, genero, quantidadeDvds, quantidadeBluerays, tipoFilme);	
		if(!novofilme.setTipoFilme(tipoFilme)) {
			return Constantes.ERRO_TIPO_FILME;
		} 
		
		if(verificarFilmeRepetido(novofilme)) {
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
				if(f.getQuantidadeBluerays() == 0 || f.getQuantidadeDvds() == 0) {
					return resultado;
				}
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
	    for (Cliente c : clientes) {
	        if (busca instanceof Integer) {
	            int codigo = (Integer) busca;
	            if (c.getCodigo() == codigo) {
	                resultado.add(c);
	            }
	        } else if (busca instanceof String) {
	            String cliente = (String) busca;
	            if (c.getNome() != null && c.getNome().equalsIgnoreCase(cliente)) {
	                resultado.add(c);
	            }
	        }
	    }
	    return resultado;
	}
	
	public List<Cliente> buscaDependentes(String dependente){
		List<Cliente> resultado = new ArrayList<>();
		List<Dependentes> listaDependentes = new ArrayList<>();
		if(dependente.isEmpty()){
			return resultado;
		}
		else{
			for(Cliente c : clientes){
				listaDependentes = c.getDependentes();
				if(!listaDependentes.isEmpty()){
					for(Dependentes d : listaDependentes){
						if(d.getNome() != null && d.getNome().equals(dependente))
						{
							resultado.add(c);
						}
					}	
				}
			}
		}
		return resultado;
	}
	
	public List<Movimentacao> buscarMovimentacaoPorNome(String nome) {
        List<Movimentacao> resultados = new ArrayList<>();
        for (Movimentacao m : movimentacoes) {
            if (m.getNome().equalsIgnoreCase(nome)) {
                resultados.add(m);
            }
        }
        return resultados;
    }
	
	public double calcularBalancetePorMes(int mes) {
        double totalEntradas = 0;
        double totalSaidas = 0;
        for (Movimentacao m : movimentacoes) {
            if (m.getMes() == mes) {
                if (m instanceof Entrada) {
                    totalEntradas += m.getValor();
                } else if (m instanceof Saida) {
                    totalSaidas += m.getValor();
                }
            }
        }
        double saldo = totalEntradas - totalSaidas;
        System.out.println("Entradas: R$ " + totalEntradas);
        System.out.println("Saídas: R$ " + totalSaidas);
        System.out.println("Saldo do mês: R$ " + saldo);
        return saldo;
    }

	public double calcularBalancetePorAno(int ano) {
        double totalEntradas = 0;
        double totalSaidas = 0;
        for (Movimentacao m : movimentacoes) {
            if (m.getAno() == ano) {
                if (m instanceof Entrada) {
                    totalEntradas += m.getValor();
                } else if (m instanceof Saida) {
                    totalSaidas += m.getValor();
                }
            }
        }
        double saldo = totalEntradas - totalSaidas;
        System.out.println("Entradas: R$ " + totalEntradas);
        System.out.println("Saídas: R$ " + totalSaidas);
        System.out.println("Saldo do ano: R$ " + saldo);
        return saldo;
    }
	
	public boolean locarFilmeCliente(Cliente cliente, Filme filme, int tipo) {
		if (!podeAlugarFilme(cliente)) {
	        return false;
	    }
	    if (tipo == 1) {
	    	if (filme.getQuantidadeDvds() == 0) {
	    		return false;
	    	} else {
				cliente.getFilmes().add(filme);
	    		int filmeLocado = filme.getQuantidadeDvds() - 1;
		        filme.setQuantidadeDvds(filmeLocado);
				cliente.getTipoMidiaLocada().add("DVD");
	    	}
	        return true;
	    } else if (tipo == 2) {
	    	if (filme.getQuantidadeBluerays() == 0) {
	    		return false;
	    	} else {
				cliente.getFilmes().add(filme);
		        int filmeLocado = filme.getQuantidadeBluerays() - 1;
		        filme.setQuantidadeBluerays(filmeLocado);
				cliente.getTipoMidiaLocada().add("Blu-ray");
	    	}
	        return true;
	    }
	    return false;
	}
	
	public void exluirFilmes(Filme filme) {
			if(!filmes.isEmpty()) {
				filmes.remove(filme);
			}
	}
	
	public void exluirMovimentacoes(Movimentacao movimentacao) {
		if(!movimentacoes.isEmpty()) {
			movimentacoes.remove(movimentacao);
		}
	}
	
}