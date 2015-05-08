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
@Table(name = "VIVIENDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vivienda.findAll", query = "SELECT v FROM Vivienda v"),
    @NamedQuery(name = "Vivienda.findByIdvivienda", query = "SELECT v FROM Vivienda v WHERE v.idvivienda = :idvivienda"),
    @NamedQuery(name = "Vivienda.findByNumero", query = "SELECT v FROM Vivienda v WHERE v.numero = :numero"),
    @NamedQuery(name = "Vivienda.findByPiso", query = "SELECT v FROM Vivienda v WHERE v.piso = :piso"),
    @NamedQuery(name = "Vivienda.findByLetra", query = "SELECT v FROM Vivienda v WHERE v.letra = :letra"),
    @NamedQuery(name = "Vivienda.findByMano", query = "SELECT v FROM Vivienda v WHERE v.mano = :mano")})
public class Vivienda implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDVIVIENDA")
    private BigDecimal idvivienda;
    @Basic(optional = false)
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "PISO")
    private String piso;
    @Column(name = "LETRA")
    private String letra;
    @Column(name = "MANO")
    private String mano;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vivienda")
    private Collection<Direccion> direccionCollection;

    public Vivienda() {
    }

    public Vivienda(BigDecimal idvivienda) {
        this.idvivienda = idvivienda;
    }

    public Vivienda(BigDecimal idvivienda, String numero) {
        this.idvivienda = idvivienda;
        this.numero = numero;
    }

    public BigDecimal getIdvivienda() {
        return idvivienda;
    }

    public void setIdvivienda(BigDecimal idvivienda) {
        this.idvivienda = idvivienda;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getMano() {
        return mano;
    }

    public void setMano(String mano) {
        this.mano = mano;
    }

    @XmlTransient
    public Collection<Direccion> getDireccionCollection() {
        return direccionCollection;
    }

    public void setDireccionCollection(Collection<Direccion> direccionCollection) {
        this.direccionCollection = direccionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvivienda != null ? idvivienda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vivienda)) {
            return false;
        }
        Vivienda other = (Vivienda) object;
        if ((this.idvivienda == null && other.idvivienda != null) || (this.idvivienda != null && !this.idvivienda.equals(other.idvivienda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bd.Vivienda[ idvivienda=" + idvivienda + " ]";
    }
    
}
