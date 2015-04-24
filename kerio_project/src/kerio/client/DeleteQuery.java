package kerio.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kerio.client.statistic.model.QueryManagement;

/**
 * Servlet implementation class DeleteQuery
 */
@WebServlet("/delete-query")
public class DeleteQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
			
		QueryManagement queries = new QueryManagement();
		queries.deleteQuery(id);
			
		request.getSession().setAttribute("message", "Query deleted succesfully");
		request.getSession().setAttribute("message_type", "success");	
		
		response.sendRedirect("administration");
	}

}
