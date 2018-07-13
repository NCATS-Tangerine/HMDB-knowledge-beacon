package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Connection to an SQLite database
 */
public class DBconnection {

	/** Create connection to an SQLite database
	 * @return the connection
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	static Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("org.sqlite.JDBC");
		return DriverManager.getConnection("jdbc:sqlite:HMDB-KS.db");
	}
	
	/** Create an SQL IN clause from a string list
	 * @param list
	 * @return
	 */
	static String inS(List<String> list){
		StringBuffer in = new StringBuffer(" IN (");
		boolean first = true;
		for (String rel: list){
			if (!first) in.append(",");
			in.append("'"+rel+"'");
			first = false;
		}
		in.append(")");
		return in.toString();
	}
	
	/** Create an SQL IN clause from a int list
	 * @param list
	 * @return
	 */
	static String inI(List<Integer> list){
		StringBuffer in = new StringBuffer(" IN (");
		boolean first = true;
		for (int rel: list){
			if (!first) in.append(",");
			in.append(rel);
			first = false;
		}
		in.append(")");
		return in.toString();
	}
}
