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
    name = "SearchImages",
    urlPatterns = {"/searchimages"}
)
public class SearchImages extends HttpServlet {

	private static Connection conn;
	private static Statement statement;
	private static ResultSet resultSet;
	private static ArrayList<TableObject>tableList = new ArrayList<TableObject>();
	private static ArrayList<String>similarImageList = new ArrayList<String>();
	private static HashMap<Integer,String>labelMap = new HashMap<Integer,String>();
	
	public void CompareObjects() {
		
		int objKey=0;
		int objLabelCount=0;
		int objLabelMatchCount=0;
		
		
		for (TableObject tObj : tableList) {
		
			if (objKey != tObj.getPrimarykey()) {
				objKey=tObj.getPrimarykey();
				objLabelCount = tObj.getLabelcount();
				objLabelMatchCount=0;
			}
			
			
			
		}
		   
		    
		
		
	}
	
	
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {

	try {  
		HttpSession session = request.getSession(); 
		tableList.clear();
		conn = DataAccess.GetCloudConnection();	
		statement = conn.createStatement();
		resultSet = statement.executeQuery("SELECT id,ipath,iname,ilabel,iscore,modified,imgsize,labels FROM images order by 1");
		while (resultSet.next()) {
			TableObject tObj = new TableObject();
			tObj.setPrimarykey(resultSet.getInt("primarykey"));
			tObj.setImgpath(resultSet.getString("ipath"));
			tObj.setImgname(resultSet.getString("iname"));
			tObj.setLabel(resultSet.getString("ilabel"));
			tObj.setScore(resultSet.getFloat("iscore"));
			tObj.setImgmodified(resultSet.getString("modified"));
			tObj.setImgsize(resultSet.getLong("imgSize"));
			tObj.setLabelcount(resultSet.getInt("labels"));
			labelMap.put(tObj.getPrimarykey(), tObj.getLabel());
			tableList.add(tObj);
		}
		
		//request.setAttribute("cloudTableList", tableList);
		//System.out.println("cloudTableList has " + tableList.size() + " elements");
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