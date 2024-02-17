package entidad;

import java.sql.Date;

public class Solicitud {
	
	


	private int NdeSolicitud;
	private int NroDeCliente;
	private int NroCuenta;
	private Date Fecha;
	private float ImporteSolicitado;
	private float ImporteAPagar;
	private int Plazo;
	private float Monto;
	private String Estado;
	
	public Solicitud() {
		
	}
	
	public Solicitud(int nroDeCliente, int nroCuenta, float importeSolicitado, int plazo, String estado) {
		super();
		
		NroDeCliente = nroDeCliente;
		NroCuenta = nroCuenta;
		ImporteSolicitado = importeSolicitado;
		ImporteAPagar = importeSolicitado + ((importeSolicitado * plazo) / 100);
		Plazo = plazo;
		Monto = ImporteAPagar/plazo;
		Estado = estado;
	}


	public void setNdeSolicitud(int ndeSolicitud) {
		NdeSolicitud = ndeSolicitud;
	}
	
	public int getNroDeCliente() {
		return NroDeCliente;
	}


	public void setNroDeCliente(int nroDeCliente) {
		NroDeCliente = nroDeCliente;
	}


	public int getNroCuenta() {
		return NroCuenta;
	}


	public void setNroCuenta(int nroCuenta) {
		NroCuenta = nroCuenta;
	}


	public Date getFecha() {
		return Fecha;
	}


	public void setFecha(Date fecha) {
		Fecha = fecha;
	}



	public float getImporteSolicitado() {
		return ImporteSolicitado;
	}


	public void setImporteSolicitado(float importeSolicitado) {
		ImporteSolicitado = importeSolicitado;
	}


	public float getImporteAPagar() {
		return ImporteAPagar;
	}


	public void setImporteAPagar(float importeAPagar) {
		ImporteAPagar = importeAPagar;
	}


	public int getPlazo() {
		return Plazo;
	}


	public void setPlazo(int plazo) {
		Plazo = plazo;
	}


	public float getMonto() {
		return Monto;
	}


	public void setMonto(float monto) {
		Monto = monto;
	}


	public String getEstado() {
		return Estado;
	}


	public void setEstado(String estado) {
		Estado = estado;
	}


	public int getNdeSolicitud() {
		return NdeSolicitud;
	}
	
	
	
}
