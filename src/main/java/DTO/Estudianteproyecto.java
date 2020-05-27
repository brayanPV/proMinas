/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "estudianteproyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudianteproyecto.findAll", query = "SELECT e FROM Estudianteproyecto e")
    , @NamedQuery(name = "Estudianteproyecto.findByEstudiante", query = "SELECT e FROM Estudianteproyecto e WHERE e.estudianteproyectoPK.estudiante = :estudiante")
    , @NamedQuery(name = "Estudianteproyecto.findByProyecto", query = "SELECT e FROM Estudianteproyecto e WHERE e.estudianteproyectoPK.proyecto = :proyecto")
    , @NamedQuery(name = "Estudianteproyecto.findByCampo", query = "SELECT e FROM Estudianteproyecto e WHERE e.campo = :campo")})
public class Estudianteproyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstudianteproyectoPK estudianteproyectoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "campo")
    private String campo;
    @JoinColumn(name = "estudiante", referencedColumnName = "codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estudiante estudiante1;
    @JoinColumn(name = "proyecto", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proyecto proyecto1;

    public Estudianteproyecto() {
    }

    public Estudianteproyecto(EstudianteproyectoPK estudianteproyectoPK) {
        this.estudianteproyectoPK = estudianteproyectoPK;
    }

    public Estudianteproyecto(EstudianteproyectoPK estudianteproyectoPK, String campo) {
        this.estudianteproyectoPK = estudianteproyectoPK;
        this.campo = campo;
    }

    public Estudianteproyecto(String estudiante, int proyecto) {
        this.estudianteproyectoPK = new EstudianteproyectoPK(estudiante, proyecto);
    }

    public EstudianteproyectoPK getEstudianteproyectoPK() {
        return estudianteproyectoPK;
    }

    public void setEstudianteproyectoPK(EstudianteproyectoPK estudianteproyectoPK) {
        this.estudianteproyectoPK = estudianteproyectoPK;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public Estudiante getEstudiante1() {
        return estudiante1;
    }

    public void setEstudiante1(Estudiante estudiante1) {
        this.estudiante1 = estudiante1;
    }

    public Proyecto getProyecto1() {
        return proyecto1;
    }

    public void setProyecto1(Proyecto proyecto1) {
        this.proyecto1 = proyecto1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estudianteproyectoPK != null ? estudianteproyectoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudianteproyecto)) {
            return false;
        }
        Estudianteproyecto other = (Estudianteproyecto) object;
        if ((this.estudianteproyectoPK == null && other.estudianteproyectoPK != null) || (this.estudianteproyectoPK != null && !this.estudianteproyectoPK.equals(other.estudianteproyectoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Estudianteproyecto[ estudianteproyectoPK=" + estudianteproyectoPK + " ]";
    }
    
}
