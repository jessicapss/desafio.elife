package br.com.elife.jdbc;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	
	private final String PROPERTIES_FILE = "bd.properties";
	
	public Connection getConnection() {
		
		Properties prop = getProperties();
		try {
			Class.forName(prop.getProperty("bd.driver"));
			return DriverManager.getConnection(prop.getProperty("bd.path"), 
					prop.getProperty("bd.user"), prop.getProperty("bd.password"));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		
		
	}
	
	private Properties getProperties() {
			
		Properties props = new Properties();
		try {
			BufferedInputStream fis = (BufferedInputStream) getClass().getResourceAsStream(PROPERTIES_FILE);
			props.load(fis);  
			fis.close();
		}
		catch (IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return props;
	}

}
