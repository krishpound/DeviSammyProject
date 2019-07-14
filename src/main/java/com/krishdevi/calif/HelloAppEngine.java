package com.krishdevi.calif;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {

	private static Connection conn;
	private static Statement statement;
	private static ResultSet resultSet;
	private static ArrayList<String>tableList = new ArrayList<String>();
	
	
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {

	try {   
		HttpSession session = request.getSession(); 
		tableList.clear();
		conn = DataAccess.GetLocalConnection();	
		statement = conn.createStatement();
		resultSet = statement.executeQuery("SELECT ipath,iname,ilabel,iscore FROM images order by 1,2,4 desc");
		while (resultSet.next()) {tableList.add("Model: "+resultSet.getString("model") + "\t\tPrice: $" + resultSet.getString("price")+"\n");}
		request.setAttribute("localTableList", tableList);
		System.out.println("localTableList has " + tableList.size() + " elements");
		getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
		
	}
	catch (ServletException se) {
		se.printStackTrace();
	}
	catch(SQLException sqle) {
		sqle.printStackTrace();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			conn.close();
			statement.close();
			resultSet.close();
		}
		catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		
	}
  }
}