package com.Database;

import java.sql.*;

import javax.swing.JOptionPane;

public class SqliteConection {
	Connection conn = null;

	public static Connection dbConector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:snakeUsers.sqlite");
			//JOptionPane.showMessageDialog(null, "Exito");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}

	}

}
