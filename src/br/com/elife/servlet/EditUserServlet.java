package br.com.elife.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.elife.jdbc.dao.UserDao;
import br.com.elife.jdbc.model.User;

//FIXME
@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {

	private static final long serialVersionUID = -5464061552108694570L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserDao dao = new UserDao();
		int id = Integer.parseInt(request.getParameter("id"));
	    int columnPosition = Integer.parseInt(request.getParameter("columnPosition"));
	    String value = request.getParameter("value");
    
	    for(User user: dao.listaUsers())
	    {
	        if(user.getId()==id)
	        {
	            switch (columnPosition)
	            {
	                case 0:
	                    user.setNome(value);
	                    break;
	                case 1:
	                    user.setEndereco(value);
	                    break;
	                case 2:
	                    user.setPais(value);
	                    break;
	                case 3:
	                	DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	                	Calendar data = Calendar.getInstance();
						try {
							data.setTime(format.parse(value));
						} catch (ParseException e) {
							response.getWriter().print("Erro na formatação da data! o formato correto é dd/MM/yyyy!");
							return;
						}
		                    user.setDataNascimento(data);
	                    break;
	                case 4:
	                    user.setEmail(value);
	                    break;
	                default:
	                    break;
	            }
	            
	            dao.edita(user);
	            response.getWriter().print(value);
	            return;
	        }
	    }
	    response.getWriter().print("Erro! Usuario nao encontrado!");
	}
	

}
