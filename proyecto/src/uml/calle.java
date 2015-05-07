package uml;

import java.util.ArrayList;

public class calle {

	private String nombre;
        private ArrayList<municipio> lMunicipio = new ArrayList();
        private ArrayList<direccion> lDireccion = new ArrayList();

    public calle() {
    }

    public calle(String nombre, ArrayList<municipio> lMunicipio, ArrayList<direccion> lDireccion) {
        this.nombre = nombre;
        this.lMunicipio = lMunicipio;
        this.lDireccion = lDireccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<municipio> getlMunicipio() {
        return lMunicipio;
    }

    public void setlMunicipio(ArrayList<municipio> lMunicipio) {
        this.lMunicipio = lMunicipio;
    }

    public ArrayList<direccion> getlDireccion() {
        return lDireccion;
    }

    public void setlDireccion(ArrayList<direccion> lDireccion) {
        this.lDireccion = lDireccion;
    }
       
    
    
    
        
}