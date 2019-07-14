package com.krishdevi.calif;

import java.util.ArrayList;
import java.sql.*;

public class PersistVisionAI {
	
	private static Connection conn;
	private static Statement statement;
	private static ResultSet resultSet;
	private static PreparedStatement preparedStmt1;
	private static PreparedStatement preparedStmt2;
	private static String sql1 = "insert into image_key(ipath,iname,imgsize) values(?,?,?) on conflict (ipath,iname,imgsize) do nothing";
	//private static String sql2 = "insert into images(ipath,iname,ilabel,iscore,modified,imgsize,labels,imgkey) values(?,?,?,?,?,?,?,?) on conflict (ipath,iname,ilabel,imgsize) do nothing";
	private static String sql2 = "insert into images (ipath,iname,ilabel,iscore,modified,imgsize,labels) values (?,?,?,?,?,?,?) on conflict (ipath,iname,ilabel,imgsize) do nothing ";
					
	static protected void Go(ArrayList<VisionObject> visionLabels) {
		
		try {
			
			System.out.println("sql1 = "+sql1);
			System.out.println("sql2 = "+sql2);
			
			
			conn = DataAccess.GetCloudConnection();	
			preparedStmt1 = conn.prepareStatement(sql1);
			preparedStmt2 = conn.prepareStatement(sql2);
		
			for (VisionObject vObj : visionLabels) {
				
				preparedStmt1.setString(1, vObj.getImgpath());
				preparedStmt1.setString(2, vObj.getImgname());
				preparedStmt1.setLong(3, vObj.getImgsize());
				preparedStmt1.execute();
				
				
				preparedStmt2.setString(1,vObj.getImgpath());
				preparedStmt2.setString(2,vObj.getImgname());
				preparedStmt2.setString(3,vObj.getLabel());
				preparedStmt2.setFloat(4,vObj.getScore());
				preparedStmt2.setString(5,vObj.getImgmodified());
				preparedStmt2.setLong(6,vObj.getImgsize());
				preparedStmt2.setInt(7,vObj.getLabelcount());
				preparedStmt2.execute();
				
			}
			
			conn.close();
	
		}
		catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}
}