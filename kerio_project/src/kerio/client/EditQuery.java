package kerio.client;

import java.io.IOException;
import java.util.List;

import kerio.data.Query;
import kerio.model.QueryManagement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get data for one query, which I want to edit
		int id = Integer.parseInt(request.getParameter("id"));
		
		QueryManagement queries = null;
		try {
			queries = new QueryManagement(getServletContext());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Query gueryToEdit = queries.getQuery(id);
		
		request.setAttribute("gueryToEdit", gueryToEdit);
		
		
		//get List of queries to show in left menu
		try {
			queries = new QueryManagement(getServletContext());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Query> allQueries = queries.getAllQueries();
		
		request.setAttribute("allQueries", allQueries);
		
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
			
		QueryManagement queries = null;
		try {
			queries = new QueryManagement(getServletContext());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		queries.updateQuery(id, statement, name, info);
			
		request.getSession().setAttribute("message", "Query edited succesfully");
		request.getSession().setAttribute("message_type", "success");	
		
		response.sendRedirect("administration");
	}

}
