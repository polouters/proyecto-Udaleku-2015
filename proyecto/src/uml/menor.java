package uml;

import java.util.Date;



public class menor extends persona {

	private String sexo;
	private String dni;
	private Date fechaNac;
	private boolean discapacidad;
        private centro cntr;
        private direccion drc;

    public menor() {
    }

    public menor(String sexo, String dni, Date fechaNac, boolean discapacidad, centro cntr, direccion drc, String nombre, String ape1, String ape2) {
        super(nombre, ape1, ape2);
        this.sexo = sexo;
        this.dni = dni;
        this.fechaNac = fechaNac;
        this.discapacidad = discapacidad;
        this.cntr = cntr;
        this.drc = drc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public boolean isDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(boolean discapacidad) {
        this.discapacidad = discapacidad;
    }

    public centro getCntr() {
        return cntr;
    }

    public void setCntr(centro cntr) {
        this.cntr = cntr;
    }

    public direccion getDrc() {
        return drc;
    }

    public void setDrc(direccion drc) {
        this.drc = drc;
    }

}