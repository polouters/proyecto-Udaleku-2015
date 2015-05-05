package uml;

import java.util.Calendar;
import java.util.Date;

public class solicitud {

	private int nSolicitud;
	private Date fecha;
	private Calendar hora;

    public solicitud() {
    }

    public solicitud(int nSolicitud, Date fecha, Calendar hora) {
        this.nSolicitud = nSolicitud;
        this.fecha = fecha;
        this.hora = hora;
    }
        
        

	public int getNSolicitud() {
		return this.nSolicitud;
	}

	/**
	 * 
	 * @param nSolicitud
	 */
	public void setNSolicitud(int nSolicitud) {
		this.nSolicitud = nSolicitud;
	}

	public Date getFecha() {
		return this.fecha;
	}

	/**
	 * 
	 * @param fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Calendar getHora() {
		return this.hora;
	}

	/**
	 * 
	 * @param hora
	 */
	public void setHora(Calendar hora) {
		this.hora = hora;
	}

}