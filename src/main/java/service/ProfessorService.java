package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.ProfessorDao;
import entity.Course;
import entity.Professor;
import response.ProfessorNotDisponibilityResponse;
import response.ProfessorResponse;
import response.Response;
import response.ResponseMobileBooking;
import utils.Util;

public class ProfessorService {	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
	static EntityManager em = emf.createEntityManager();

	private static final Gson GSON = new GsonBuilder().create();


	public static void getAllProfessors(HttpServletRequest req, HttpServletResponse resp) throws IOException {
  		HttpSession session = req.getSession();
		resp.addHeader("Access-Control-Allow-Origin", "*");
		ProfessorDao repository = new ProfessorDao(em);
		List<Professor> professorsList = repository.findAllProfessor();
		String json = GSON.toJson(professorsList);
		resp.setStatus(200);
		resp.setHeader("Content-Type", "application/json");
		resp.getOutputStream().println(json);
	}

	public static void deleteProfessor(HttpServletRequest req, HttpServletResponse resp) throws IOException, NumberFormatException, ClassNotFoundException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		String id = "id";
		String paramValue = req.getParameter(id);
		ProfessorDao repository = new ProfessorDao(em);
		int queryResult = repository.deleteProfessor(Long.parseLong(paramValue));

		if(queryResult==1) {
			Util.setResponse(resp, "Ok", "No error code", "Entity deleted correctly");
		}  else {
			Util.setResponse(resp, "Error", "Entity not deleted correctly", "Error");
		}
	}

	public static void getProfessorByCourses(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		String id = "id";
		String paramValueCourse = req.getParameter(id);

		String day = "day";
		String paramValueDay = req.getParameter(day);

		if(paramValueCourse!=null && paramValueCourse != "") {
			ProfessorDao repository = new ProfessorDao(em);
			List<Professor> professorByCourse = repository.professorsByCourse(Long.parseLong(paramValueCourse));

			List<ProfessorResponse> professorData = new ArrayList<>();

			List<String> notDisponibility = new ArrayList<>();

			for(Professor p: professorByCourse) {
				notDisponibility.clear();
				ProfessorResponse data = new ProfessorResponse();
				List<ProfessorNotDisponibilityResponse> professorNotDisponibilityResponse = repository.getNotDisponibility(p.getId(), paramValueDay);

				for(ProfessorNotDisponibilityResponse d : professorNotDisponibilityResponse) {
					notDisponibility.add(d.getHourRepetition());
				}

				List<String> hours = new ArrayList<>();
				hours.add("15-16");
				hours.add("16-17");
				hours.add("17-18");
				hours.add("18-19");

				hours.removeAll(notDisponibility);

				data.setId(p.getId());
				data.setName(p.getName());
				data.setSurname(p.getSurname());
				data.setHours(hours);
				professorData.add(data);
			}

			String json = GSON.toJson(professorData);
			resp.setStatus(200);
			resp.setHeader("Content-Type", "application/json");
			resp.getOutputStream().println(json);
		}
	}


	public static void getProfessorByCoursesMobile(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		String id = "id";
		String paramValueCourse = req.getParameter(id);

		String day = "day";
		String paramValueDay = req.getParameter(day);


		if(paramValueCourse!=null && paramValueCourse != "") {

			ProfessorDao repository = new ProfessorDao(em);
			List<Professor> professorByCourse = repository.professorsByCourse(Long.parseLong(paramValueCourse));


			List<ProfessorResponse> professorData = new ArrayList<>();


			List<String> notDisponibility = new ArrayList<>();

			for(Professor p: professorByCourse) {
				notDisponibility.clear();
				ProfessorResponse data = new ProfessorResponse();
				List<ProfessorNotDisponibilityResponse> professorNotDisponibilityResponse = repository.getNotDisponibility(p.getId(), paramValueDay);

				for(ProfessorNotDisponibilityResponse d : professorNotDisponibilityResponse) {
					notDisponibility.add(d.getHourRepetition());
				}

				List<String> hours = new ArrayList<>();
				hours.add("15-16");
				hours.add("16-17");
				hours.add("17-18");
				hours.add("18-19");

				hours.removeAll(notDisponibility);

				data.setId(p.getId());
				data.setName(p.getName());
				data.setSurname(p.getSurname());
				data.setHours(hours);
				professorData.add(data);
			}

			List<ResponseMobileBooking> response = new ArrayList<>();
			for(ProfessorResponse p: professorData) {
				for(String h: p.getHours()) {
					ResponseMobileBooking obj = new ResponseMobileBooking();
					obj.setId(p.getId());
					obj.setNameProfessor(p.getName() + " " +p.getSurname());
					obj.setHour(h);
					response.add(obj);

				}

			}

			String json = GSON.toJson(response);
			resp.setStatus(200);
			resp.setHeader("Content-Type", "application/json");
			resp.getOutputStream().println(json);
		}
	}

	public static void getFilterPanelAdmin(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		String id = "professor";
		String paramValueProfessor = req.getParameter(id);
		ProfessorDao repository = new ProfessorDao(em);
		List<Course> courseList = repository.filterPanelAdmin(Long.parseLong(paramValueProfessor));
		String json = GSON.toJson(courseList);
		resp.setStatus(200);
		resp.setHeader("Content-Type", "application/json");
		resp.getOutputStream().println(json);
	}



	public static void addProfessor(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4000");
		HttpSession session = req.getSession(false);
		System.out.println(session);
		String json = Util.readInputStream(req.getInputStream());
		Professor professor = GSON.fromJson(json, Professor.class);

		ProfessorDao repository = new ProfessorDao(em);
		Professor professorExist = repository.professorByName(professor.getName(), professor.getSurname());
		if(professorExist.getName()!=null && professorExist.getSurname() != null) {
			if(professorExist.getName().toLowerCase().equalsIgnoreCase(professor.getName().toLowerCase()) && professorExist.getSurname().toLowerCase().equalsIgnoreCase(professor.getSurname().toLowerCase())) {
				return;
			}
		}
		int result = repository.addProfessor(professor);


		System.out.println(result);
		resp.setStatus(201);
		resp.setHeader("Content-Type", "application/json");
		resp.getOutputStream().println(GSON.toJson(professor));

	}



	public static void getPing(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Response response = new Response();
		resp.addHeader("Access-Control-Allow-Origin", "*");
		response.setResult(Boolean.TRUE.toString());
		response.setErrorCode("200 Ok");
		response.setDescription("Ping api result");
		String json = GSON.toJson(response);
		Cookie[] cookies = req.getCookies();
		resp.setStatus(200);
		resp.setHeader("Content-Type", "application/json");
		resp.getOutputStream().println(json);
	}


}