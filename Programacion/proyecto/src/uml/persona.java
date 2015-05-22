package uml;
/**
 *
 * 
 * @author Ruben
 * @author Mikel
 * @author Jon
 * @version Beta 1.0
 * @since Early May
 * 
 */
public abstract class persona {

	private String nombre;
	private String ape1;
	private String ape2;

    public persona() {
    }

        
    public persona(String nombre, String ape1, String ape2) {
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
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

	public String getApe1() {
		return this.ape1;
	}

	/**
	 * 
	 * @param ape1
	 */
	public void setApe1(String ape1) {
		this.ape1 = ape1;
	}

	public String getApe2() {
		return this.ape2;
	}

	/**
	 * 
	 * @param ape2
	 */
	public void setApe2(String ape2) {
		this.ape2 = ape2;
	}

}