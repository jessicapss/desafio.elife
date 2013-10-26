package br.com.elife.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.elife.jdbc.ConnectionFactory;
import br.com.elife.jdbc.model.User;

public class UserDao {
 
   private Connection connection;
 
   public UserDao() {
     this.connection = new ConnectionFactory().getConnection();
   }
 
   public void adiciona(User user) {
	     String sql = "insert into user " +
	             "(nome,endereco,data_nascimento,pais,email)" +
	             " values (?,?,?,?,?)";
	 
	     try {
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         stmt.setString(1,user.getNome());
	         stmt.setString(2,user.getEndereco());
	         stmt.setDate(3,new Date(user.getDataNascimento().getTimeInMillis()));
	         stmt.setString(4,user.getPais());
	         stmt.setString(5,user.getEmail());
	 
	         stmt.execute();
	         stmt.close();
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     } 
	 }
   
   public void edita(User user) {
	   String sql = "update user set nome=?, endereco=?, "
	   		+ "data_nascimento=?, pais=?, email=? where id=?";
	   
	   try {
		   PreparedStatement stmt = connection.prepareStatement(sql);
		   stmt.setString(1, user.getNome());
		   stmt.setString(2, user.getEndereco());
		   stmt.setDate(3, new Date(user.getDataNascimento().getTimeInMillis()));
		   stmt.setString(4,user.getPais());
	       stmt.setString(5,user.getEmail());
	       stmt.setLong(6, user.getId());
	 
	       stmt.execute();
	       stmt.close();
	   } catch (SQLException e) {
		   throw new RuntimeException(e);
	   }
	   
   }
   
   public void deleta(User user) {
	   String sql = "delete from user where id=?";
	   
	   try {
		   PreparedStatement stmt = connection.prepareStatement(sql);
	       stmt.setLong(1, user.getId());
	 
	       stmt.execute();
	       stmt.close();
	   } catch (SQLException e) {
		   throw new RuntimeException(e);
	   }
	   
   }
   
   public List<User> listaUsers() {
	     try {
	         List<User> users = new ArrayList<User>();
	         PreparedStatement stmt = this.connection.
	                 prepareStatement("select * from user");
	         ResultSet rs = stmt.executeQuery();
	 
	         while (rs.next()) {
	             User user = new User();
	             user.setId(rs.getLong("id"));
	             user.setNome(rs.getString("nome"));
	             user.setEndereco(rs.getString("endereco"));
	             user.setPais(rs.getString("pais"));
	             user.setEmail(rs.getString("email"));
	 
	             Calendar data = Calendar.getInstance();
	             data.setTime(rs.getDate("data_nascimento"));
	             user.setDataNascimento(data);
	 
	             users.add(user);
	         }
	         rs.close();
	         stmt.close();
	         return users;
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	 }
   
 }