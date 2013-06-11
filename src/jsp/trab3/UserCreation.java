package jsp.trab3;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.trab3.dao.LabDAO;
import jsp.trab3.dao.UserDAO;
import jsp.trab3.model.LabModel;
import jsp.trab3.model.UserModel;

/**
 * Servlet implementation class UserCreation
 */
@WebServlet("/UserCreation")
public class UserCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCreation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Popular lista de usuários
		ArrayList<UserModel> usrs = new ArrayList<UserModel>();
		usrs.addAll(UserDAO.getUsers());
		
		request.setAttribute("users", usrs);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/userCreation.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// Verificar qual ação foi tomada
		
		String act = request.getParameter("option");
		
		if (act.equals("Cadastrar")) {
			String login = request.getParameter("login");
			String pass = request.getParameter("pass");
			String confPass = request.getParameter("confPass");
			String fullName = request.getParameter("fullName");
			String email = request.getParameter("email");
			
			String isAdmin = request.getParameter("isAdmin");
			
			
			if (!login.equals("") && !pass.equals("") && !fullName.equals("") && !email.equals("")) {
				// Verificar se os passwords combinam
				
				if (pass.equals(confPass)) {				
					//Ok! Cadastrar somente se não existir
					if (UserDAO.getUser(login) == null) {
						UserModel usr = new UserModel();
						usr.setLogin(login);
						usr.setPassword(pass);
						usr.setFullName(fullName);
						usr.setEmail(email);
						Boolean adm = isAdmin.equals("1") ? true : false;
						usr.setIsAdmin(adm);
						
						UserDAO.insert(usr);
						
					}				
				}
			}
		}
		
		else if (act.equals("X")) {
			//Remover usuário
			
			String hid = request.getParameter("delUser");
			
			String[] split = hid.split("del");
			
			UserModel usr = new UserModel();
			
			usr =  UserDAO.getUser(split[1]);
			
			UserDAO.remove(usr);
			
			
		}
		
		
		
		//Popular lista de usuários
		ArrayList<UserModel> usrs = new ArrayList<UserModel>();
		usrs.addAll(UserDAO.getUsers());
		
		request.setAttribute("users", usrs);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/userCreation.jsp");
		dispatcher.forward(request, response);
	}

}
