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
    name = "KrishServlet",
    urlPatterns = {"/krish"}
)
public class KrishServlet extends HttpServlet {

	private static Connection conn;
	private static Statement statement;
	private static ResultSet resultSet;
	private static ArrayList<TableObject>tableList = new ArrayList<TableObject>();
	
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {

	try {  
		HttpSession session = request.getSession(); 
		tableList.clear();
		conn = DataAccess.GetCloudConnection();	
		statement = conn.createStatement();
		resultSet = statement.executeQuery("SELECT ipath,iname,ilabel,iscore,modified,imgsize,labels FROM images order by 1,2,4 desc");
		while (resultSet.next()) {
			TableObject tObj = new TableObject();
			tObj.setImgpath(resultSet.getString("ipath"));
			tObj.setImgname(resultSet.getString("iname"));
			tObj.setLabel(resultSet.getString("ilabel"));
			tObj.setScore(resultSet.getFloat("iscore"));
			tObj.setImgmodified(resultSet.getString("modified"));
			tObj.setImgsize(resultSet.getLong("imgSize"));
			tObj.setLabelcount(resultSet.getInt("labels"));
			tableList.add(tObj);
		}
		request.setAttribute("cloudTableList", tableList);
		System.out.println("cloudTableList has " + tableList.size() + " elements");
		getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
		conn.close();
		statement.close();
		resultSet.close();
		
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
	finally {}
  }
}