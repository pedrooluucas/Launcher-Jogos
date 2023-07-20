package br.edu.iftm.modelos;

import org.json.*;

public class Jogo {
	private String nome;
	private String caminho;
	private String icone;
	private String fundo;
	
	
	
	public Jogo(JSONObject jsonObject) {
		this.nome = jsonObject.getString("nome");
		this.caminho = jsonObject.getString("caminho");
		this.icone = jsonObject.getString("icone");
		this.fundo = jsonObject.getString("fundo");
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	
	
	public String getIcone() {
		return icone;
	}
	public void setIcone(String icone) {
		this.icone = icone;
	}
	
	
	public String getFundo() {
		return fundo;
	}
	public void setFundo(String fundo) {
		this.fundo = fundo;
	}
	
	
	
}
