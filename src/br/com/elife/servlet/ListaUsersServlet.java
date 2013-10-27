package br.com.elife.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.elife.jdbc.dao.UserDao;
import br.com.elife.jdbc.model.User;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

@WebServlet("/listaUsers")
public class ListaUsersServlet extends HttpServlet {
	
	private static final long serialVersionUID = -1638694086640232072L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	               throws ServletException, IOException {
		
		UserDao dao = new UserDao();
	    String sEcho = request.getParameter("sEcho");
	    int iTotalRecords; 
	    int iTotalDisplayRecords;
	    JsonArray data = new JsonArray();
	    DateFormat formata_data = new SimpleDateFormat("dd/MM/yyyy");

	    iTotalRecords = dao.listaUsers().size();
	    List<User> users = new LinkedList<User>();
	    
	    //busca
	    for(User u : dao.listaUsers()){
	        if(u.getNome().toLowerCase().contains(request.getParameter("sSearch").toLowerCase())
	                    ||
	            u.getEndereco().toLowerCase().contains(request.getParameter("sSearch").toLowerCase())
	                    ||
	            u.getPais().toLowerCase().contains(request.getParameter("sSearch").toLowerCase())
	        			||
	            u.getEmail().toLowerCase().contains(request.getParameter("sSearch").toLowerCase()))
	        {
	            users.add(u);
	        }
	    }
	    iTotalDisplayRecords = users.size();
	    
	    
	    //ordenacao
	    final int sortColumnIndex = Integer.parseInt( request.getParameter("iSortingCols") );
	    final int sortDirection = request.getParameter("sSortDir_0").equals("asc") ? -1 : 1;

	    Collections.sort(users, new Comparator<User>(){
	        @Override
	        public int compare(User u1, User u2) {    
	            switch(sortColumnIndex){
	            case 0:
	            	return 0;
	            case 1:
	            	System.out.println("nome");
	                return u1.getNome().compareTo(u2.getNome()) * sortDirection;
	            case 2:
	            	System.out.println("ENDERECO");
	                return u1.getEndereco().compareTo(u2.getEndereco()) * sortDirection;
	            case 3:
	            	System.out.println("pais");
	                return u1.getPais().compareTo(u2.getPais()) * sortDirection;
	            case 4:
	            	System.out.println("data");
	            	return u1.getDataNascimento().compareTo(u2.getDataNascimento()) * sortDirection;
	            case 5:
	            	System.out.println("email");
	            	return u1.getEmail().compareTo(u2.getEmail()) * sortDirection;
	            }
	            return 0;
	        }
	    });
	    
	    try {
			JsonObject jsonResponse = new JsonObject();			
			jsonResponse.addProperty("sEcho", sEcho);
			jsonResponse.addProperty("iTotalRecords", iTotalRecords);
			jsonResponse.addProperty("iTotalDisplayRecords", iTotalDisplayRecords);
			
		        
			for(User u : users){
				JsonArray row = new JsonArray();
				row.add(new JsonPrimitive(u.getId()));
				row.add(new JsonPrimitive(u.getNome()));
				row.add(new JsonPrimitive(u.getEndereco()));
				row.add(new JsonPrimitive(u.getPais()));
				row.add(new JsonPrimitive(formata_data.format(u.getDataNascimento().getTime())));
				row.add(new JsonPrimitive(u.getEmail()));
				data.add(row);
			}
			jsonResponse.add("aaData", data);
			
			response.setContentType("application/Json");
			response.getWriter().print(jsonResponse.toString());
			
		} catch (JsonIOException e) {
			e.printStackTrace();
			response.setContentType("text/html");
			response.getWriter().print(e.getMessage());
		}
	}
	


}
