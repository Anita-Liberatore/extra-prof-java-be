package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {

	public static final String DB_URL = "jdbc:postgresql://localhost:5432/TRAINING?useSSL=false&serverTimeZone=UTC";
	public static final String USER = "postgres";
	public static final String PASS = "root";
	public static final String DRIVER = "org.postgresql.Driver";
	
	public static Connection connectDB() throws ClassNotFoundException {
		try {
			Connection con = null;

			con = DriverManager.getConnection(
					DbUtils.DB_URL,
					DbUtils.USER, DbUtils.PASS);

			return con;
		} catch (SQLException e) {

			System.out.println(e);
		}
		return null;
	}
}
