package com.projectOne.servlets;
import java.io.*;
import java.util.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class Results
 */
//@WebServlet("/Results")
public class Results extends HttpServlet {
	private static final long serialVersionUID = 1;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Results() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/Display");
		rd.forward(request, response);
		
		String file_name = null;
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if(!isMultipartContent) {
			return;
		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			
			
			List <FileItem> fields = upload.parseRequest(request);
			Iterator <FileItem> it = fields.iterator();
			if(!it.hasNext()) {
				return;
			}
			while (it.hasNext()) {
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				if(isFormField) {
					if(file_name == null) {
						if(fileItem.getFieldName().equals("file_name")) {
							file_name = fileItem.getString(); //////////
						}
					}
				}else {
					if(fileItem.getSize() > 0) {
						fileItem.write(new File("C:\\uploaded_files\\" + fileItem.getName()));

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.println("<script type='text/javascript'>");
			out.println("window.location.href='index.jsp?filename="+file_name+"'");
			out.println("</script>");
			
			out.close();
		}
	}
}
