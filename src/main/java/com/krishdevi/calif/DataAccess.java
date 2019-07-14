package com.krishdevi.calif;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class DataAccess { 
	
	static Connection conn;
	static String url;
	
	public static Connection GetLocalConnection() {
		try {conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Chr1$T00l$!");} catch (SQLException e) {e.printStackTrace();}
        return conn;
    }
	public static Connection GetCloudConnection() {
		try {
			url = "jdbc:postgresql://google/fashion?cloudSqlInstance=fashiontrack:us-east4:fashiontrack-postgres-db&socketFactory=com.google.cloud.sql.postgres.SocketFactory&user=krishpound&password=DHARSHINI";
			System.out.println("trying to connect to: " + url);
			conn = DriverManager.getConnection(url);
			System.out.println("Cloud connection created :D");
		}
		catch (SQLException e) {
			System.out.println("SQLException when trying to connect to Google Cloud");
			e.printStackTrace();
		}
		return conn;
     }
}
