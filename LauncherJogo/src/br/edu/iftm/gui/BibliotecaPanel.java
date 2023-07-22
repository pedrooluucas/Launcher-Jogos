package br.edu.iftm.gui;

import javax.swing.*;
import org.json.*;
import br.edu.iftm.gui.componentes.*;
import br.edu.iftm.modelos.Jogo;
import java.awt.event.*;
import java.util.*;
import java.io.*;



public class BibliotecaPanel extends TelaPanel {
	private ArrayList<Jogo> jogos;
	
	public BibliotecaPanel(JPanel telas, JFrame janela) {
		super(telas, janela);
		this.jogos = new ArrayList<Jogo>();
		
    
		carregarJogos();
		exibirJogos();
	}
	
	
	private void carregarJogos() {
		String jsonString = lerJsonJogos();
		JSONArray jsonArray = new JSONArray(jsonString);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jogoJObject = (JSONObject) jsonArray.get(i);
			
			jogos.add(new Jogo(jogoJObject));
		}
		
	}
	
	
	private void exibirJogos() {
		
		int posicaoX = 50;
		int posicaoY = 50;
		final int POSICAO_MAX_X = 1250;
		
		 for (Jogo jogo : jogos) {
		String icone = jogo.getIcone();
		Imagem imagem = new Imagem(icone);
		imagem.setBounds(posicaoX, posicaoY, 256, 256);
			
		posicaoX += 30 + 256;
		if (posicaoX > POSICAO_MAX_X) {
			posicaoX = 50;
			posicaoY += 30 + 256;
		}
		
		this.add(imagem);
		} 
	} 


	private String lerJsonJogos()  {
		File arquivoJson = new File("jogos.json");
		StringBuilder conteudoArquivo = new StringBuilder();
		
		try {
			BufferedReader leitor = new BufferedReader(new FileReader(arquivoJson));
			String linha = leitor.readLine();
			while(linha != null) {
				conteudoArquivo.append(linha);
				linha = leitor.readLine();
			}
			
			leitor.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conteudoArquivo.toString();
	}
}
