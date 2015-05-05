package uml;

import java.util.Calendar;

public class Menor extends persona {

	private String sexo;
	private String dni;
	private Calendar fechaNac;
	private boolean discapacidad;

    public Menor() {
    }

    public Menor(String sexo, String dni, Calendar fechaNac, boolean discapacidad) {
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

	public Calendar getFechaNac() {
		return this.fechaNac;
	}

	/**
	 * 
	 * @param fechaNac
	 */
	public void setFechaNac(Calendar fechaNac) {
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