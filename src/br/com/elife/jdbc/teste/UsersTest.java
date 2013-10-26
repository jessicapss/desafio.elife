package br.com.elife.jdbc.teste;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.elife.jdbc.model.User;

public class UsersTest {

	
	public static List<User> getUsers() {
		
		List<User> users = new ArrayList<User>();
		
		for (int i=1; i<200; i++) {
			User user = new User();
			 user.setId((long) (i));
			 user.setNome("Maria");
			 user.setEmail("maria@mail.com");
			 user.setEndereco("R. Santo Antonio, 200");
			 user.setPais("Brasil");
			 user.setDataNascimento(Calendar.getInstance());
			 
			 users.add(user);
			
		}
		User user = new User();
		 user.setId((long) (201));
		 user.setNome("Joao");
		 user.setEmail("joao@mail.com");
		 user.setEndereco("R. Santo Antonio, 200");
		 user.setPais("Brasil");
		 user.setDataNascimento(Calendar.getInstance());
		 users.add(user);
		
		return users;
	}
}
