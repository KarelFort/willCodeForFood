package kerio.client;

import java.io.IOException;

import kerio.data.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kerio.client.statistic.model.QueryManagement;



/**
 * Servlet implementation class EditQuery
 */
@WebServlet("/edit-query")
public class EditQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		QueryManagement queries = new QueryManagement();
		Query gueryToEdit = queries.getQuery(id);
		
		request.setAttribute("gueryToEdit", gueryToEdit);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit-query.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String statement = (String) request.getParameter("statement");
		String name = (String) request.getParameter("name");
		String info = (String) request.getParameter("info");
			
		QueryManagement queries = new QueryManagement();
		queries.updateQuery(id, statement, name, info);
			
		request.getSession().setAttribute("message", "Query edited succesfully");
		request.getSession().setAttribute("message_type", "success");	
		
		response.sendRedirect("administration");
	}

}
