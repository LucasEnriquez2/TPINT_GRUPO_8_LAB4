package entidad;

import java.sql.Date;

public class Cliente {
	private int NdeCliente;
	private String DNI;
	private String CUIL;
	private String apellido;
	private String sexo;
	private String nombre;
	private String nacionalidad;
	private Date fechaDeNacimiento;
	private String direccion;
	private String localidad;
	private String provincia;
	private String mail;
	private String Telefono;
	private String usuario;
	private String contrasenia;
	private String estado;
	
	
	public Cliente(int ndeCliente, String dNI, String cUIL, String apellido, String sexo, String nombre,
			String nacionalidad, Date fechaDeNacimiento, String direccion, String localidad, String provincia,
			String mail, String usuario, String contrasenia, String estado) {
		super();
		NdeCliente = ndeCliente;
		DNI = dNI;
		CUIL = cUIL;
		this.apellido = apellido;
		this.sexo = sexo;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.mail = mail;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.estado=estado;
	}
	
	public Cliente() {}
//test
	
	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public int getNdeCliente() {
		return NdeCliente;
	}

	public void setNdeCliente(int ndeCliente) {
		NdeCliente = ndeCliente;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getCUIL() {
		return CUIL;
	}

	public void setCUIL(String cUIL) {
		CUIL = cUIL;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String i) {
		this.estado = i;
	}

	@Override
	public String toString() {
		return "Cliente [NdeCliente=" + NdeCliente + ", DNI=" + DNI + ", CUIL=" + CUIL + ", apellido=" + apellido
				+ ", sexo=" + sexo + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", fechaDeNacimiento="
				+ fechaDeNacimiento + ", direccion=" + direccion + ", localidad=" + localidad + ", provincia="
				+ provincia + ", mail=" + mail + ", usuario=" + usuario + ", contrasenia=" + contrasenia + "]";
	}

	
	
	
	
	
	
}
