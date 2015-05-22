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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "DIRECCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Direccion.findAll", query = "SELECT d FROM Direccion d"),
    @NamedQuery(name = "Direccion.findByIdcalle", query = "SELECT d FROM Direccion d WHERE d.direccionPK.idcalle = :idcalle"),
    @NamedQuery(name = "Direccion.findByIdvivienda", query = "SELECT d FROM Direccion d WHERE d.direccionPK.idvivienda = :idvivienda"),
    @NamedQuery(name = "Direccion.findByCp", query = "SELECT d FROM Direccion d WHERE d.cp = :cp")})
public class Direccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DireccionPK direccionPK;
    @Basic(optional = false)
    @Column(name = "CP")
    private String cp;
    @JoinColumn(name = "IDCALLE", referencedColumnName = "IDCALLE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Calle calle;
    @JoinColumn(name = "IDVIVIENDA", referencedColumnName = "IDVIVIENDA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vivienda vivienda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "direccion")
    private Collection<Menor> menorCollection;

    public Direccion() {
    }

    public Direccion(DireccionPK direccionPK) {
        this.direccionPK = direccionPK;
    }

    public Direccion(DireccionPK direccionPK, String cp) {
        this.direccionPK = direccionPK;
        this.cp = cp;
    }

    public Direccion(short idcalle, short idvivienda) {
        this.direccionPK = new DireccionPK(idcalle, idvivienda);
    }

    public DireccionPK getDireccionPK() {
        return direccionPK;
    }

    public void setDireccionPK(DireccionPK direccionPK) {
        this.direccionPK = direccionPK;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Calle getCalle() {
        return calle;
    }

    public void setCalle(Calle calle) {
        this.calle = calle;
    }

    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }

    @XmlTransient
    public Collection<Menor> getMenorCollection() {
        return menorCollection;
    }

    public void setMenorCollection(Collection<Menor> menorCollection) {
        this.menorCollection = menorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (direccionPK != null ? direccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Direccion)) {
            return false;
        }
        Direccion other = (Direccion) object;
        if ((this.direccionPK == null && other.direccionPK != null) || (this.direccionPK != null && !this.direccionPK.equals(other.direccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Direccion[ direccionPK=" + direccionPK + " ]";
    }
    
}
