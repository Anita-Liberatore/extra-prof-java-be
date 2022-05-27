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
import utils.Util;

public class RepetitionService {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
    static EntityManager em = emf.createEntityManager();

	private static final Gson GSON = new GsonBuilder().create();


	public static void getAllRepetitions(HttpServletResponse resp) throws IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		RepetitionDao repository = new RepetitionDao(em);
		List<Repetition> repetition = repository.findAll();
		String json = GSON.toJson(repetition);
		resp.setStatus(200);
		resp.setHeader("Content-Type", "application/json");
		resp.getOutputStream().println(json);
	}
	
	public static void getAllRepetitionsForAdmin(HttpServletResponse resp) throws IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		RepetitionDao repository = new RepetitionDao(em);
		List<Repetition> repetition = repository.findAll();
		String json = GSON.toJson(repetition);
		resp.setStatus(200);
		resp.setHeader("Content-Type", "application/json");
		resp.getOutputStream().println(json);
	}
	
	public static void addRepetitions(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4000");
		String json = Util.readInputStream(req.getInputStream());
		Repetition repetition = GSON.fromJson(json, Repetition.class);
		RepetitionDao repository = new RepetitionDao(em);
		int result = repository.addRepetition(repetition);
		System.out.println(result);
		resp.setStatus(201);
		resp.setHeader("Content-Type", "application/json");
		resp.getOutputStream().println(GSON.toJson(repetition));

	}
	
	public static void updateRepetitions(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4000");
		
		String id = "id";
		String paramValue = req.getParameter(id);
		String json = Util.readInputStream(req.getInputStream());
		Repetition repetition = GSON.fromJson(json,Repetition.class);
		RepetitionDao repository = new RepetitionDao(em);
		int result = repository.updateRepetition(Long.parseLong(paramValue), repetition.getStatus());
		System.out.println(result);
		resp.setStatus(204);
	

	}
	
	public static void updateRepetitionsDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4000");
		
		String id = "id";
		String paramValue = req.getParameter(id);
		String json = Util.readInputStream(req.getInputStream());
		Repetition repetition = GSON.fromJson(json,Repetition.class);
		RepetitionDao repository = new RepetitionDao(em);
		int result = repository.updateRepetitionDelete(Long.parseLong(paramValue), repetition.getStatus());
		System.out.println(result);
		resp.setStatus(204);
	

	}
}
