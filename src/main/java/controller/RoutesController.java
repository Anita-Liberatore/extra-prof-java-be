package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CourseService;
import service.ProfessorService;

/**
 * Servlet implementation class RoutesController
 */
@WebServlet("/")
public class RoutesController extends HttpServlet {

	private static final long serialVersionUID = -8818717035593737509L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String action = req.getServletPath();

		switch (action) {
		case "/ping":
			ProfessorService.getPing(resp);
			break;
		case "/professors":
			ProfessorService.getAllProfessors(resp);
			break;
		case "/courses":
			CourseService.getAllCourses(resp);
			break;
		case "/delete-professor":
			try {
				ProfessorService.deleteProfessor(req,resp);
			} catch (NumberFormatException | ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
			break;
		case "/delete-course":
			try {
				CourseService.deleteCourse(req,resp);
			} catch (NumberFormatException | ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}	
	}
}
