package service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.RepetitionDao;
import entity.Repetition;

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
}
