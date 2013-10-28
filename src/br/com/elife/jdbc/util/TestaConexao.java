package br.com.elife.jdbc.util;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.elife.jdbc.ConnectionFactory;

public class TestaConexao {
	
	public static void main(String[] args) throws SQLException {
		Connection connection = new ConnectionFactory().getConnection();
		System.out.println("Conex‹o aberta!");
		connection.close();
	}

}
