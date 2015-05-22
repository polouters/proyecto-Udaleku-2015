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
public class tutor extends persona {

	private String dni;
	private String tlf1;
	private String tlf2;
	private String tlf3;
	private String tlf4;
        private inscripcion incr;

    public tutor() {
    }

    public tutor(String dni, String tlf1, String tlf2, String tlf3, String tlf4, inscripcion incr, String nombre, String ape1, String ape2) {
        super(nombre, ape1, ape2);
        this.dni = dni;
        this.tlf1 = tlf1;
        this.tlf2 = tlf2;
        this.tlf3 = tlf3;
        this.tlf4 = tlf4;
        this.incr = incr;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTlf1() {
        return tlf1;
    }

    public void setTlf1(String tlf1) {
        this.tlf1 = tlf1;
    }

    public String getTlf2() {
        return tlf2;
    }

    public void setTlf2(String tlf2) {
        this.tlf2 = tlf2;
    }

    public String getTlf3() {
        return tlf3;
    }

    public void setTlf3(String tlf3) {
        this.tlf3 = tlf3;
    }

    public String getTlf4() {
        return tlf4;
    }

    public void setTlf4(String tlf4) {
        this.tlf4 = tlf4;
    }

    public inscripcion getIncr() {
        return incr;
    }

    public void setIncr(inscripcion incr) {
        this.incr = incr;
    }

   
}