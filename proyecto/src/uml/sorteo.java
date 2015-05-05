package uml;

import java.util.Date;

public class sorteo {

	private Date diaInicio;
	private Date diaFin;
	private Date diaSorteo;

    public sorteo() {
    }

    public sorteo(Date diaInicio, Date diaFin, Date diaSorteo) {
        this.diaInicio = diaInicio;
        this.diaFin = diaFin;
        this.diaSorteo = diaSorteo;
    }

        
	public Date getDiaInicio() {
		return this.diaInicio;
	}

	/**
	 * 
	 * @param diaInicio
	 */
	public void setDiaInicio(Date diaInicio) {
		this.diaInicio = diaInicio;
	}

	public Date getDiaFin() {
		return this.diaFin;
	}

	/**
	 * 
	 * @param diaFin
	 */
	public void setDiaFin(Date diaFin) {
		this.diaFin = diaFin;
	}

	public Date getDiaSorteo() {
		return this.diaSorteo;
	}

	/**
	 * 
	 * @param diaSorteo
	 */
	public void setDiaSorteo(Date diaSorteo) {
		this.diaSorteo = diaSorteo;
	}

	
}