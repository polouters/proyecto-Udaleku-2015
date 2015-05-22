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
@Table(name = "CENTRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Centro.findAll", query = "SELECT c FROM Centro c"),
    @NamedQuery(name = "Centro.findByIdcentro", query = "SELECT c FROM Centro c WHERE c.idcentro = :idcentro"),
    @NamedQuery(name = "Centro.findByNombre", query = "SELECT c FROM Centro c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Centro.findByModelo", query = "SELECT c FROM Centro c WHERE c.modelo = :modelo")})
public class Centro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDCENTRO")
    private Short idcentro;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "MODELO")
    private String modelo;
    @JoinColumn(name = "IDPROV", referencedColumnName = "IDPROV")
    @ManyToOne(optional = false)
    private Provincia idprov;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcentro")
    private Collection<Menor> menorCollection;

    public Centro() {
    }

    public Centro(Short idcentro) {
        this.idcentro = idcentro;
    }

    public Centro(Short idcentro, String nombre, String modelo) {
        this.idcentro = idcentro;
        this.nombre = nombre;
        this.modelo = modelo;
    }

    public Short getIdcentro() {
        return idcentro;
    }

    public void setIdcentro(Short idcentro) {
        this.idcentro = idcentro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Provincia getIdprov() {
        return idprov;
    }

    public void setIdprov(Provincia idprov) {
        this.idprov = idprov;
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
        hash += (idcentro != null ? idcentro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Centro)) {
            return false;
        }
        Centro other = (Centro) object;
        if ((this.idcentro == null && other.idcentro != null) || (this.idcentro != null && !this.idcentro.equals(other.idcentro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Centro[ idcentro=" + idcentro + " ]";
    }
    
}
