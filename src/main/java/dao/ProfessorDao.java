package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Professor;
import response.ProfessorNotDisponibilityResponse;
import utils.DbUtils;

public class ProfessorDao {
	
	protected EntityManager em;

	public ProfessorDao(EntityManager em) {
		this.em = em;
	}
	
	static final String DELETE = "delete from professor where id_professor= ";
	static final String FIND_ALL = "SELECT p FROM Professor p";
    static final String FIND_PROFESSOR_BY_COURSE = "SELECT p.id_professor, p.name_professor, p.surname "
    		+ "    		FROM associazione_corso_docente a "
    		+ "    		JOIN professor p on a.id_docente = p.id_professor "
    		+ "    		JOIN course c on a.id_corso = c.id_course where id_corso = ";

    public List<ProfessorNotDisponibilityResponse> getNotDisponibility(Long id, String day) {
    	List<ProfessorNotDisponibilityResponse> notDisponibility = new ArrayList<>();
    	Connection con=null;
    	try {
    		con=DbUtils.connectDB();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}


    	Statement stmt;
    	try {

    		stmt = con.createStatement();              

    		ResultSet rs = stmt.executeQuery("select r.id_professor, r.day_repetitions, r.hour_repetition from repetitions r join professor p on r.id_professor = p.id_professor where r.id_professor = " +id +" and r.day_repetitions = '"+day +"' and status = 'P'");

    		while (rs.next()) {
    			ProfessorNotDisponibilityResponse professorNotDisponibilityResponse = new ProfessorNotDisponibilityResponse();
    			Long idProfessor = rs.getLong(1);
    			String dayRepetition = rs.getString(2);
    			String hour = rs.getString(3);

    			professorNotDisponibilityResponse.setId(idProfessor);
    			professorNotDisponibilityResponse.setDayRepetition(dayRepetition);
    			professorNotDisponibilityResponse.setHourRepetition(hour);
    			notDisponibility.add(professorNotDisponibilityResponse);
    		}

    		stmt.close();
    		con.close();

    		return notDisponibility;

    	} catch(SQLException ex) {
    		System.err.print("SQLException: ");
    		System.err.println(ex.getMessage());
    	}
    	return new ArrayList<>();  
    }


    
	public int deleteProfessor(Long id) throws ClassNotFoundException {
		Connection con=null;
		PreparedStatement p=null;
		con=DbUtils.connectDB();

		try{
			String sql=DELETE+id;
			p = con.prepareStatement(sql);
			p.execute();
			return 1;
		}catch(SQLException  e){
			System.out.println(e);

		}
		return -1;
	}
	
	public List<Professor> professorsByCourse(Long id) throws ClassNotFoundException {
		List<Professor>  list = new ArrayList<>();
		Connection con=null;
		try {
			con=DbUtils.connectDB();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		Statement stmt;
		try {

			stmt = con.createStatement();              

			ResultSet rs = stmt.executeQuery(FIND_PROFESSOR_BY_COURSE+id);

			while (rs.next()) {
				Professor professor = new Professor();
				Long idProfessor = rs.getLong(1);
				String name = rs.getString(2);
				String surname = rs.getString(3);

				professor.setId(idProfessor);
				professor.setName(name);
				professor.setSurname(surname);
				list.add(professor);
			}

			stmt.close();
			con.close();

			return list;

		} catch(SQLException ex) {
			System.err.print("SQLException: ");
			System.err.println(ex.getMessage());
		}
		return new ArrayList<>();  
	}

	
	public int addProfessor(Professor professor) throws ClassNotFoundException, SQLException {
		Connection con = null;
		con=DbUtils.connectDB();
		
		try {
			PreparedStatement prep = con.prepareStatement ("INSERT INTO professor (NAME_PROFESSOR, SURNAME) VALUES (?,?)");
            prep.setString (1, professor.getName());
            prep.setString (2, professor.getSurname());
            prep.executeUpdate ();
	        return 1;
		} catch(SQLException  e){
			System.out.println(e);

		}
		return -1;
	}
	

	@SuppressWarnings("unchecked")
	public List<Professor> findAll() {
		Query query = em.createQuery(FIND_ALL);
		return query.getResultList();

	}

}
