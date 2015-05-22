package uml;

import java.util.ArrayList;
import java.util.Date;
/**
 *
 * 
 * @author Ruben
 * @author Mikel
 * @author Jon
 * @version Beta 1.0
 * @since Early May
 * 
 */
public class sorteo {

	private Date diaInicio;
	private Date diaFin;
	private Date diaSorteo;
        private ArrayList <solicitud> lSorteo = new ArrayList();

    public sorteo() {
    }

    public sorteo(Date diaInicio, Date diaFin, Date diaSorteo,ArrayList <solicitud> lSorteo) {
        this.diaInicio = diaInicio;
        this.diaFin = diaFin;
        this.diaSorteo = diaSorteo;
        this.lSorteo = lSorteo;
    }


    public Date getDiaInicio() {
        return diaInicio;
    }

    public void setDiaInicio(Date diaInicio) {
        this.diaInicio = diaInicio;
    }

    public Date getDiaFin() {
        return diaFin;
    }

    public void setDiaFin(Date diaFin) {
        this.diaFin = diaFin;
    }

    public Date getDiaSorteo() {
        return diaSorteo;
    }

    public void setDiaSorteo(Date diaSorteo) {
        this.diaSorteo = diaSorteo;
    }

    public ArrayList<solicitud> getlSorteo() {
        return lSorteo;
    }

    public void setlSorteo(ArrayList<solicitud> lSorteo) {
        this.lSorteo = lSorteo;
    }
          
}