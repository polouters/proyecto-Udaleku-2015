/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Ruben
 */
@Embeddable
public class DireccionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "IDCALLE")
    private short idcalle;
    @Basic(optional = false)
    @Column(name = "IDVIVIENDA")
    private short idvivienda;

    public DireccionPK() {
    }

    public DireccionPK(short idcalle, short idvivienda) {
        this.idcalle = idcalle;
        this.idvivienda = idvivienda;
    }

    public short getIdcalle() {
        return idcalle;
    }

    public void setIdcalle(short idcalle) {
        this.idcalle = idcalle;
    }

    public short getIdvivienda() {
        return idvivienda;
    }

    public void setIdvivienda(short idvivienda) {
        this.idvivienda = idvivienda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idcalle;
        hash += (int) idvivienda;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DireccionPK)) {
            return false;
        }
        DireccionPK other = (DireccionPK) object;
        if (this.idcalle != other.idcalle) {
            return false;
        }
        if (this.idvivienda != other.idvivienda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.DireccionPK[ idcalle=" + idcalle + ", idvivienda=" + idvivienda + " ]";
    }
    
}
