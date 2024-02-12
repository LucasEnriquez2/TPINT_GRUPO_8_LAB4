package entidad;

public class Usuario {

	String usuario;
	String dni;
	String contrasenia;
	boolean esAdmin;
	
	public Usuario() {
		
	}
	
	public Usuario(String u, String d, String c, boolean e) {
		this.usuario = u;
		this.dni = d;
		this.contrasenia = c;
		this.esAdmin = e;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public boolean isEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}
	
	
}