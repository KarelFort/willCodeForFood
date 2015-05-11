package kerio.client;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kerio.model.QueryManagement;



/**
 * Servlet implementation class AddQuery
 */
@WebServlet("/add-query")
public class AddQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//next 4 lines are here for the case, that syntax of SQL is wrong and user is forwarded back to form
		String message = (String) request.getParameter("message");
		String message_type = (String) request.getParameter("message_type");		
		request.setAttribute("message", message);
		request.setAttribute("message_type", message_type);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("add-query.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String statement = (String) request.getParameter("statement");
		String name = (String) request.getParameter("name");
		String info = (String) request.getParameter("info");
		
		QueryManagement queries = null;
		try {
			queries = new QueryManagement(getServletContext());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(queries.addQuery(statement, name, info)){
			request.getSession().setAttribute("message", "Statistic added successfully");
			request.getSession().setAttribute("message_type", "success");	
			response.sendRedirect("administration");
		} else {
			request.setAttribute("message", "Something went wrong. Check SQL syntax.");
			request.setAttribute("message_type", "danger");
			
			request.setAttribute("name", name);
			request.setAttribute("info", info);
			request.setAttribute("statement", statement);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("add-query.jsp");
			dispatcher.forward(request, response);
		}
	}

}
