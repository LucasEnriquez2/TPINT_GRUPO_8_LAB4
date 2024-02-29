package entidad;

public class FaltaArrobaException extends Exception{

	public FaltaArrobaException() {}
	
	@Override 
	public String getMessage() {
		return "falta arroba";
	}
	
}
