package uml;

import java.util.ArrayList;

public class provincia {

	private String nombre;
        private ArrayList<centro> lCentro = new ArrayList();
        private ArrayList<municipio> lMunicipio = new ArrayList();

    public provincia() {
    }

    public provincia(String nombre,ArrayList<centro> lCentro,ArrayList<municipio> lMunicipio) {
        this.nombre = nombre;
        this.lCentro = lCentro;
        this.lMunicipio = lMunicipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<centro> getlCentro() {
        return lCentro;
    }

    public void setlCentro(ArrayList<centro> lCentro) {
        this.lCentro = lCentro;
    }

    public ArrayList<municipio> getlMunicipio() {
        return lMunicipio;
    }

    public void setlMunicipio(ArrayList<municipio> lMunicipio) {
        this.lMunicipio = lMunicipio;
    }
        
        
 
   



}