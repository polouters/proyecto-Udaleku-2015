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
import java.util.ArrayList;

public class direccion {

	private String cp;
        private calle calle;
        private vivienda vivienda;
        private ArrayList <menor> lMenor = new ArrayList();

    public direccion() {
    }

    public direccion(String cp, calle calle, vivienda vivienda,ArrayList <menor> lMenor) {
        this.cp = cp;
        this.calle = calle;
        this.vivienda = vivienda;
        this.lMenor = lMenor;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public calle getCalle() {
        return calle;
    }

    public void setCalle(calle calle) {
        this.calle = calle;
    }

    public vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(vivienda vivienda) {
        this.vivienda = vivienda;
    }

    public ArrayList<menor> getlMenor() {
        return lMenor;
    }
    public void setMenor (menor m){
        lMenor.add(m);
    }


}