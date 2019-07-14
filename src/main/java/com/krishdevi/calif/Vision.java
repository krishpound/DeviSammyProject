package com.krishdevi.calif;

import java.io.IOException;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.text.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Vision
 */
@WebServlet(
		name = "Vision",
	    urlPatterns = {"/vision"}
)

public class Vision extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String[] imageList;
	private static ArrayList<VisionObject>  visionLabelList = new ArrayList<VisionObject>();
	private static ArrayList<String>sectionList = new ArrayList<String>();
	private static File imageFolder = new File("data/");
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Vision() {
        super();
    }
 
    protected ArrayList<String> getSections(ArrayList<VisionObject> visionList) {
    	ArrayList<String>Sections = new ArrayList<String>();
    	for (VisionObject vObj : visionList) {if (!Sections.contains(vObj.getImgid())) Sections.add(vObj.getImgid());}
    	return Sections;
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession(); 
			visionLabelList.clear();
			sectionList.clear();
			File[] images = imageFolder.listFiles();
			Calendar calendar = Calendar.getInstance();
			String lastModified;
			
			for (File img : images) {
				if(img.getName().toUpperCase().contains(".JPG") || img.getName().toUpperCase().contains(".PNG")) {
				    calendar.setTimeInMillis(img.lastModified());
				    lastModified = df.format(calendar.getTime());
				    visionLabelList = VisionLabel.getImageLabels(img.getParent(),img.getName(),lastModified,img.length());}
			}
			PersistVisionAI.Go(visionLabelList);
			sectionList = getSections(visionLabelList);
			request.setAttribute("visionLabelList", visionLabelList);
			request.setAttribute("sectionList", sectionList);
			getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
		}
		catch (ServletException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
