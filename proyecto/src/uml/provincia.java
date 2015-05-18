package uml;

import java.util.ArrayList;

public class provincia {

	private String nombre;
        private ArrayList<centro> ListaCentros;
        private ArrayList<municipio> ListaMuni;

    public provincia() {
       ListaMuni = new ArrayList();
    }

    public provincia(String nombre, ArrayList<centro> ListaCentros, ArrayList<municipio> ListaMuni) {
        this.nombre = nombre;
        this.ListaCentros = ListaCentros;
        this.ListaMuni = ListaMuni;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<municipio> getListaMuni() {
        return ListaMuni;
    }

    public void setListaMuni(ArrayList<municipio> ListaMuni) {
        this.ListaMuni = ListaMuni;
    }

    public ArrayList<centro> getListaCentros() {
        return ListaCentros;
    }

    public void setListaCentros(ArrayList<centro> ListaCentros) {
        this.ListaCentros = ListaCentros;
    }
    
    
    
    
}