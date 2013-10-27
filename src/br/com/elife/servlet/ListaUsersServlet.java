package br.com.elife.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	    
	    int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
	    int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
	    
	    if(users.size() < iDisplayStart + iDisplayLength)
			users = users.subList(iDisplayStart, users.size());
		else
			users = users.subList(iDisplayStart, iDisplayStart + iDisplayLength);
	    
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
