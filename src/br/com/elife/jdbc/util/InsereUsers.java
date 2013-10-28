package br.com.elife.jdbc.util;

import java.util.Calendar;
import java.util.Random;

import br.com.elife.jdbc.dao.UserDao;
import br.com.elife.jdbc.model.User;

public class InsereUsers {
	
	public static void main(String[] args) {
		 User user = new User();
		 UserDao dao = new UserDao();
		 Random rd = new Random();
		 
		 String[] nomes = {"Maria", "Joao", "Jose", "Ana"};
		 String[] enderecos = {"R. Santo Antonio", "R. Joao Pessoa", "Floriano peixoto"};
		 
		 for (int i = 0; i < 10; i++) {
			 String nome = nomes[rd.nextInt(4)];
			 String endereco = enderecos[rd.nextInt(3)] + ", " +rd.nextInt(500);
			 user.setNome(nome);
			 user.setEmail(nome.toLowerCase() + "@mail.com");
			 user.setEndereco(endereco);
			 user.setPais("Brasil");
			 user.setDataNascimento(Calendar.getInstance());
			 dao.adiciona(user);
		 }
		 
		 
		 System.out.println("Gravado!");
	}

}
