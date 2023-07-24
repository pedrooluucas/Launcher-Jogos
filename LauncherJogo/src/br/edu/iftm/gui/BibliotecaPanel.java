package br.edu.iftm.gui;

import javax.swing.*;
import org.json.*;
import br.edu.iftm.gui.componentes.*;
import br.edu.iftm.modelos.Jogo;

import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.*;
import java.io.*;



public class BibliotecaPanel extends TelaPanel {
	private ArrayList<Jogo> jogos;
	private JPanel grid;
	private Imagem imagemFundo;
	private Jogo jogoSelecionado;
	private JLabel labelJogo;
	
	public BibliotecaPanel(JPanel telas, JFrame janela) {
		super(telas, janela);
		this.jogos = new ArrayList<Jogo>();
		
		
		grid = new JPanel(new FlowLayout(FlowLayout.LEFT, 22, 22));
		grid.setBackground(Color.decode("#202028"));
		
		
		carregarJogos();
		exibirJogos();
		
		
		JScrollPane scrollPanel = new JScrollPane(grid);
		scrollPanel.setBounds(50, 300, 1456, 300);
		
		labelJogo = new JLabel("San Andreas MultiPlayer");
		labelJogo.setBounds(50, 50, 1000, 60);
		
		labelJogo.setFont(new Font("Roboto", Font.BOLD, 60));
		
		Botao botaoJogar = new Botao("JOGAR");
		botaoJogar.setBounds(1300, 200, 140, 60);
		botaoJogar.setFont(new Font("Roboto", Font.PLAIN, 30));
		botaoJogar.setForeground(Color.WHITE);
		
		trocarImagemFundo("samp-fundo.jpg");
		
		this.add(scrollPanel);
		this.add(labelJogo);
		this.add(botaoJogar);
		this.add(imagemFundo);
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
		
		imagem.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jogoSelecionado = jogo; 	
				String caminho = jogo.getCaminho();
				String fundo = jogo.getFundo();
				trocarImagemFundo(fundo);
				//executarJogo(caminho);
			}
		});
		
		grid.add(imagem);
		} 
	} 
	

	private void trocarImagemFundo(String imagem) {
		if(imagemFundo != null) {
			remove(imagemFundo);
		}
		imagemFundo = new Imagem(imagem);
		imagemFundo.setBounds(0, 0, 1920, 1080);
		add(imagemFundo);
		repaint();
		revalidate();
	}
	
	
	
	private void carregarJogos() {
		String jsonString = lerJsonJogos();
		JSONArray jsonArray = new JSONArray(jsonString);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jogoJObject = (JSONObject) jsonArray.get(i);
			
			jogos.add(new Jogo(jogoJObject));
		}
		
	}
	
	
	private void executarJogo(String caminho) {
		File arquivo = new File(caminho);
		String pasta = arquivo.getAbsoluteFile().getParent();
		//System.out.println(pasta);
		try {
			Runtime.getRuntime().exec(caminho, null, new File(pasta));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
