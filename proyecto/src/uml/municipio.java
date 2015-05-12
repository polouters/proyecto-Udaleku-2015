package uml;

import java.util.ArrayList;

public class municipio {

	private String nombre;
        private provincia prov;

    public municipio() {
    }

    public municipio(String nombre, provincia prov,ArrayList <calle> lCalle) {
        this.nombre = nombre;
        this.prov = prov;
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

      
    
        

}