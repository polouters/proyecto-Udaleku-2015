package uml;

public class vivienda {

	private String numero;
	private String piso;
	private String letra;
	private String mano;

    public vivienda() {
    }

    public vivienda(String numero, String piso, String letra, String mano, direccion drc) {
        this.numero = numero;
        this.piso = piso;
        this.letra = letra;
        this.mano = mano;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getMano() {
        return mano;
    }

    public void setMano(String mano) {
        this.mano = mano;
    }
        
 

}