package es.upm.dit.isst.tfg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.dit.upm.isst.t4.dao.TFGDAO;
import es.dit.upm.isst.t4.dao.TFGDAOImpl;
import es.upm.dit.isst.t4.model.TFG;

@SuppressWarnings("serial")
public class SolicitarServlet extends HttpServlet {		
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		TFGDAO dao = TFGDAOImpl.getInstance( );
		String autor = (String) req.getUserPrincipal().getName();
		TFG tfg = dao.create(autor, req.getParameter("titulo") ,req.getParameter("resumen") ,req.getParameter("tutor") , null, null, 1);
		 resp.sendRedirect("./isst_tfg_t4");
	}
}
