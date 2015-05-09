package kerio.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kerio.data.Query;
import kerio.model.AdminManagement;
import kerio.model.QueryManagement;

/**
 * Servlet implementation class Index
 */
@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		/**
		 * get list of all queries from Db in alfabetical order
		 * used for the list in left menu
		 */
		List<Query> allQueries = null;
		try {
			QueryManagement queries = new QueryManagement(getServletContext());
			allQueries = queries.getAllQueries();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		
		/**
		 * get timestamp from table logs, when was the database updated for the last time
		 */
		String lastUpdate = null;
		try {
			AdminManagement admins = new AdminManagement(getServletContext());
			lastUpdate = admins.getDatabaseLastUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		
		request.setAttribute("allQueries", allQueries);
		request.setAttribute("lastUpdate", lastUpdate);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
