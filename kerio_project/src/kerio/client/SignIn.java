package kerio.client;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kerio.model.AdminManagement;

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/sign-in")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminManagement admins = null;
		try {
			admins = new AdminManagement(getServletContext());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String passwordInfo = admins.getPasswordInfo();
		
		request.setAttribute("passwordInfo", passwordInfo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("sign-in.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String password = request.getParameter("password");

		
		AdminManagement admins = null;
		try {
			admins = new AdminManagement(getServletContext());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (admins.passwordIsCorrect(password)){
			request.getSession().setAttribute("admin", "YES");
			response.sendRedirect("administration");
		} else {
			request.setAttribute("message", "Password incorrect. Try again.");
			request.setAttribute("message_type", "danger");
			doGet(request, response );}		
	}

}
