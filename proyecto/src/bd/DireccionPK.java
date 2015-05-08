/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author jon
 */
@Embeddable
public class DireccionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "IDCALLE")
    private BigInteger idcalle;
    @Basic(optional = false)
    @Column(name = "IDVIVIENDA")
    private BigInteger idvivienda;

    public DireccionPK() {
    }

    public DireccionPK(BigInteger idcalle, BigInteger idvivienda) {
        this.idcalle = idcalle;
        this.idvivienda = idvivienda;
    }

    public BigInteger getIdcalle() {
        return idcalle;
    }

    public void setIdcalle(BigInteger idcalle) {
        this.idcalle = idcalle;
    }

    public BigInteger getIdvivienda() {
        return idvivienda;
    }

    public void setIdvivienda(BigInteger idvivienda) {
        this.idvivienda = idvivienda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcalle != null ? idcalle.hashCode() : 0);
        hash += (idvivienda != null ? idvivienda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DireccionPK)) {
            return false;
        }
        DireccionPK other = (DireccionPK) object;
        if ((this.idcalle == null && other.idcalle != null) || (this.idcalle != null && !this.idcalle.equals(other.idcalle))) {
            return false;
        }
        if ((this.idvivienda == null && other.idvivienda != null) || (this.idvivienda != null && !this.idvivienda.equals(other.idvivienda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bd.DireccionPK[ idcalle=" + idcalle + ", idvivienda=" + idvivienda + " ]";
    }
    
}
