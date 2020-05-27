/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author stive
 */
@Embeddable
public class AsesorPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "proyecto")
    private int proyecto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "docente")
    private String docente;

    public AsesorPK() {
    }

    public AsesorPK(int proyecto, String docente) {
        this.proyecto = proyecto;
        this.docente = docente;
    }

    public int getProyecto() {
        return proyecto;
    }

    public void setProyecto(int proyecto) {
        this.proyecto = proyecto;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) proyecto;
        hash += (docente != null ? docente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsesorPK)) {
            return false;
        }
        AsesorPK other = (AsesorPK) object;
        if (this.proyecto != other.proyecto) {
            return false;
        }
        if ((this.docente == null && other.docente != null) || (this.docente != null && !this.docente.equals(other.docente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.AsesorPK[ proyecto=" + proyecto + ", docente=" + docente + " ]";
    }
    
}
