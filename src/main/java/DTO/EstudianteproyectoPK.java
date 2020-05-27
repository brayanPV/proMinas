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
public class EstudianteproyectoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "estudiante")
    private String estudiante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proyecto")
    private int proyecto;

    public EstudianteproyectoPK() {
    }

    public EstudianteproyectoPK(String estudiante, int proyecto) {
        this.estudiante = estudiante;
        this.proyecto = proyecto;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public int getProyecto() {
        return proyecto;
    }

    public void setProyecto(int proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estudiante != null ? estudiante.hashCode() : 0);
        hash += (int) proyecto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudianteproyectoPK)) {
            return false;
        }
        EstudianteproyectoPK other = (EstudianteproyectoPK) object;
        if ((this.estudiante == null && other.estudiante != null) || (this.estudiante != null && !this.estudiante.equals(other.estudiante))) {
            return false;
        }
        if (this.proyecto != other.proyecto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.EstudianteproyectoPK[ estudiante=" + estudiante + ", proyecto=" + proyecto + " ]";
    }
    
}
