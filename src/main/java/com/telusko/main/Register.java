package com.telusko.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.JdbcUtility;


@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String id = request.getParameter("Id");
		String name = request.getParameter("ename");
		String amount = request.getParameter("esal");

		System.out.println(name + " ," + amount);
		Connection connect = null;
		String query = "INSERT INTO employee(eid,ename,esal) " + "VALUES(?,?,?)";
		PreparedStatement pstmnt = null;
		PrintWriter writer=response.getWriter();

		try {
			connect = JdbcUtility.getDBConnection();
			if (connect != null) {
				pstmnt = connect.prepareStatement(query);

				if (pstmnt != null) {
					pstmnt.setInt(1, 11);
					pstmnt.setString(2, name);
					pstmnt.setInt(3, Integer.parseInt(amount));
					int row=pstmnt.executeUpdate();
					writer.println("<html><head><title>Vaccine Reg</title></head>");
					if (row != 0) {

						writer.println("<body><h1>Registered!</h1></body>");
					} else {

						writer.println("<body><h1>Fail to Register!</h1></body>");
					}

					writer.println("</html>");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				JdbcUtility.closeResource(connect, pstmnt);
				writer.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
