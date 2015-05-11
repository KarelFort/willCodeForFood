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
		QueryManagement queries = null;
		try {
			queries = new QueryManagement(getServletContext());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String name = (String) request.getParameter("name");
		if(name == null) {//parameter name was not set, which means I am normaly editting. Filling form with data from DB
			
			//get data for one query, which I want to edit
			int id = Integer.parseInt(request.getParameter("id"));
			
			Query gueryToEdit = queries.getQuery(id);
			
			request.setAttribute("gueryToEdit", gueryToEdit);			
		} else { // parameter name was set, which means I am forwarded here from doPost after submit was not sucessfull. Filling form with submited data
			int id = Integer.parseInt(request.getParameter("id"));
			String statement = (String) request.getParameter("statement");
			String info = (String) request.getParameter("info");
			
			Query gueryToEdit = new Query();
			
			gueryToEdit.setId(id);
			gueryToEdit.setName(name);
			gueryToEdit.setInfo(info);
			gueryToEdit.setStatement(statement);	
			
			request.setAttribute("gueryToEdit", gueryToEdit);
		}
		
		//get List of queries to show in left menu	
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
		
		if(queries.updateQuery(id, statement, name, info)){
			request.getSession().setAttribute("message", "Query edited successfully");
			request.getSession().setAttribute("message_type", "success");			
			response.sendRedirect("administration");
		} else {
			request.setAttribute("message", "Something went wrong. Check SQL syntax.");
			request.setAttribute("message_type", "danger");
			

			request.setAttribute("name", name);
			/*		request.setAttribute("info", info);
			request.setAttribute("statement", statement);
	*/		
			doGet(request, response);
		/*	RequestDispatcher dispatcher = request.getRequestDispatcher("edit-query.jsp");
			dispatcher.forward(request, response);
	*/	}
	}

}
