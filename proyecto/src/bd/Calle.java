/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jon
 */
@Entity
@Table(name = "CALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calle.findAll", query = "SELECT c FROM Calle c"),
    @NamedQuery(name = "Calle.findByIdcalle", query = "SELECT c FROM Calle c WHERE c.idcalle = :idcalle"),
    @NamedQuery(name = "Calle.findByNombre", query = "SELECT c FROM Calle c WHERE c.nombre = :nombre")})
public class Calle implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDCALLE")
    private BigDecimal idcalle;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calle")
    private Collection<Direccion> direccionCollection;
    @JoinColumn(name = "IDMUNICIPIO", referencedColumnName = "IDMUNICIPIO")
    @ManyToOne(optional = false)
    private Municipio idmunicipio;

    public Calle() {
    }

    public Calle(BigDecimal idcalle) {
        this.idcalle = idcalle;
    }

    public Calle(BigDecimal idcalle, String nombre) {
        this.idcalle = idcalle;
        this.nombre = nombre;
    }

    public BigDecimal getIdcalle() {
        return idcalle;
    }

    public void setIdcalle(BigDecimal idcalle) {
        this.idcalle = idcalle;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Direccion> getDireccionCollection() {
        return direccionCollection;
    }

    public void setDireccionCollection(Collection<Direccion> direccionCollection) {
        this.direccionCollection = direccionCollection;
    }

    public Municipio getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(Municipio idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcalle != null ? idcalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calle)) {
            return false;
        }
        Calle other = (Calle) object;
        if ((this.idcalle == null && other.idcalle != null) || (this.idcalle != null && !this.idcalle.equals(other.idcalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bd.Calle[ idcalle=" + idcalle + " ]";
    }
    
}
