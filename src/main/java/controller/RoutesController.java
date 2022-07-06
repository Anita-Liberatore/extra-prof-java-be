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
import service.LoginService;
import service.ProfessorService;
import service.RepetitionService;

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
		case "/login":
			try {
				LoginService.getLogin(req, resp);
			} catch (NumberFormatException | ClassNotFoundException | IOException e2) {
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
		case "/associazioni":
			try {
				AssociazioneService.addAssociazione(req, resp);
			} catch (ClassNotFoundException | IOException | SQLException e2) {
				e2.printStackTrace();
			}
			break;
		case "/add-repetitions":
			try {
				RepetitionService.addRepetitions(req, resp);
			} catch (ClassNotFoundException | IOException | SQLException e2) {
				e2.printStackTrace();
			}
			break;
		case "/repetitions":
			try {
				RepetitionService.updateRepetitions(req, resp);
			} catch (ClassNotFoundException | IOException | SQLException e2) {
				e2.printStackTrace();
			}
			break;
		case "/repetitions-delete":
			try {
				RepetitionService.updateRepetitionsDelete(req, resp);
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
		case "/logout":
			LoginService.logout(req, resp);
			break;
		case "/professors":
			ProfessorService.getAllProfessors(req, resp);
			break;
		case "/professors-course-mobile":
			try {
				ProfessorService.getProfessorByCoursesMobile(req, resp);
			} catch (NumberFormatException | ClassNotFoundException | IOException e3) {
				e3.printStackTrace();
			}
			break;
		case "/admin-filter-panel":
			try {
				ProfessorService.getFilterPanelAdmin(req, resp);
			} catch (NumberFormatException | ClassNotFoundException | IOException e3) {
				e3.printStackTrace();
			}
			break;
		case "/associazioni":
			AssociazioneService.getAllAssociazioni(resp);
			break;
		case "/repetitions":
			RepetitionService.getAllRepetitions(req, resp);
			break;
		case "/repetitions-admin":
			RepetitionService.getAllRepetitionsForAdmin(req, resp);
			break;
		case "/courses":
			CourseService.getAllCourses(req,resp);
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
				try {
					AssociazioneService.deleteAssociazione(req, resp);
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
