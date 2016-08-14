package omer.coupons.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	private static final String SELECT_STATEMENT = "SELECT COMP_NAME FROM Company";
	private static final String INSERT_STATEMENT = "INSERT INTO Company VALUES (4, 'apple','32422','apple@gmail.com')";
	private static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\USER\\Documents\\dbpractice\\coupons.db";

	// Play with the code
	// Advance Selects: More columns, WHERE
	// Try update and delete statements
	public static void main(String[] args) {
		try {
			Connection connection = DriverManager
					.getConnection(CONNECTION_STRING);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_STATEMENT);
			while (resultSet.next()) {
				System.out.println(resultSet.getString("COMP_NAME"));
			}
			int i = statement.executeUpdate(INSERT_STATEMENT);
			System.err.println(i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
