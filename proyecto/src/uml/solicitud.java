package uml;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class solicitud {
<<<<<<< HEAD
    
        private inscripcion[] lInsc = new inscripcion[2];
=======

	private int nSolicitud;
	private Date fecha;
	private Calendar hora;
        private sorteo srt;
        private String situacion;
        private int orden;
        private ArrayList<inscripcion> lInsc = new ArrayList();
>>>>>>> 1cfde457e9805a75a42df78627bef8ae6b59b805

    public solicitud() {
    }
 public solicitud(int nSolicitud, Date fecha, Calendar hora, sorteo srt, String situacion) {
        this.nSolicitud = nSolicitud;
        this.fecha = fecha;
        this.hora = hora;
        this.srt = srt;
        this.situacion = situacion;
    }

<<<<<<< HEAD
    public inscripcion[] getlInsc() {
=======
    public solicitud(int nSolicitud, Date fecha, Calendar hora, sorteo srt) {
        this.nSolicitud = nSolicitud;
        this.fecha = fecha;
        this.hora = hora;
        this.srt = srt;
        
    }
    public sorteo getSrt() {
        return srt;
    }

    public void setSrt(sorteo srt) {
        this.srt = srt;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
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

    public ArrayList<inscripcion> getlInsc() {
>>>>>>> 1cfde457e9805a75a42df78627bef8ae6b59b805
        return lInsc;
    }

    public void setlInsc(ArrayList<inscripcion> lInsc) {
        this.lInsc = lInsc;
    }

   
  
}