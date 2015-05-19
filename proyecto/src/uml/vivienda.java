package uml;

public class vivienda {

	private String numero;
	private String piso;
	private String letra;
	private String mano;
        private String escalera;

    public vivienda() {
    }

    public vivienda(String numero, String piso, String letra, String mano, String escalera) {
        this.numero = numero;
        this.piso = piso;
        this.letra = letra;
        this.mano = mano;
        this.escalera = escalera;
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

    public String getEscalera() {
        return escalera;
    }

    public void setEscalera(String escalera) {
        this.escalera = escalera;
    }
        
 

}