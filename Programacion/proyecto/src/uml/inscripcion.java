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
public class inscripcion {

	
        private menor menor;
        private tutor tutor;

    public inscripcion() {
    }

    public inscripcion(solicitud slc, menor menor, tutor tutor) {
    
        this.menor = menor;
        this.tutor = tutor;
    }
    public menor getMenor() {
        return menor;
    }

    public void setMenor(menor menor) {
        this.menor = menor;
    }

    public tutor getTutor() {
        return tutor;
    }

    public void setTutor(tutor tutor) {
        this.tutor = tutor;
    }




}