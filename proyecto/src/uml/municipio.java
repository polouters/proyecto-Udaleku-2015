package uml;

import java.util.ArrayList;

public class municipio {

	private String nombre;
        private ArrayList<calle> ListaCalles = new ArrayList();

    
        

    public municipio() {
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<calle> getListaCalles() {
        return ListaCalles;
    }

    public void setListaCalles(ArrayList<calle> ListaCalles) {
        this.ListaCalles = ListaCalles;
    }

      
    
        

}