package service;

import java.io.IOException;

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
import request.Auth;
import response.Response;
import utils.Util;


public class LoginService {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
	static EntityManager em = emf.createEntityManager();

	private static final Gson GSON = new GsonBuilder().create();

	
	public static void getLogin(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		HttpSession session = req.getSession();
		
		String json = Util.readInputStream(req.getInputStream());
		Auth userAuth = GSON.fromJson(json, Auth.class);

		LoginDao repository = new LoginDao(em);
		User user = repository.userByEmail(userAuth.getUsername());
		if(user.getUsername()!=null) {
			session.setAttribute("username", userAuth.getUsername());
            session.setAttribute("role", user.getRole());
            req.setAttribute("role",user.getRole());
            
            
			if (session.getId()!= null) {
				user.setToken(session.getId());
				session.setAttribute("token", session.getId());
				String responseJson = GSON.toJson(user);
				resp.setStatus(200);
				resp.setHeader("Content-Type", "application/json");
				
				resp.getOutputStream().println(responseJson);
			} 
		} else {
			Response response = new Response();
			response.setDescription("Login non autorizzato");
			response.setErrorCode("503");
			String respError = GSON.toJson(response);
			resp.setStatus(503);
			resp.setHeader("Content-Type", "application/json");
			resp.getOutputStream().println(respError);
		}

	}
	

	public static void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		    resp.addHeader("Access-Control-Allow-Origin", "*");
		    if(Util.checkSession(req, resp)) {
		    	req.getSession().invalidate();
				Response response = new Response();
				response.setDescription("Logout avvenuto correttamente");
				response.setErrorCode("200");
				String json = GSON.toJson(response);
				resp.setStatus(200);
				resp.setHeader("Content-Type", "application/json");
				resp.getOutputStream().println(json);
		    } else {
		    	Response response = new Response();
				response.setDescription("Logout non avenuto correttamente");
				response.setErrorCode("500");
				String json = GSON.toJson(response);
				resp.setStatus(500);
				resp.setHeader("Content-Type", "application/json");
				resp.getOutputStream().println(json);
		    }
		} 
}
