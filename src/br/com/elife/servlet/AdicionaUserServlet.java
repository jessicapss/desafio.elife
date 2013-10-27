package br.com.elife.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.elife.jdbc.dao.UserDao;
import br.com.elife.jdbc.model.User;

@WebServlet("/addUser")
public class AdicionaUserServlet extends HttpServlet {

	private static final long serialVersionUID = 3772892623649126079L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        PrintWriter out = response.getWriter();
                        
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String pais = request.getParameter("pais");
        String dataEmTexto = request
                .getParameter("dataNascimento");
        Calendar dataNascimento = null;
        

        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
            dataNascimento = Calendar.getInstance();
            dataNascimento.setTime(date);
        } catch (ParseException e) {
        	
        	//TODO
            out.println("Erro de convers‹o da data");
            return;
        }
        
   
        User user = new User();
        user.setNome(nome);
        user.setEndereco(endereco);
        user.setPais(pais);
        user.setEmail(email);
        user.setDataNascimento(dataNascimento);
        
        UserDao dao = new UserDao();
        dao.adiciona(user);

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	

}
