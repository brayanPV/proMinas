/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "asesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asesor.findAll", query = "SELECT a FROM Asesor a")
    , @NamedQuery(name = "Asesor.findByProyecto", query = "SELECT a FROM Asesor a WHERE a.asesorPK.proyecto = :proyecto")
    , @NamedQuery(name = "Asesor.findByDocente", query = "SELECT a FROM Asesor a WHERE a.asesorPK.docente = :docente")})
public class Asesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AsesorPK asesorPK;
    @JoinColumn(name = "docente", referencedColumnName = "codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Docente docente1;
    @JoinColumn(name = "proyecto", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proyecto proyecto1;
    @JoinColumn(name = "tipo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tipoasesor tipo;

    public Asesor() {
    }

    public Asesor(AsesorPK asesorPK) {
        this.asesorPK = asesorPK;
    }

    public Asesor(int proyecto, String docente) {
        this.asesorPK = new AsesorPK(proyecto, docente);
    }

    public AsesorPK getAsesorPK() {
        return asesorPK;
    }

    public void setAsesorPK(AsesorPK asesorPK) {
        this.asesorPK = asesorPK;
    }

    public Docente getDocente1() {
        return docente1;
    }

    public void setDocente1(Docente docente1) {
        this.docente1 = docente1;
    }

    public Proyecto getProyecto1() {
        return proyecto1;
    }

    public void setProyecto1(Proyecto proyecto1) {
        this.proyecto1 = proyecto1;
    }

    public Tipoasesor getTipo() {
        return tipo;
    }

    public void setTipo(Tipoasesor tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asesorPK != null ? asesorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asesor)) {
            return false;
        }
        Asesor other = (Asesor) object;
        if ((this.asesorPK == null && other.asesorPK != null) || (this.asesorPK != null && !this.asesorPK.equals(other.asesorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Asesor[ asesorPK=" + asesorPK + " ]";
    }
    
}
