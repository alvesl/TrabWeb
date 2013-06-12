package jsp.trab3;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.trab3.dao.UserDAO;
import jsp.trab3.model.UserModel;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthBean authBean = new AuthBean("Usuário", "./User");
		request.setAttribute("authBean", authBean);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/auth.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login =  request.getParameter("login");
		String pass = request.getParameter("senha");
		// Realizar login
		UserModel usr ;
		usr = UserDAO.getUser(login);
		RequestDispatcher dispatcher;
		if (usr != null) {
			if (usr.getPassword().equals(pass) && !usr.getIsAdmin()) {
				 request.getSession().setAttribute("user", usr.getFullName());
				 request.getSession().setAttribute("userId", Integer.toString(usr.getId()));
				 response.sendRedirect("./BookingRequest");
				 return;
			}
			
			else {
				dispatcher = request.getRequestDispatcher("/WEB-INF/auth.jsp");
				
			}
		} 
		else {
			dispatcher = request.getRequestDispatcher("/WEB-INF/auth.jsp");
		}

		
		dispatcher.forward(request, response);
	}

}
