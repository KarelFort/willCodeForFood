package kerio.client;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kerio.model.AdminManagement;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/change-password")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		AdminManagement admins = null;
		try {
			admins = new AdminManagement(getServletContext());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String passwordInfo = admins.getPasswordInfo();
		
		request.setAttribute("passwordInfo", passwordInfo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("change-password.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = (String) request.getParameter("password");
		String passwordInfo = (String) request.getParameter("passwordInfo");
		
		AdminManagement admins = null;
		try {
			admins = new AdminManagement(getServletContext());
			admins.changePassword(password, passwordInfo);
			
			request.getSession().setAttribute("message", "Password changed successfully");
			request.getSession().setAttribute("message_type", "success");
		} catch (ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
			request.getSession().setAttribute("message", "Something went wrong. Password not changed.");
			request.getSession().setAttribute("message_type", "danger");
		}
					
		response.sendRedirect("administration");
	}

}
