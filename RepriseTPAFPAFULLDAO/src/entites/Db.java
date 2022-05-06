package entites;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db {
	private static String dburl="jdbc:mysql://localhost:3306/bdtp?characterEncoding=utf8"; //wamp necessaire encodage
	private static String dbuser="root";
	private static String dbpass="";
	public static Connection connexion=null;
	
	public static void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connexion=DriverManager.getConnection(dburl,dbuser,dbpass);
			System.out.println("coOK"); //
		} catch (Exception ex) {
			System.out.println("Soucis de connexion");
        	ex.printStackTrace();
        }
	}
}
