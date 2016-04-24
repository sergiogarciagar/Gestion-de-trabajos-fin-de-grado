package es.upm.dit.isst.tfg;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.*;


import es.dit.upm.isst.t4.dao.TFGDAO;
import es.dit.upm.isst.t4.dao.TFGDAOImpl;
import es.upm.dit.isst.t4.model.TFG;

@SuppressWarnings("serial")
public class AceptarTutorServlet extends HttpServlet {		
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		TFGDAO dao = TFGDAOImpl.getInstance( );
		String autor = (String) req.getUserPrincipal().getName();
		TFG tfg1= dao.read_alumno(req.getParameter("autor"));
		tfg1.setEstado(2);
		dao.update(tfg1);
		 resp.sendRedirect("./isst_tfg_t4");
	}
}

