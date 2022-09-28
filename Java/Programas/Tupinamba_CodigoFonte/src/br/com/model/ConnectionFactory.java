package br.com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	static Connection conn = null;

	public static Connection createConnection() {
		
		try {
			
			String url = "jdbc:mysql://172.16.0.18:3306/dto_keys";
			String user = "dtoadm";
			String password = "dtoAdmin2022$lab";
			conn = DriverManager.getConnection(url,user,password);	
			//System.out.println("Conectou ao Banco IP 172.16.0.18");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("\n\nERRO NA CONEXAO BANCO\n\n");
		}
		
		return conn;
		
	}
	
}
