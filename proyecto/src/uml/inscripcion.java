package uml;

public class inscripcion {

	private int idIns;
        private solicitud slc;
        private menor menor;
        private tutor tutor;

    public inscripcion() {
    }

    public inscripcion(int idIns, solicitud slc, menor menor, tutor tutor) {
        this.idIns = idIns;
        this.slc = slc;
        this.menor = menor;
        this.tutor = tutor;
    }

    public int getIdIns() {
        return idIns;
    }

    public void setIdIns(int idIns) {
        this.idIns = idIns;
    }

    public solicitud getSlc() {
        return slc;
    }

    public void setSlc(solicitud slc) {
        this.slc = slc;
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