package br.com.view;

import br.com.controller.Chave;
import br.com.model.ChavesDAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class MainProgram {
	
	static Chave chaveObj = new Chave();
	static ChavesDAO chDAO = new ChavesDAO();
	
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		System.out.println("*********************************  TUPINAMBA   ***************************************\n");
		keyRequest();
		
	}
	
	public static void keyRequest() {
		
		System.out.println("*********************************  NOVA CHAVE  ***************************************");
		chDAO.getKeyDB(chaveObj);
		chDAO.setStateUsing(chaveObj);
		activateKey();
		
	}
	
	public static void activateKey() {
		
		String keyCont = chaveObj.getKeyContent(); 
		String scriptAtivacao = keyCont;
		System.out.println(scriptAtivacao);
		int idkeyCont = chaveObj.getId();
		System.out.println("\nID: "+idkeyCont);
		int log = logValidate();
		validaLog(log);
		
	}
	
	public static String getInpLogValidate() {
		String inpLogUser = input.next();
		
		return inpLogUser;
	}
	
	public static String inputLogUser() {
		
		System.out.println("\nDigite o Status da Ativacao:\n(a) Chave Ativada\n(b) Chave Bloqueada\n\nStatus: ");
		String logVal = getInpLogValidate();
		return logVal;
	}
	
	public static int logValidate() {
		
		String inpLog = null;
		inpLog = inputLogUser();
		int auxLogInp = 0;

		if(inpLog.equals("a")) {
			auxLogInp = 3;
		}else if(inpLog.equals("b")) {
			auxLogInp = 2;
		}else {
			System.out.println("\nSTATUS INVALIDO, O PROGRAMA SERA ENCERRADO\n\nSeta pra CIMA e ENTER para executar o programa novamente | Use a nova chave gerada | A outra NAO sera perdida\n");
		}
		
		return auxLogInp;
		
	}
	
	public static void validaLog(int logParam) {
		if(logParam == 2) {
			System.out.println("CHAVE BLOQUEADA\n");
			setStateBloqued();
			keyRequest();
			
		}else if(logParam == 3) {
			System.out.println("CHAVE ATIVADA\n");
			setStateActived();
			
		}
	}
	
	public static void setStateBloqued() {
		
		chDAO.setStateForBloqued(chaveObj);
		
	}
	
	public static void setStateActived() {
		
		getSerialNumber();
		System.out.println("\nCopie e Cole abaixo o Serial da Maquina:\nSerial: ");
		String serial = input.next();
		chaveObj.setSerialnumber(serial);
		chDAO.setStateForActived(chaveObj);
		System.out.println("\nMAQUINA ATIVADA - O PROGRAMA PODE SER ENCERRADO\n");
		
	}
	
	public static void getSerialNumber(){

		String serialScript = "wmic bios get serialnumber";
		Runtime myRuntime = Runtime.getRuntime();
		Process pro;
		BufferedReader bufread;
		
		try {
			
			pro = myRuntime.exec(serialScript);
			bufread = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			System.out.println(bufread.readLine());
			String line;
			
			while((line = bufread.readLine()) != null ) {
				System.out.println(line);
			}
			
			
			
		} catch (Exception e) {
			System.out.println("ERRO: "+ e.getMessage());
		}

	}

}
