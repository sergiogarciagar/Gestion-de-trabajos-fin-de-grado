package es.upm.dit.isst.tfg;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.dit.upm.isst.t4.dao.TFGDAO;
import es.dit.upm.isst.t4.dao.TFGDAOImpl;
import es.upm.dit.isst.t4.model.TFG;

@SuppressWarnings("serial")
public class ISST_TFG_T4Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		TFGDAO dao = TFGDAOImpl.getInstance( );
		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLoginURL(req.getRequestURI());
		String urlLinktext = "Entrar"; 
		String user = "" ;
		TFG tfg = null;
		List<TFG> tfgs = null;
		if (req.getUserPrincipal() != null) {
			user = req.getUserPrincipal().getName();
			url = userService. createLogoutURL(req.getRequestURI());
			urlLinktext = "Salir";
			tfg = dao.read_alumno(user);
			tfgs = dao.read_tutor(user);
		}
		req.getSession().setAttribute("user", user); 
		req.getSession().setAttribute("url", url); 
		req.setAttribute("tfg", tfg);
		req.setAttribute("tfgs", tfgs);
		req.getSession().setAttribute("urlLinktext", urlLinktext);
		RequestDispatcher view = req.getRequestDispatcher("MostrarTFGView.jsp"); 
		view.forward(req,resp);
		
	}
}
