package entidad;

import java.sql.Date;

public class Cuenta {
private int NroCuenta;
private int NroDeCliente;
private String Cbu;
private String TipoDeCuenta;
private Date FechaCreacion;
private float Saldo;
private int Estado;

public Cuenta() {
	
}

public Cuenta(int numCuenta, int nroDeCliente, Date FCreacion, String tipoCuenta, String cbu, float saldo, int estado) {
	this.NroCuenta=numCuenta;
	this.NroDeCliente=nroDeCliente;
	this.Cbu=cbu;
	this.TipoDeCuenta=tipoCuenta;
	this.FechaCreacion=FCreacion;
	this.Saldo=saldo;
	this.Estado=estado;
}



public int getNroCuenta() {
	return NroCuenta;
}

public void setNroCuenta(int nroCuenta) {
	NroCuenta = nroCuenta;
}

public int getNroDeCliente() {
	return NroDeCliente;
}

public void setNroDeCliente(int nroDeCliente) {
	NroDeCliente = nroDeCliente;
}

public String getCbu() {
	return Cbu;
}

public void setCbu(String cbu) {
	Cbu = cbu;
}

public String getTipoDeCuenta() {
	return TipoDeCuenta;
}

public void setTipoDeCuenta(String tipoDeCuenta) {
	TipoDeCuenta = tipoDeCuenta;
}

public Date getFechaCreacion() {
	return FechaCreacion;
}

public void setFechaCreacion(Date fechaCreacion) {
	FechaCreacion = fechaCreacion;
}

public float getSaldo() {
	return Saldo;
}

public void setSaldo(float saldo) {
	Saldo = saldo;
}

public int getEstado() {
	return Estado;
}

public void setEstado(int estado) {
	Estado = estado;
}



}

