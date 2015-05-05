package uml;

public class Vivienda {

	private String numero;
	private String piso;
	private String letra;
	private String mano;

    public Vivienda() {
    }

    public Vivienda(String numero, String piso, String letra, String mano) {
        this.numero = numero;
        this.piso = piso;
        this.letra = letra;
        this.mano = mano;
    }

        
	public String getNumero() {
		return this.numero;
	}

	/**
	 * 
	 * @param numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPiso() {
		return this.piso;
	}

	/**
	 * 
	 * @param piso
	 */
	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getLetra() {
		return this.letra;
	}

	/**
	 * 
	 * @param letra
	 */
	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getMano() {
		return this.mano;
	}

	/**
	 * 
	 * @param mano
	 */
	public void setMano(String mano) {
		this.mano = mano;
	}


}