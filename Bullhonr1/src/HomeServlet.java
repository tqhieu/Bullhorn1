

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DbPost;
import customTools.DbUser;
import model.Bhuser;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date postdate = new Date();
		String posttext = request.getParameter("posttext");
		String nextURL = "/error.jsp";
		//need a reference to the session
		//get user out of session. If they don't exist then send them back to the login page.
		//kill the session while you're at it.
		 HttpSession session = request.getSession();
		if (session.getAttribute("user")==null){
		    nextURL = "/login.jsp";
		    session.invalidate();
		    response.sendRedirect(request.getContextPath() + nextURL);
		    return;//return prevents an error
		}
		 
		//get user information from session so we can connect to the db
		Bhuser user = (Bhuser)session.getAttribute("user");
		
		java.util.Date d = new java.util.Date();
		 postdate = new java.sql.Date(d.getTime());//returns current date
		 posttext = "This is a test2";
		 int userid = (int) user.getBhuserid();
		 
		 DbPost.insert(postdate, posttext, userid);
		
		
		//go to the newsfeed or error
		getServletContext().getRequestDispatcher(nextURL).forward(request, response);
		
		// TODO Auto-generated method stub
		//this page does not require user to be logged in
		//we could make it an html page or a jsp page
		//the jsp page allows me to use code from other pages so 
		String useremail = request.getParameter("email");
		String userpassword = request.getParameter("password");
		String action = request.getParameter("action");
		String nextURL = "/error.jsp";
		
		//get an instance of the session so we can set attributes to it
		//the JSP and NavBar will read from the session 
		//The session is one of the primary ways we maintain state
		//in an otherwise stateless web application
		HttpSession session = request.getSession();
		
		//create a variable to hold our user. The variable must be defined
		//so it is visible to all code within the method. So define it here
		//but set it to null since we don't have a user yet.
		Bhuser user = null;
		//create an instance of the user and put it in the session
		//only add the user to the session if the user if valid.
		//The presence of the user is used to determine who 
		//owns the site and will be used to connect to the database
		if (action.equals("logout")){
			session.invalidate();
			nextURL = "/login.jsp";
			
		}else{
			user = DbUser.getUserByEmail(useremail);
			if (DbUser.isValidUser(useremail,userpassword)){
				session.setAttribute("user", user);
				int size = 30;
				nextURL = "/home.jsp";
			}else{
				nextURL = "/login.jsp";
			}
				
		}
		//redirect to next page as indicated by the value of the nextURL variable
		//getServletContext().getRequestDispatcher(nextURL).forward(request,response);
		response.sendRedirect(request.getContextPath() + nextURL);
		
		doGet(request, response);
	}

}
