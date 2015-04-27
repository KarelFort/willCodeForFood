package kerio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;






import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import kerio.model.DataManagement;


/**
 * Servlet implementation class json
 * Testing JSON format only! 
 */
@WebServlet("/json")
public class Json extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Json() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(
//				request.getInputStream()));
//		String jsonString = "";
//		if (br != null) {
//			jsonString = br.readLine();
//		}
//		String jsonString = "{\"id\": 9, \"date1\":\"2015-03-01\", \"date2\":\"2015-03-30\"}";
//		int id = -1;
//		String date1 = null;
//		String date2 = null;
//		try {
//			JSONObject jObjRecieved = new JSONObject(jsonString);
//			id = jObjRecieved.getInt("id");
//			date1 = jObjRecieved.getString("date1");
//			date2 = jObjRecieved.getString("date2");
//
//		} catch (JSONException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		JSONObject jObj = new JSONObject();
//		DataManagement dm = null;
//		try {
//			dm = new DataManagement(getServletContext());
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		jObj = dm.dataService(id, date1, date2);
//		response.setContentType("application/json");
//		response.getWriter().write(jObj.toString());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 1. get received JSON data from request
		BufferedReader br = new BufferedReader(new InputStreamReader(
				request.getInputStream()));
		String jsonString = "";
		if (br != null) {
			jsonString = br.readLine();
		}
		// String jsonString = "{\"query\": [{\"id\": 1, \"date1\":\"2015-03-30\", \"date2\":\"2015-03-30\"}]}";
		int id = -1;
		String date1 = null;
		String date2 = null;
		try {
			JSONObject jObjRecieved = new JSONObject(jsonString);
			id = jObjRecieved.getInt("id");
			date1 = jObjRecieved.getString("date1");
			date2 = jObjRecieved.getString("date2");

		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JSONObject jObj = new JSONObject();
		DataManagement dm = null;
		try {
			dm = new DataManagement(getServletContext());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jObj = dm.dataService(id, date1, date2);
		response.setContentType("application/json");
		response.getWriter().write(jObj.toString());
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(jObj.toString());
		String prettyJsonString = gson.toJson(je);
		System.out.println("pretty "+prettyJsonString);

	}

}
