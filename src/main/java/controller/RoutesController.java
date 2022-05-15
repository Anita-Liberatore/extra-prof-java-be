package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AssociazioneService;
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
		String action = req.getServletPath();
		
		switch (action) {
		case "/professors":
			try {
				ProfessorService.addProfessor(req, resp);
			} catch (ClassNotFoundException | IOException | SQLException e2) {
				e2.printStackTrace();
			}
			break;
		case "/courses":
			try {
				CourseService.addCourse(req, resp);
			} catch (ClassNotFoundException | IOException | SQLException e2) {
				e2.printStackTrace();
			}
			break;
		default:
			break;
		}	
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
		case "/associazioni":
			AssociazioneService.getAllAssociazioni(resp);
			break;
		case "/add-professor":
			try {
				ProfessorService.addProfessor(req, resp);
			} catch (ClassNotFoundException | IOException | SQLException e2) {
				e2.printStackTrace();
			}
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
		case "/delete-associazione":
			try {
				AssociazioneService.deleteAssociazione(req, resp);
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
