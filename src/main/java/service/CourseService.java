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

import dao.CourseDao;
import entity.Course;
import entity.Response;
import utils.Util;


public class CourseService {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
    static EntityManager em = emf.createEntityManager();
    
    private static final Gson GSON = new GsonBuilder().create();


	public static void getAllCourses(HttpServletResponse resp) throws IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		CourseDao repository = new CourseDao(em);
		List<Course> coursesList = repository.findAll();
		String json = GSON.toJson(coursesList);
		resp.setStatus(200);
		resp.setHeader("Content-Type", "application/json");
		resp.getOutputStream().println(json);
	}
	
	public static void deleteCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException, NumberFormatException, ClassNotFoundException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		String id = "id";
		String paramValue = req.getParameter(id);
		CourseDao repository = new CourseDao(em);
		int queryResult = repository.deleteCourse(Long.parseLong(paramValue));
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
	
	public static void addCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4000");
		String json = Util.readInputStream(req.getInputStream());
		Course course = GSON.fromJson(json, Course.class);
		CourseDao repository = new CourseDao(em);
		int result = repository.addCourse(course);
		System.out.println(result);
		resp.setStatus(201);
		resp.setHeader("Content-Type", "application/json");
		resp.getOutputStream().println(GSON.toJson(course));

	}


}
