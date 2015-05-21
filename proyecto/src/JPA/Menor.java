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
import javax.persistence.JoinColumns;
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
@Table(name = "MENOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menor.findAll", query = "SELECT m FROM Menor m"),
    @NamedQuery(name = "Menor.findByCodmenor", query = "SELECT m FROM Menor m WHERE m.codmenor = :codmenor"),
    @NamedQuery(name = "Menor.findByNombre", query = "SELECT m FROM Menor m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Menor.findByApe1", query = "SELECT m FROM Menor m WHERE m.ape1 = :ape1"),
    @NamedQuery(name = "Menor.findByApe2", query = "SELECT m FROM Menor m WHERE m.ape2 = :ape2"),
    @NamedQuery(name = "Menor.findBySexo", query = "SELECT m FROM Menor m WHERE m.sexo = :sexo"),
    @NamedQuery(name = "Menor.findByDni", query = "SELECT m FROM Menor m WHERE m.dni = :dni"),
    @NamedQuery(name = "Menor.findByFechanac", query = "SELECT m FROM Menor m WHERE m.fechanac = :fechanac"),
    @NamedQuery(name = "Menor.findByDiscapacidad", query = "SELECT m FROM Menor m WHERE m.discapacidad = :discapacidad")})
public class Menor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODMENOR")
    private Short codmenor;
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
    @Column(name = "SEXO")
    private String sexo;
    @Column(name = "DNI")
    private String dni;
    @Basic(optional = false)
    @Column(name = "FECHANAC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechanac;
    @Basic(optional = false)
    @Column(name = "DISCAPACIDAD")
    private String discapacidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codmenor")
    private Collection<Inscripcion> inscripcionCollection;
    @JoinColumn(name = "IDCENTRO", referencedColumnName = "IDCENTRO")
    @ManyToOne(optional = false)
    private Centro idcentro;
    @JoinColumns({
        @JoinColumn(name = "IDCALLE", referencedColumnName = "IDCALLE"),
        @JoinColumn(name = "IDVIVIENDA", referencedColumnName = "IDVIVIENDA")})
    @ManyToOne(optional = false)
    private Direccion direccion;

    public Menor() {
    }

    public Menor(Short codmenor) {
        this.codmenor = codmenor;
    }

    public Menor(Short codmenor, String nombre, String ape1, String ape2, String sexo, Date fechanac, String discapacidad) {
        this.codmenor = codmenor;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.sexo = sexo;
        this.fechanac = fechanac;
        this.discapacidad = discapacidad;
    }

    public Short getCodmenor() {
        return codmenor;
    }

    public void setCodmenor(Short codmenor) {
        this.codmenor = codmenor;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechanac() {
        return fechanac;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    @XmlTransient
    public Collection<Inscripcion> getInscripcionCollection() {
        return inscripcionCollection;
    }

    public void setInscripcionCollection(Collection<Inscripcion> inscripcionCollection) {
        this.inscripcionCollection = inscripcionCollection;
    }

    public Centro getIdcentro() {
        return idcentro;
    }

    public void setIdcentro(Centro idcentro) {
        this.idcentro = idcentro;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmenor != null ? codmenor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menor)) {
            return false;
        }
        Menor other = (Menor) object;
        if ((this.codmenor == null && other.codmenor != null) || (this.codmenor != null && !this.codmenor.equals(other.codmenor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Menor[ codmenor=" + codmenor + " ]";
    }
    
}
