package uml;

import java.util.ArrayList;

public class centro {

	private String nombre;
	private String modelo;
        private provincia provincia;
        private ArrayList <menor> lMenor = new ArrayList();

    public centro() {
    }

    public centro(String nombre, String modelo, provincia provincia, ArrayList <menor> lMenor) {
        this.nombre = nombre;
        this.modelo = modelo;
        this.provincia = provincia;
        this.lMenor = lMenor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(provincia provincia) {
        this.provincia = provincia;
    }

    public ArrayList<menor> getlMenor() {
        return lMenor;
    }

    public void setlMenor(ArrayList<menor> lMenor) {
        this.lMenor = lMenor;
    }
 
        
}