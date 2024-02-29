package entidad;

public class FaltaPuntoException extends Exception{

	
	public FaltaPuntoException() {
		
	}
	
	@Override
	public String getMessage() {
		return "falta punto";
	}
	
	
	
}
