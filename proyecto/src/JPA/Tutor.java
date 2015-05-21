/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ruben
 */
@Entity
@Table(name = "TUTOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tutor.findAll", query = "SELECT t FROM Tutor t"),
    @NamedQuery(name = "Tutor.findByDni", query = "SELECT t FROM Tutor t WHERE t.dni = :dni"),
    @NamedQuery(name = "Tutor.findByNombre", query = "SELECT t FROM Tutor t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tutor.findByApe1", query = "SELECT t FROM Tutor t WHERE t.ape1 = :ape1"),
    @NamedQuery(name = "Tutor.findByApe2", query = "SELECT t FROM Tutor t WHERE t.ape2 = :ape2"),
    @NamedQuery(name = "Tutor.findByTlf1", query = "SELECT t FROM Tutor t WHERE t.tlf1 = :tlf1"),
    @NamedQuery(name = "Tutor.findByTlf2", query = "SELECT t FROM Tutor t WHERE t.tlf2 = :tlf2"),
    @NamedQuery(name = "Tutor.findByTlf3", query = "SELECT t FROM Tutor t WHERE t.tlf3 = :tlf3"),
    @NamedQuery(name = "Tutor.findByTlf4", query = "SELECT t FROM Tutor t WHERE t.tlf4 = :tlf4")})
public class Tutor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DNI")
    private String dni;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "APE1")
    private String ape1;
    @Basic(optional = false)
    @Column(name = "APE2")
    private String ape2;
    @Basic(optional = false)
    @Column(name = "TLF1")
    private String tlf1;
    @Column(name = "TLF2")
    private String tlf2;
    @Column(name = "TLF3")
    private String tlf3;
    @Column(name = "TLF4")
    private String tlf4;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dni")
    private Collection<Inscripcion> inscripcionCollection;

    public Tutor() {
    }

    public Tutor(String dni) {
        this.dni = dni;
    }

    public Tutor(String dni, String nombre, String ape1, String ape2, String tlf1) {
        this.dni = dni;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.tlf1 = tlf1;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe1() {
        return ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public String getApe2() {
        return ape2;
    }

    public void setApe2(String ape2) {
        this.ape2 = ape2;
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

    @XmlTransient
    public Collection<Inscripcion> getInscripcionCollection() {
        return inscripcionCollection;
    }

    public void setInscripcionCollection(Collection<Inscripcion> inscripcionCollection) {
        this.inscripcionCollection = inscripcionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dni != null ? dni.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tutor)) {
            return false;
        }
        Tutor other = (Tutor) object;
        if ((this.dni == null && other.dni != null) || (this.dni != null && !this.dni.equals(other.dni))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Tutor[ dni=" + dni + " ]";
    }
    
}
