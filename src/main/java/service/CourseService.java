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
import utils.Util;


public class CourseService {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
    static EntityManager em = emf.createEntityManager();
    
    private static final Gson GSON = new GsonBuilder().create();


    public static void getAllCourses(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	resp.addHeader("Access-Control-Allow-Origin", "*");
    	if(Util.checkSession(req, resp)) {
    		CourseDao repository = new CourseDao(em);
    		List<Course> coursesList = repository.findAllCourses();
    		String json = GSON.toJson(coursesList);
    		resp.setStatus(200);
    		resp.setHeader("Content-Type", "application/json");
    		resp.getOutputStream().println(json);
    	} else {
			Util.setResponse(resp, "Error", "Session error", "Error");
    	}
    }
	
    public static void deleteCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException, NumberFormatException, ClassNotFoundException {
    	resp.addHeader("Access-Control-Allow-Origin", "*");
    	resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4000");
    	if(Util.checkSession(req, resp)) {
    		String id = "id";
    		String paramValue = req.getParameter(id);
    		CourseDao repository = new CourseDao(em);
    		int queryResult = repository.deleteCourse(Long.parseLong(paramValue));

    		if(queryResult==1) {
    			Util.setResponse(resp, "Ok", "No error code", "Entity deleted correctly");
    		}  else {
    			Util.setResponse(resp, "Error", "Entity not deleted correctly", "Error");
    		}
    	} else {
			Util.setResponse(resp, "Error", "Session error", "Error");

    	}
    }

    public static void addCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException {
    	resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4000");
    	if(Util.checkSession(req, resp)) {

    		String json = Util.readInputStream(req.getInputStream());
    		Course course = GSON.fromJson(json, Course.class);
    		CourseDao repository = new CourseDao(em);
    		Course courseExist = repository.courseByName(course.getCourseName());

    		if(courseExist.getCourseName()!=null) {
    			if(courseExist.getCourseName().toLowerCase().equalsIgnoreCase(course.getCourseName().toLowerCase())) {
    				return;
    			}
    		}

    		int result = repository.addCourse(course);
    		if(result==1) {
    			Util.setResponse(resp, "Ok", "No error code", "Entity add correctly");

    		} else {
    			Util.setResponse(resp, "ERROR", "error code", "Entity NOT add correctly");

    		}
    	}else {
			Util.setResponse(resp, "ERROR", "error session", "Entity NOT add correctly");

    	}

    }



}
