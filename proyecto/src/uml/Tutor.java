package uml;

public class Tutor extends persona {

	private String dni;
	private String tlf1;
	private String tlf2;
	private String tlf3;
	private String tlf4;

    public Tutor() {
    }

    public Tutor(String dni, String tlf1, String tlf2, String tlf3, String tlf4) {
        this.dni = dni;
        this.tlf1 = tlf1;
        this.tlf2 = tlf2;
        this.tlf3 = tlf3;
        this.tlf4 = tlf4;
    }

        
        
	public String getDni() {
		return this.dni;
	}

	/**
	 * 
	 * @param dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTlf1() {
		return this.tlf1;
	}

	/**
	 * 
	 * @param tlf1
	 */
	public void setTlf1(String tlf1) {
		this.tlf1 = tlf1;
	}

	public String getTlf2() {
		return this.tlf2;
	}

	/**
	 * 
	 * @param tlf2
	 */
	public void setTlf2(String tlf2) {
		this.tlf2 = tlf2;
	}

	public String getTlf3() {
		return this.tlf3;
	}

	/**
	 * 
	 * @param tlf3
	 */
	public void setTlf3(String tlf3) {
		this.tlf3 = tlf3;
	}

	public String getTlf4() {
		return this.tlf4;
	}

	/**
	 * 
	 * @param tlf4
	 */
	public void setTlf4(String tlf4) {
		this.tlf4 = tlf4;
	}

	

}