package br.ufop.trabalho.entities;

/***
 * Falta implementar
 * @author filipe
 *
 */
public class Filme {

	private String nome;
	private int anoLancado;
	private String genero;
	private int quantidadeDvds;
	private int quantidadeBluerays;
	private String tipoFilme;
	
	public Filme(String nome, int anoLancado, String genero, int quantidadeDvds, int quantidadeBluerays,
			String tipoFilme) {
		super();
		this.nome = nome;
		this.anoLancado = anoLancado;
		this.genero = genero;
		this.quantidadeDvds = quantidadeDvds;
		this.quantidadeBluerays = quantidadeBluerays;
		this.tipoFilme = tipoFilme;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAnoLancado() {
		return anoLancado;
	}

	public void setAnoLancado(int anoLancado) {
		this.anoLancado = anoLancado;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getQuantidadeDvds() {
		return quantidadeDvds;
	}

	public void setQuantidadeDvds(int quantidadeDvds) {
		this.quantidadeDvds = quantidadeDvds;
	}

	public int getQuantidadeBluerays() {
		return quantidadeBluerays;
	}

	public void setQuantidadeBluerays(int quantidadeBluerays) {
		this.quantidadeBluerays = quantidadeBluerays;
	}

	public String getTipoFilme() {
		return tipoFilme;
	}

	public void setTipoFilme(String tipoFilme) {
		this.tipoFilme = tipoFilme;
	}
	
	
	
}
