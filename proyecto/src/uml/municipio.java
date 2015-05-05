package uml;

public class municipio {

	private String nombre;

    public municipio() {
    }

    public municipio(String nombre) {
        this.nombre = nombre;
    }
        
        
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}