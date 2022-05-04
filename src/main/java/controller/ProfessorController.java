package controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.ProfessorDao;
import entity.Professor;
import entity.Response;

/**
 * Servlet implementation class HelloController
 */
@WebServlet("/")
public class ProfessorController extends HttpServlet {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
    EntityManager em = emf.createEntityManager();

	private static final long serialVersionUID = -7036938676449415736L;

	private static final Gson GSON = new GsonBuilder().create();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String action = req.getServletPath();

		switch (action) {
		case "/ping":
			getPing(resp);
			break;
		case "/professors":
			getAllProfessors(resp);
			break;
		case "/delete-professor":
			try {
				deleteProfessor(req,resp);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}	
	}


	private void getAllProfessors(HttpServletResponse resp) throws IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		ProfessorDao repository = new ProfessorDao(em);
		List<Professor> professorsList = repository.findAll();
		String json = GSON.toJson(professorsList);
		resp.setStatus(200);
		resp.setHeader("Content-Type", "application/json");
		resp.getOutputStream().println(json);
	}

	private void deleteProfessor(HttpServletRequest req, HttpServletResponse resp) throws IOException, NumberFormatException, ClassNotFoundException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		String id = "id";
		String paramValue = req.getParameter(id);
		ProfessorDao repository = new ProfessorDao(em);
		int queryResult = repository.deleteProfessor(Long.parseLong(paramValue));
		Response response = new Response();
		if(queryResult==1) {
			response.setResult("Ok");
			response.setErrorCode("No error code");
			response.setDescription("Entity deleted correctly");
			String json = GSON.toJson(response);
			resp.setStatus(200);
			resp.setHeader("Content-Type", "application/json");
			resp.getOutputStream().println(json);
		}  else {
			response.setResult("Error");
			response.setErrorCode("500");
			response.setDescription("Entity NOT deleted correctly, ERROR");
			String json = GSON.toJson(response);
			resp.setStatus(500);
			resp.setHeader("Content-Type", "application/json");
			resp.getOutputStream().println(json);
		}
	}


	private void getPing(HttpServletResponse resp) throws IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		String ping = "result: true";
		String json = GSON.toJson(ping);

		resp.setStatus(200);
		resp.setHeader("Content-Type", "application/json");
		resp.getOutputStream().println(json);
	}
}