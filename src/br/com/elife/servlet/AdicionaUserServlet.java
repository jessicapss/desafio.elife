package br.com.elife.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.elife.jdbc.dao.UserDao;
import br.com.elife.jdbc.model.User;

public class AdicionaUserServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        PrintWriter out = response.getWriter();
                        
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String pais = request.getParameter("pais");
        String dataEmTexto = request
                .getParameter("data_nascimento");
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
        
        //TODO
//        request.setAttribute("msg", "Gravado com sucesso!");  
//        request.getRequestDispatcher("index.jsp").forward(request, response); 
//        
//        out.println("<html>");
//        out.println("<body>");
//        out.println("Contato " + user.getNome() +
//                " adicionado com sucesso");
//        out.println("</body>");
//        out.println("</html>");

	}
	

}
