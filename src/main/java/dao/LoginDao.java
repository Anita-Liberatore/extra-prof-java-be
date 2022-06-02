package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;

import entity.User;
import utils.DbUtils;

public class LoginDao {
	
	protected EntityManager em;

	public LoginDao(EntityManager em) {
		this.em = em;
	}
	
	public User userByEmail(String username) throws ClassNotFoundException {
		User user = new User();
		Connection con=null;
		try {
			con=DbUtils.connectDB();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Statement stmt;
		try {

			stmt = con.createStatement();              

			ResultSet rs = stmt.executeQuery("select username, password_user, role_user, name_user, surname_user from user_login where username = '"+username +"'");

			while (rs.next()) {
				
				String usernameValue = rs.getString(1);
				String password = rs.getString(2);
				String role = rs.getString(3);
				String name = rs.getString(4);
				String surname = rs.getString(5);

				user.setUsername(usernameValue);
				user.setPassword(password);
				user.setRole(role);
				user.setName(name);
				user.setSurname(surname);
				
			}

			stmt.close();
			con.close();

			return user;

		} catch(SQLException ex) {
			System.err.print("SQLException: ");
			System.err.println(ex.getMessage());
		}
		return new User();  
	}

}
