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

import dao.CourseDao;
import entity.Course;

/**
 * Servlet implementation class HelloController
 */
@WebServlet("/v1")
public class CourseController extends HttpServlet {

	private static final long serialVersionUID = -3659385933125554656L;
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
    static EntityManager em = emf.createEntityManager();
    
    private static final Gson GSON = new GsonBuilder().create();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String action = req.getServletPath();

		switch (action) {
		case "/courses":
			getAllCourses(resp);
			break;
		case "/delete-professor":
			
			break;
		default:
			break;
		}	
	}


	public static void getAllCourses(HttpServletResponse resp) throws IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		CourseDao repository = new CourseDao(em);
		List<Course> coursesList = repository.findAll();
		String json = GSON.toJson(coursesList);
		resp.setStatus(200);
		resp.setHeader("Content-Type", "application/json");
		resp.getOutputStream().println(json);
	}

}
