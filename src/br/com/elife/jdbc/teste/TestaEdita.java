package br.com.elife.jdbc.teste;

import java.util.Calendar;

import br.com.elife.jdbc.dao.UserDao;
import br.com.elife.jdbc.model.User;

public class TestaEdita {
	
	public static void main(String[] args) {
		 User user = new User();
		 user.setId(1L);
		 user.setNome("Maria");
		 user.setEmail("maria@mail.com");
		 user.setEndereco("R. Santo Antonio, 200");
		 user.setPais("Brasil");
		 user.setDataNascimento(Calendar.getInstance());
		 
		 UserDao dao = new UserDao();
		 
		 dao.edita(user);
		 
		 System.out.println("Gravado!");
	}

}
