package br.com.controller;

public class Chave {

	private int id;
	private String keyContent;
	private String serialnumber;
	private int keystate;
	private String bancada = "b1"; 

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getKeyContent() {
		return keyContent;
	}
	
	public void setKeyContent(String keyContent) {
		this.keyContent = keyContent;
	}
	
	public String getSerialnumber() {
		return serialnumber;
	}
	
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	
	public int getKeystate() {
		return keystate;
	}
	
	public void setKeystate(int keystate) {
		this.keystate = keystate;
	}

	public String getBancada() {
		return bancada;
	}

	public void setBancada(String bancada) {
		this.bancada = bancada;
	}
	
}
