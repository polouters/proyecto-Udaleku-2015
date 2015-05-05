package uml;

public class centro {

	private String nombre;
	private String modelo;

    public centro() {
    }

    public centro(String nombre, String modelo) {
        this.nombre = nombre;
        this.modelo = modelo;
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

	public String getModelo() {
		return this.modelo;
	}

	/**
	 * 
	 * @param modelo
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}



}