package br.com.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.controller.Chave;

public class ChavesDAO extends ConnectionFactory{
	
	PreparedStatement pstm;
	
	public void getKeyDB(Chave ch) {
		
		try {
			
			createConnection();
			
			String querySelect = "SELECT idkey,keycontent FROM general_keys WHERE keystate=0 LIMIT 1;";
			pstm = (PreparedStatement) conn.prepareStatement(querySelect);
			ResultSet rs = pstm.executeQuery();
			
			if(rs.next()) {
				ch.setId(rs.getInt("idkey"));
				ch.setKeyContent(rs.getString("keycontent"));
			}else {
				System.out.println("ERRO AO PEGAR CHAVE");
			}
			
			conn.close();
			
		} catch (Exception e) {
			System.out.println("ERRO AO PEGAR CHAVE: "+ e.getMessage());
		}
		
	}
	
	public void setStateUsing(Chave ch) {
		
		try {
			
			createConnection();
			
			String queryUpdateForUsing = "UPDATE general_keys SET keystate=1,bancada=? WHERE idkey=?;";
			pstm = (PreparedStatement) conn.prepareStatement(queryUpdateForUsing);
			pstm.setString(1, ch.getBancada());
			pstm.setInt(2, ch.getId());
			pstm.execute();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("ERRO AO MUDAR ESTADO EM USO: "+ e.getMessage());
		}
		
	}
	
	public void setStateForBloqued(Chave ch) {
		
		try {
			
			createConnection();
			
			String queryUpdateForBloqued = "UPDATE general_keys SET keystate=2 WHERE idkey=?;";
			pstm = (PreparedStatement) conn.prepareStatement(queryUpdateForBloqued);
			pstm.setInt(1, ch.getId());
			pstm.execute();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("ERRO AO MUDAR ESTADO BLOQUEADA: "+ e.getMessage());
		}
		
	}
	
	public void setStateForActived(Chave ch) {
		
		try {
			
			createConnection();
			
			String queryUpdateForActived = "UPDATE general_keys SET serialcontent=?, keystate=3 WHERE idkey=?;";
			pstm = (PreparedStatement) conn.prepareStatement(queryUpdateForActived);
			pstm.setString(1, ch.getSerialnumber());
			pstm.setInt(2, ch.getId());
			pstm.execute();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("ERRO AO MUDAR ESTADO ATIVADO: "+ e.getMessage());
		}
		
	}
	
}
