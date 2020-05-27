/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "juradoanteproyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Juradoanteproyecto.findAll", query = "SELECT j FROM Juradoanteproyecto j")
    , @NamedQuery(name = "Juradoanteproyecto.findByDocente", query = "SELECT j FROM Juradoanteproyecto j WHERE j.juradoanteproyectoPK.docente = :docente")
    , @NamedQuery(name = "Juradoanteproyecto.findByProyecto", query = "SELECT j FROM Juradoanteproyecto j WHERE j.juradoanteproyectoPK.proyecto = :proyecto")
    , @NamedQuery(name = "Juradoanteproyecto.findByObservacion", query = "SELECT j FROM Juradoanteproyecto j WHERE j.observacion = :observacion")
    , @NamedQuery(name = "Juradoanteproyecto.findByNota", query = "SELECT j FROM Juradoanteproyecto j WHERE j.nota = :nota")
    , @NamedQuery(name = "Juradoanteproyecto.findByFechaasignacion", query = "SELECT j FROM Juradoanteproyecto j WHERE j.fechaasignacion = :fechaasignacion")
    , @NamedQuery(name = "Juradoanteproyecto.findByFechanota", query = "SELECT j FROM Juradoanteproyecto j WHERE j.fechanota = :fechanota")})
public class Juradoanteproyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JuradoanteproyectoPK juradoanteproyectoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nota")
    private int nota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaasignacion")
    @Temporal(TemporalType.DATE)
    private Date fechaasignacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechanota")
    @Temporal(TemporalType.DATE)
    private Date fechanota;
    @JoinColumn(name = "docente", referencedColumnName = "codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Docente docente1;
    @JoinColumn(name = "proyecto", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proyecto proyecto1;

    public Juradoanteproyecto() {
    }

    public Juradoanteproyecto(JuradoanteproyectoPK juradoanteproyectoPK) {
        this.juradoanteproyectoPK = juradoanteproyectoPK;
    }

    public Juradoanteproyecto(JuradoanteproyectoPK juradoanteproyectoPK, String observacion, int nota, Date fechaasignacion, Date fechanota) {
        this.juradoanteproyectoPK = juradoanteproyectoPK;
        this.observacion = observacion;
        this.nota = nota;
        this.fechaasignacion = fechaasignacion;
        this.fechanota = fechanota;
    }

    public Juradoanteproyecto(String docente, int proyecto) {
        this.juradoanteproyectoPK = new JuradoanteproyectoPK(docente, proyecto);
    }

    public JuradoanteproyectoPK getJuradoanteproyectoPK() {
        return juradoanteproyectoPK;
    }

    public void setJuradoanteproyectoPK(JuradoanteproyectoPK juradoanteproyectoPK) {
        this.juradoanteproyectoPK = juradoanteproyectoPK;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Date getFechaasignacion() {
        return fechaasignacion;
    }

    public void setFechaasignacion(Date fechaasignacion) {
        this.fechaasignacion = fechaasignacion;
    }

    public Date getFechanota() {
        return fechanota;
    }

    public void setFechanota(Date fechanota) {
        this.fechanota = fechanota;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (juradoanteproyectoPK != null ? juradoanteproyectoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Juradoanteproyecto)) {
            return false;
        }
        Juradoanteproyecto other = (Juradoanteproyecto) object;
        if ((this.juradoanteproyectoPK == null && other.juradoanteproyectoPK != null) || (this.juradoanteproyectoPK != null && !this.juradoanteproyectoPK.equals(other.juradoanteproyectoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Juradoanteproyecto[ juradoanteproyectoPK=" + juradoanteproyectoPK + " ]";
    }
    
}
