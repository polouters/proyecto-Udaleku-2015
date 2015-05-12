package uml;

import java.util.ArrayList;

public class provincia {

	private String nombre;
        private centro centro;

    public provincia() {
    }

    public provincia(String nombre,centro centro) {
        this.nombre = nombre;
        this.centro = centro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public centro getCentro() {
        return centro;
    }

    public void setCentro(centro centro) {
        this.centro = centro;
    }
}