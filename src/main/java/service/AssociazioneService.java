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

import dao.AssociazioneDao;
import dao.RepetitionDao;
import entity.Associazione;
import entity.Repetition;
import request.AssociazioniRequest;
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

	public static void deleteAssociazione(HttpServletRequest req, HttpServletResponse resp) throws IOException, NumberFormatException, ClassNotFoundException, SQLException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		String id = "id";
		String paramValue = req.getParameter(id);
		AssociazioneDao repository = new AssociazioneDao(em);
		RepetitionDao repositoryDao = new RepetitionDao(em);
		AssociazioniRequest associazione = new AssociazioniRequest();
		associazione = repository.findById(Long.parseLong(paramValue));
		

		List<Repetition> listRepetitionForDelete = repositoryDao.findAll();

		for (Repetition r: listRepetitionForDelete) {
			if(r.getStatus().equalsIgnoreCase("P")) {
				if(r.getIdCourse() == associazione.getIdCourse() && r.getIdProfessor() == associazione.getIdProfessor()) {
					repository.updateRepetitionFromAssociationAction(associazione.getIdProfessor(), associazione.getIdCourse(), r.getId());
				}
			}
		}

		int queryResult = repository.deleteAssociazione(Long.parseLong(paramValue));
		if(queryResult==1) {
			Util.setResponse(resp, "Ok", "No error code", "Course-professor deleted correctly");
		}  else {
			Util.setResponse(resp, "Error", "Course-professor not deleted correctly", "Error");
		}

	}
	
	public static void addAssociazione(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4000");
		String json = Util.readInputStream(req.getInputStream());
		AssociazioniRequest associazioniRequest = GSON.fromJson(json, AssociazioniRequest.class);
		AssociazioneDao repository = new AssociazioneDao(em);
		associazioniRequest = repository.addAssociazione(associazioniRequest);
		resp.setStatus(201);
		resp.setHeader("Content-Type", "application/json");
		resp.getOutputStream().println(GSON.toJson(associazioniRequest));

	}
}
