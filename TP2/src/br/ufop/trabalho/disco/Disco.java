package br.ufop.trabalho.disco;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Cliente;
import br.ufop.trabalho.entities.Filme;
import br.ufop.trabalho.entities.Funcionario;

public class Disco {

	Controle controle = new Controle();
	
	public Disco(Controle c) {
		super();
		this.controle = c;
	}

	public void salvarClientes(String nomeArquivo) {
        try (ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
        	obj.writeObject(controle.getClientes());
        } catch (IOException e) {
        }
    }

    @SuppressWarnings("unchecked")
    public void carregarClientes(String nomeArquivo) {
        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
        	ArrayList<Cliente> clientesCarregados = (ArrayList<Cliente>) obj.readObject();
        	controle.setClientes(clientesCarregados);
        } catch (IOException | ClassNotFoundException e) {
        }
    }
    
    public void salvarFilmes(String nomeArquivo) {
        try (ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
        	obj.writeObject(controle.getFilmes());
        } catch (IOException e) {
        }
    }


    @SuppressWarnings("unchecked")
	public void carregarFilmes(String nomeArquivo) {
        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
        	ArrayList<Filme> filmesCarregados = (ArrayList<Filme>) obj.readObject();
        	controle.setFilmes(filmesCarregados);
        } catch (IOException | ClassNotFoundException e) {
        }
    }
	
}
