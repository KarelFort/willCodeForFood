package kerio.client;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kerio.data.Query;
import kerio.model.QueryManagement;

/**
 * Servlet implementation class StatementInfo
 */
public class StatementInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatementInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		QueryManagement queries = null;
		try {
			queries = new QueryManagement(getServletContext());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Query queryInfo = queries.getQueryWithDate(id);
/*		
		System.out.println(queryInfo.getId() +" "+queryInfo.getInfo()+" "+queryInfo.getMinDate()+" "+queryInfo.getMaxDate()
				+ " "+queryInfo.getName()+" "+queryInfo.getStatement());*/
		request.setAttribute("QueryInfo", queryInfo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("query-datepicker.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
