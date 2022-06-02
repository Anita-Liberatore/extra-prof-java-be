package service;

import java.io.IOException;
import java.io.PrintWriter;

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

			if (jsessionID != null) {
				String json = GSON.toJson(user);
				resp.setStatus(200);
				resp.setHeader("Content-Type", "application/json");
				resp.getOutputStream().println(json);
			} 
		}

	}
}
