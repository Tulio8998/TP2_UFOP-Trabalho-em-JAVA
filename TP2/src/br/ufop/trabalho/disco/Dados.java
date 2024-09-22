package br.ufop.trabalho.disco;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.ufop.trabalho.entities.Cliente;
import br.ufop.trabalho.entities.Filme;

public class Dados {

	public static void salvarClientes(ArrayList<Cliente> clientes, String nomeArquivo) {
        try {
        	FileOutputStream arquivoGrav = new FileOutputStream(nomeArquivo);
        	ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);
        	objGravar.writeObject(clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
   /* public static void salvarFilmes(ArrayList<Filme> filmes, String nomeArquivo) {
        try {
            //out.writeObject(filmes);  // Grava o ArrayList de filmes no arquivo
            System.out.println("Filmes salvos com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
	
	@SuppressWarnings("unchecked")
    public static ArrayList<Cliente> carregarClientes(String nomeArquivo) {
        try {
        	FileInputStream arquivoLeit = new FileInputStream(nomeArquivo);
        	ObjectInputStream objLeit = new ObjectInputStream(arquivoLeit);
            return (ArrayList<Cliente>) objLeit.readObject();  // Lê o ArrayList de clientes do arquivo
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();  // Retorna uma lista vazia se falhar
    }
    
    @SuppressWarnings("unchecked")
    public static ArrayList<Filme> carregarFilmes(String nomeArquivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (ArrayList<Filme>) in.readObject();  // Lê o ArrayList de filmes do arquivo
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();  // Retorna uma lista vazia se falhar
    }
	
}
