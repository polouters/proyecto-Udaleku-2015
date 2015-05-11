package uml;

import java.util.Calendar;
import java.util.Date;

public class solicitud {

	private int nSolicitud;
	private Date fecha;
	private Calendar hora;
        private sorteo srt;
        private String situacion;
        private inscripcion[] lInsc = new inscripcion[2];

    public solicitud() {
    }

    public solicitud(int nSolicitud, Date fecha, Calendar hora, sorteo srt, String situacion) {
        this.nSolicitud = nSolicitud;
        this.fecha = fecha;
        this.hora = hora;
        this.srt = srt;
        this.situacion = situacion;
    }

    public solicitud(int nSolicitud, Date fecha, Calendar hora, sorteo srt,inscripcion[] lInsc) {
        this.nSolicitud = nSolicitud;
        this.fecha = fecha;
        this.hora = hora;
        this.srt = srt;
        this.lInsc = lInsc;
    }

    public int getnSolicitud() {
        return nSolicitud;
    }

    public void setnSolicitud(int nSolicitud) {
        this.nSolicitud = nSolicitud;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Calendar getHora() {
        return hora;
    }

    public void setHora(Calendar hora) {
        this.hora = hora;
    }

    public sorteo getS() {
        return srt;
    }

    public void setS(sorteo srt) {
        this.srt = srt;
    }

    public inscripcion[] getlInsc() {
        return lInsc;
    }

    public void setlInsc(inscripcion[] lInsc) {
        this.lInsc = lInsc;
    }
  
}