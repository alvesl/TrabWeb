package jsp.trab3;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.trab3.dao.UserDAO;
import jsp.trab3.model.UserModel;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext application = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException { 
         application = config.getServletContext();
    } 
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthBean authBean = new AuthBean("Administrador", "./Admin");
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
			if (usr.getPassword().equals(pass)) {
				//Auth ok
				 dispatcher = request.getRequestDispatcher("/WEB-INF/adminMenu.html");
			
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
