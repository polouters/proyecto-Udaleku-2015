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
@Table(name = "MUNICIPIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Municipio.findAll", query = "SELECT m FROM Municipio m"),
    @NamedQuery(name = "Municipio.findByIdmunicipio", query = "SELECT m FROM Municipio m WHERE m.idmunicipio = :idmunicipio"),
    @NamedQuery(name = "Municipio.findByNombre", query = "SELECT m FROM Municipio m WHERE m.nombre = :nombre")})
public class Municipio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDMUNICIPIO")
    private Short idmunicipio;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmunicipio")
    private Collection<Calle> calleCollection;
    @JoinColumn(name = "IDPROV", referencedColumnName = "IDPROV")
    @ManyToOne(optional = false)
    private Provincia idprov;

    public Municipio() {
    }

    public Municipio(Short idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public Municipio(Short idmunicipio, String nombre) {
        this.idmunicipio = idmunicipio;
        this.nombre = nombre;
    }

    public Short getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(Short idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Calle> getCalleCollection() {
        return calleCollection;
    }

    public void setCalleCollection(Collection<Calle> calleCollection) {
        this.calleCollection = calleCollection;
    }

    public Provincia getIdprov() {
        return idprov;
    }

    public void setIdprov(Provincia idprov) {
        this.idprov = idprov;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmunicipio != null ? idmunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) object;
        if ((this.idmunicipio == null && other.idmunicipio != null) || (this.idmunicipio != null && !this.idmunicipio.equals(other.idmunicipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Municipio[ idmunicipio=" + idmunicipio + " ]";
    }
    
}
