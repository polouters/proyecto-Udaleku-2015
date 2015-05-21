/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ruben
 */
@Entity
@Table(name = "INSCRIPCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inscripcion.findAll", query = "SELECT i FROM Inscripcion i"),
    @NamedQuery(name = "Inscripcion.findByIdins", query = "SELECT i FROM Inscripcion i WHERE i.idins = :idins")})
public class Inscripcion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDINS")
    private Short idins;
    @JoinColumn(name = "CODMENOR", referencedColumnName = "CODMENOR")
    @ManyToOne(optional = false)
    private Menor codmenor;
    @JoinColumn(name = "NSOLICITUD", referencedColumnName = "NSOLICITUD")
    @ManyToOne(optional = false)
    private Solicitud nsolicitud;
    @JoinColumn(name = "DNI", referencedColumnName = "DNI")
    @ManyToOne(optional = false)
    private Tutor dni;

    public Inscripcion() {
    }

    public Inscripcion(Short idins) {
        this.idins = idins;
    }

    public Short getIdins() {
        return idins;
    }

    public void setIdins(Short idins) {
        this.idins = idins;
    }

    public Menor getCodmenor() {
        return codmenor;
    }

    public void setCodmenor(Menor codmenor) {
        this.codmenor = codmenor;
    }

    public Solicitud getNsolicitud() {
        return nsolicitud;
    }

    public void setNsolicitud(Solicitud nsolicitud) {
        this.nsolicitud = nsolicitud;
    }

    public Tutor getDni() {
        return dni;
    }

    public void setDni(Tutor dni) {
        this.dni = dni;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idins != null ? idins.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inscripcion)) {
            return false;
        }
        Inscripcion other = (Inscripcion) object;
        if ((this.idins == null && other.idins != null) || (this.idins != null && !this.idins.equals(other.idins))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Inscripcion[ idins=" + idins + " ]";
    }
    
}
