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
import jsp.trab3.model.LabModel;

/**
 * Servlet implementation class LabCreation
 */
@WebServlet(description = "Creates and destroys labs", urlPatterns = { "/LabCreation" })
public class LabCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LabCreation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<LabModel> labs = new ArrayList<LabModel>();
		labs.addAll(LabDAO.getList());
		
		request.setAttribute("labs", labs);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/labCreation.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// Verificar qual ação foi tomada
		
		String act = request.getParameter("option");
		
		if (act.equals("Cadastrar")) {
			String name = request.getParameter("labName");
			
			if (name != null) {
				// Registrar um novo lab
				LabModel lab = new LabModel();
				lab.setName(name);
				
				if (LabDAO.getLab(name) == null)
					LabDAO.insert(lab);
				
			}
			
			
		}
		else if (act.equals("X")) {
			String hid = request.getParameter("hidden");
			
			String[] split = hid.split("del");
			
			LabModel lab = new LabModel();
			
			lab = LabDAO.getLab(split[1]);
	
			LabDAO.remove(lab);
			
		} 
		else {
			// Usuário manipulou string 
		}
		
		// Popular lista
		
		ArrayList<LabModel> labs = new ArrayList<LabModel>();
		labs.addAll(LabDAO.getList());
		
		request.setAttribute("labs", labs);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/labCreation.jsp");
		dispatcher.forward(request, response);
	}

}
