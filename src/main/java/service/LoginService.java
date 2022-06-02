package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.LoginDao;
import entity.User;
import response.Response;

public class LoginService {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
	static EntityManager em = emf.createEntityManager();

	private static final Gson GSON = new GsonBuilder().create();
	

	public static void getUserByEmail(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		String username = "username";
		String paramUsername = req.getParameter(username);
		
		LoginDao repository = new LoginDao(em);
		User user = repository.userByEmail(paramUsername);
		if(user!=null) {
			HttpSession s = req.getSession();
			String jsessionID = s.getId(); // estraggo il session ID

			System.out.println("JSessionID:" + jsessionID);

			s.setAttribute("userName", paramUsername); // salvo dei dati in sessione...

			if (jsessionID!= null) {
				String json = GSON.toJson(user);
				resp.setStatus(200);
				resp.setHeader("Content-Type", "application/json");
				resp.getOutputStream().println(json);
			} 
		} else {
			Response response = new Response();
			response.setDescription("Login non autorizzato");
			String json = GSON.toJson(response);
			resp.setStatus(503);
			resp.setHeader("Content-Type", "application/json");
			resp.getOutputStream().println(json);
		}

	}
	
	public static void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		HttpSession session=req.getSession(false);  
		if(session!=null) {
			String jsessionID = session.getId(); // estraggo il session ID
			System.out.println("JSessionID:" + jsessionID);
			session.invalidate();  
			Response response = new Response();
			response.setDescription("Logout avvenuto correttamente");
			String json = GSON.toJson(response);
			resp.setStatus(200);
			resp.setHeader("Content-Type", "application/json");
			resp.getOutputStream().println(json);
		} else {
			Response response = new Response();
			response.setDescription("Logout NON avvenuto correttamente");
			String json = GSON.toJson(response);
			resp.setStatus(500);
			resp.setHeader("Content-Type", "application/json");
			resp.getOutputStream().println(json);
		}
	}
}
