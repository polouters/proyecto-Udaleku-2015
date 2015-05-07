package uml;

import java.util.ArrayList;

public class municipio {

	private String nombre;
        private provincia prov;
        private ArrayList <calle> lCalle = new ArrayList();

    public municipio() {
    }

    public municipio(String nombre, provincia prov,ArrayList <calle> lCalle) {
        this.nombre = nombre;
        this.prov = prov;
        this.lCalle = lCalle;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public provincia getProv() {
        return prov;
    }

    public void setProv(provincia prov) {
        this.prov = prov;
    }

    public ArrayList<calle> getlCalle() {
        return lCalle;
    }

    public void setlCalle(ArrayList<calle> lCalle) {
        this.lCalle = lCalle;
    }
      
    
        

}