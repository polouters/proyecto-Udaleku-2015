package uml;

import java.util.Date;



public class Menor extends persona {

	private String sexo;
	private String dni;
	private Date fechaNac;
	private boolean discapacidad;

    public Menor() {
    }

    public Menor(String sexo, String dni, Date fechaNac, boolean discapacidad) {
        this.sexo = sexo;
        this.dni = dni;
        this.fechaNac = fechaNac;
        this.discapacidad = discapacidad;
    }
        
        

	public String getSexo() {
		return this.sexo;
	}

	/**
	 * 
	 * @param sexo
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDni() {
		return this.dni;
	}

	/**
	 * 
	 * @param dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getFechaNac() {
		return this.fechaNac;
	}

	/**
	 * 
	 * @param fechaNac
	 */
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public boolean getDiscapacidad() {
		return this.discapacidad;
	}

	/**
	 * 
	 * @param discapacidad
	 */
	public void setDiscapacidad(boolean discapacidad) {
		this.discapacidad = discapacidad;
	}


}