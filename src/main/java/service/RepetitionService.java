package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.RepetitionDao;
import entity.Repetition;
import response.Response;
import utils.Util;

public class RepetitionService {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
	static EntityManager em = emf.createEntityManager();

	private static final Gson GSON = new GsonBuilder().create();


	public static void getAllRepetitions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4000");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		if(Util.checkSession(req, resp)) {
			String user = "user";
			String paramValue = req.getParameter(user);
			String role = "role";
			String paramRole = req.getParameter(role);
			req.setAttribute("roleSession", paramRole);
			RepetitionDao repository = new RepetitionDao(em);
			List<Repetition> repetition = repository.findAll(paramValue);
			String json = GSON.toJson(repetition);
			resp.setStatus(200);
			resp.setHeader("Content-Type", "application/json");
			resp.getOutputStream().println(json);

		} else {
			Response response = new Response();
			response.setDescription("Errore");
			response.setErrorCode("500");
			String json = GSON.toJson(response);
			resp.setStatus(500);
			resp.setHeader("Content-Type", "application/json");
			resp.getOutputStream().println(json);
		}
	}

	public static void getAllRepetitionsForAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		if(Util.checkSession(req, resp)) {
			RepetitionDao repository = new RepetitionDao(em);
			List<Repetition> repetition = repository.findAll();
			String json = GSON.toJson(repetition);
			resp.setStatus(200);
			resp.setHeader("Content-Type", "application/json");
			resp.getOutputStream().println(json);
		} else {
			Response response = new Response();
			response.setDescription("Errore");
			response.setErrorCode("500");
			String json = GSON.toJson(response);
			resp.setStatus(500);
			resp.setHeader("Content-Type", "application/json");
			resp.getOutputStream().println(json);
		}
	}

	public static void addRepetitions(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4000");
		if(Util.checkSession(req, resp)) {
			String roleSession = (String) req.getAttribute("roleSession");
			String json = Util.readInputStream(req.getInputStream());
			Repetition repetition = GSON.fromJson(json, Repetition.class);
			RepetitionDao repository = new RepetitionDao(em);
			int result = repository.addRepetition(repetition);
			System.out.println(result);
			resp.setStatus(201);
			resp.setHeader("Content-Type", "application/json");
			resp.getOutputStream().println(GSON.toJson(repetition));
		} else {
			Response response = new Response();
			response.setDescription("Ripetizione non aggiunta a causa di un errore");
			response.setErrorCode("500");
			String json = GSON.toJson(response);
			resp.setStatus(500);
			resp.setHeader("Content-Type", "application/json");
			resp.getOutputStream().println(json);
		}
	}

	public static void updateRepetitions(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4000");
		if(Util.checkSession(req, resp)) {
			String id = "id";
			String paramValue = req.getParameter(id);
			String role = "role";
			String paramRole = req.getParameter(role);
			req.setAttribute("roleSession", paramRole);
			String roleSession = (String) req.getAttribute("roleSession");
			if(roleSession!=null && roleSession.equalsIgnoreCase("A") || roleSession.equalsIgnoreCase("C")) {
				String json = Util.readInputStream(req.getInputStream());
				Repetition repetition = GSON.fromJson(json,Repetition.class);
				RepetitionDao repository = new RepetitionDao(em);
				int result = repository.updateRepetition(Long.parseLong(paramValue), repetition.getStatus());
				System.out.println(result);
				resp.setStatus(204);
			}
		} else {
			Response response = new Response();
			response.setDescription("Ripetizione non modificata a causa di un errore");
			response.setErrorCode("500");
			String json = GSON.toJson(response);
			resp.setStatus(500);
			resp.setHeader("Content-Type", "application/json");
			resp.getOutputStream().println(json);
		}
	}

	public static void updateRepetitionsDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4000");
		if(Util.checkSession(req, resp)) {
			String roleSession = (String) req.getAttribute("roleSession");
			String id = "id";
			String paramValue = req.getParameter(id);
			String json = Util.readInputStream(req.getInputStream());
			Repetition repetition = GSON.fromJson(json,Repetition.class);
			RepetitionDao repository = new RepetitionDao(em);
			int result = repository.updateRepetitionDelete(Long.parseLong(paramValue), repetition.getStatus());
			System.out.println(result);
			resp.setStatus(204);
		}  else {
			Response response = new Response();
			response.setDescription("Ripetizione non modificata a causa di un errore");
			response.setErrorCode("500");
			String json = GSON.toJson(response);
			resp.setStatus(500);
			resp.setHeader("Content-Type", "application/json");
			resp.getOutputStream().println(json);
		}

	}
}
