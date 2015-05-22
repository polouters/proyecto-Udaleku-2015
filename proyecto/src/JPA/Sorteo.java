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
@Table(name = "SORTEO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sorteo.findAll", query = "SELECT s FROM Sorteo s"),
    @NamedQuery(name = "Sorteo.findByIdsorteo", query = "SELECT s FROM Sorteo s WHERE s.idsorteo = :idsorteo"),
    @NamedQuery(name = "Sorteo.findByDiainicio", query = "SELECT s FROM Sorteo s WHERE s.diainicio = :diainicio"),
    @NamedQuery(name = "Sorteo.findByDiafin", query = "SELECT s FROM Sorteo s WHERE s.diafin = :diafin"),
    @NamedQuery(name = "Sorteo.findByDiasorteo", query = "SELECT s FROM Sorteo s WHERE s.diasorteo = :diasorteo")})
public class Sorteo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDSORTEO")
    private Short idsorteo;
    @Basic(optional = false)
    @Column(name = "DIAINICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date diainicio;
    @Basic(optional = false)
    @Column(name = "DIAFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date diafin;
    @Basic(optional = false)
    @Column(name = "DIASORTEO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date diasorteo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsorteo")
    private Collection<Solicitud> solicitudCollection;

    public Sorteo() {
    }

    public Sorteo(Short idsorteo) {
        this.idsorteo = idsorteo;
    }

    public Sorteo(Short idsorteo, Date diainicio, Date diafin, Date diasorteo) {
        this.idsorteo = idsorteo;
        this.diainicio = diainicio;
        this.diafin = diafin;
        this.diasorteo = diasorteo;
    }

    public Short getIdsorteo() {
        return idsorteo;
    }

    public void setIdsorteo(Short idsorteo) {
        this.idsorteo = idsorteo;
    }

    public Date getDiainicio() {
        return diainicio;
    }

    public void setDiainicio(Date diainicio) {
        this.diainicio = diainicio;
    }

    public Date getDiafin() {
        return diafin;
    }

    public void setDiafin(Date diafin) {
        this.diafin = diafin;
    }

    public Date getDiasorteo() {
        return diasorteo;
    }

    public void setDiasorteo(Date diasorteo) {
        this.diasorteo = diasorteo;
    }

    @XmlTransient
    public Collection<Solicitud> getSolicitudCollection() {
        return solicitudCollection;
    }

    public void setSolicitudCollection(Collection<Solicitud> solicitudCollection) {
        this.solicitudCollection = solicitudCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsorteo != null ? idsorteo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sorteo)) {
            return false;
        }
        Sorteo other = (Sorteo) object;
        if ((this.idsorteo == null && other.idsorteo != null) || (this.idsorteo != null && !this.idsorteo.equals(other.idsorteo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Sorteo[ idsorteo=" + idsorteo + " ]";
    }
    
}
