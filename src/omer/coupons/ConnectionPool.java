package omer.coupons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ConnectionPool {
	private static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\USER\\Documents\\dbpractice\\coupons.db";
	
	Set<Connection> connections;
	
	public ConnectionPool(int size) throws SQLException {
		connections = new HashSet<>();
		for (int i=0; i < size; i++) {
			Connection c = DriverManager
					.getConnection(CONNECTION_STRING);
			connections.add(c);
		}
	}

	public synchronized Connection getConnection() throws InterruptedException {
		while (connections.isEmpty())
			wait();
		Iterator<Connection> i = connections.iterator();
		Connection c = i.next();
		i.remove();
		return c;
	}

	public synchronized void returnConnection(Connection c) {
		connections.add(c);
		notify();
	}

	public void closeAllConnection() throws SQLException {
		for(Connection c : connections) {
			c.close();
		}
	}

}
