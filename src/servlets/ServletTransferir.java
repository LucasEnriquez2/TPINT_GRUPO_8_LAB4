package servlets;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;

/**
 * Servlet implementation class ServletTransferir
 */
@WebServlet("/ServletTransferir")
public class ServletTransferir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTransferir() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String NroDeCuenta = request.getParameter("ncuenta");
		String Monto = request.getParameter("monto");
		String Detalle = request.getParameter("detalle");
		String Cbu = request.getParameter("cbu");
		    
		    
		CuentaNegocioImpl neg = new CuentaNegocioImpl();
		CuentaNegocioImpl negM = new CuentaNegocioImpl();
		MovimientoNegocioImpl negMov = new MovimientoNegocioImpl();

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out2 = response.getWriter();
		
		// Crear un mensaje de confirmación
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea enviar esta transferencia?", "Confirmación", JOptionPane.YES_NO_OPTION);

        // Verificar la respuesta del usuario
        if (respuesta == JOptionPane.YES_OPTION) {
        	if (!neg.ExisteCbu(Cbu)) {
    			out.print("<p style='color: red;'>El CBU no existe. Por favor, verifique el CBU ingresado.</p>"
    					+ "<a href='Transferir.jsp'> Regresar </a>");
    			out.flush();
    			return;
    			
    		}
    		
    		
    		if(!negM.PuedeTransferir(Float.parseFloat(Monto), NroDeCuenta)){
    			out2.print("<p style='color: red;'> No posee este monto actualmente para realizar una transferencia y/o el monto ingresado debe ser mayor a 0.</p>"
    					+ "<a href='Transferir.jsp'> Regresar </a>");
    			out2.flush();
    			return;
    		}
    		
    		
    		if(request.getParameter("btnConfirmar")!=null) {
    			negMov.CrearMovimiento(NroDeCuenta, Cbu, Monto, Detalle);
    			response.sendRedirect("Movimientos.jsp");
    		}
        	
        	
            
        } else {
            response.sendRedirect("Transferir.jsp");
        }

		
		   
	}			
				
		
		
		
	   
	   
	    
	    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
