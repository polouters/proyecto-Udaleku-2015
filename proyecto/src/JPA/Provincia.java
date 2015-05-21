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
@Table(name = "PROVINCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provincia.findAll", query = "SELECT p FROM Provincia p"),
    @NamedQuery(name = "Provincia.findByIdprov", query = "SELECT p FROM Provincia p WHERE p.idprov = :idprov"),
    @NamedQuery(name = "Provincia.findByNombre", query = "SELECT p FROM Provincia p WHERE p.nombre = :nombre")})
public class Provincia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDPROV")
    private Short idprov;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprov")
    private Collection<Centro> centroCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprov")
    private Collection<Municipio> municipioCollection;

    public Provincia() {
    }

    public Provincia(Short idprov) {
        this.idprov = idprov;
    }

    public Provincia(Short idprov, String nombre) {
        this.idprov = idprov;
        this.nombre = nombre;
    }

    public Short getIdprov() {
        return idprov;
    }

    public void setIdprov(Short idprov) {
        this.idprov = idprov;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Centro> getCentroCollection() {
        return centroCollection;
    }

    public void setCentroCollection(Collection<Centro> centroCollection) {
        this.centroCollection = centroCollection;
    }

    @XmlTransient
    public Collection<Municipio> getMunicipioCollection() {
        return municipioCollection;
    }

    public void setMunicipioCollection(Collection<Municipio> municipioCollection) {
        this.municipioCollection = municipioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprov != null ? idprov.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provincia)) {
            return false;
        }
        Provincia other = (Provincia) object;
        if ((this.idprov == null && other.idprov != null) || (this.idprov != null && !this.idprov.equals(other.idprov))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Provincia[ idprov=" + idprov + " ]";
    }
    
}
