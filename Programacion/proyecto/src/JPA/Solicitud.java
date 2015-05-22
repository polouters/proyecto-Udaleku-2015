/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ruben
 */
@Entity
@Table(name = "SOLICITUD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s"),
    @NamedQuery(name = "Solicitud.findByNsolicitud", query = "SELECT s FROM Solicitud s WHERE s.nsolicitud = :nsolicitud"),
    @NamedQuery(name = "Solicitud.findByFecha", query = "SELECT s FROM Solicitud s WHERE s.fecha = :fecha"),
    @NamedQuery(name = "Solicitud.findByHora", query = "SELECT s FROM Solicitud s WHERE s.hora = :hora"),
    @NamedQuery(name = "Solicitud.findBySituacion", query = "SELECT s FROM Solicitud s WHERE s.situacion = :situacion"),
    @NamedQuery(name = "Solicitud.findByNorden", query = "SELECT s FROM Solicitud s WHERE s.norden = :norden")})
public class Solicitud implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NSOLICITUD")
    private Short nsolicitud;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;
    @Column(name = "SITUACION")
    private String situacion;
    @Column(name = "NORDEN")
    private Short norden;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nsolicitud")
    private Collection<Inscripcion> inscripcionCollection;
    @JoinColumn(name = "IDSORTEO", referencedColumnName = "IDSORTEO")
    @ManyToOne(optional = false)
    private Sorteo idsorteo;

    public Solicitud() {
    }

    public Solicitud(Short nsolicitud) {
        this.nsolicitud = nsolicitud;
    }

    public Short getNsolicitud() {
        return nsolicitud;
    }

    public void setNsolicitud(Short nsolicitud) {
        this.nsolicitud = nsolicitud;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public Short getNorden() {
        return norden;
    }

    public void setNorden(Short norden) {
        this.norden = norden;
    }

    @XmlTransient
    public Collection<Inscripcion> getInscripcionCollection() {
        return inscripcionCollection;
    }

    public void setInscripcionCollection(Collection<Inscripcion> inscripcionCollection) {
        this.inscripcionCollection = inscripcionCollection;
    }

    public Sorteo getIdsorteo() {
        return idsorteo;
    }

    public void setIdsorteo(Sorteo idsorteo) {
        this.idsorteo = idsorteo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nsolicitud != null ? nsolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.nsolicitud == null && other.nsolicitud != null) || (this.nsolicitud != null && !this.nsolicitud.equals(other.nsolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Solicitud[ nsolicitud=" + nsolicitud + " ]";
    }
    
}
