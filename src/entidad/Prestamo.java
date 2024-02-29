package entidad;

import java.sql.Date;

public class Prestamo {
	
	private int NdeSolicitud;
	private int NroDeCliente;
	private int NroCuenta;
	private Date Fecha;
	private float ImporteSolicitado;
	private float ImporteAPagar;
	private int Plazo;
	private float Monto;
	private String Estado;
	private int CuotasPagas;
	
	public Prestamo() {
		
	}
	
	public Prestamo(int ndeSolicitud, int nroDeCliente, int nroCuenta, Date fecha, float importeSolicitado,
			float importeAPagar, int plazo, float monto, String estado, int cuotasPagas) {
		super();
		NdeSolicitud = ndeSolicitud;
		NroDeCliente = nroDeCliente;
		NroCuenta = nroCuenta;
		Fecha = fecha;
		ImporteSolicitado = importeSolicitado;
		ImporteAPagar = importeAPagar;
		Plazo = plazo;
		Monto = monto;
		Estado = estado;
		CuotasPagas = cuotasPagas;
	}

	public int getNdeSolicitud() {
		return NdeSolicitud;
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

	public int getCuotasPagas() {
		return CuotasPagas;
	}

	public void setCuotasPagas(int cuotasPagas) {
		CuotasPagas = cuotasPagas;
	}
	
	
	
		
		
}
