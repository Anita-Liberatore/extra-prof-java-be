package service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.AssociazioneDao;
import dao.ProfessorDao;
import entity.Associazione;
import utils.Util;

public class AssociazioneService {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
	static EntityManager em = emf.createEntityManager();

	private static final Gson GSON = new GsonBuilder().create();


	public static void getAllAssociazioni(HttpServletResponse resp) throws IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		AssociazioneDao repository = new AssociazioneDao(em);
		List<Associazione> associazioneList = repository.findAll();
		String json = GSON.toJson(associazioneList);
		resp.setStatus(200);
		resp.setHeader("Content-Type", "application/json");
		resp.getOutputStream().println(json);
	}

	public static void deleteAssociazione(HttpServletRequest req, HttpServletResponse resp) throws IOException, NumberFormatException, ClassNotFoundException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		String id = "id";
		String paramValue = req.getParameter(id);
		AssociazioneDao repository = new AssociazioneDao(em);
		int queryResult = repository.deleteAssociazione(Long.parseLong(paramValue));
		
		if(queryResult==1) {
			Util.setResponse(resp, "Ok", "No error code", "Course-professor deleted correctly");
		}  else {
			Util.setResponse(resp, "Error", "Course-professor not deleted correctly", "Error");
		}
	}
}
