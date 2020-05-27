/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p")
    , @NamedQuery(name = "Proyecto.findByDirector", query = "SELECT p FROM Proyecto p WHERE p.director = :director")
    , @NamedQuery(name = "Proyecto.findById", query = "SELECT p FROM Proyecto p WHERE p.id = :id")
    , @NamedQuery(name = "Proyecto.findByTitulo", query = "SELECT p FROM Proyecto p WHERE p.titulo = :titulo")
    , @NamedQuery(name = "Proyecto.findByFechainicio", query = "SELECT p FROM Proyecto p WHERE p.fechainicio = :fechainicio")
    , @NamedQuery(name = "Proyecto.findByFechafin", query = "SELECT p FROM Proyecto p WHERE p.fechafin = :fechafin")
    , @NamedQuery(name = "Proyecto.findByResumen", query = "SELECT p FROM Proyecto p WHERE p.resumen = :resumen")
    , @NamedQuery(name = "Proyecto.findByFechaanteproyecto", query = "SELECT p FROM Proyecto p WHERE p.fechaanteproyecto = :fechaanteproyecto")
    , @NamedQuery(name = "Proyecto.findByFechaproyecto", query = "SELECT p FROM Proyecto p WHERE p.fechaproyecto = :fechaproyecto")})
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "director")
    private String director;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "resumen")
    private String resumen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaanteproyecto")
    @Temporal(TemporalType.DATE)
    private Date fechaanteproyecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaproyecto")
    @Temporal(TemporalType.DATE)
    private Date fechaproyecto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto1")
    private List<Estudianteproyecto> estudianteproyectoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto1")
    private List<Juradoanteproyecto> juradoanteproyectoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto1")
    private List<Asesor> asesorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto1")
    private List<Juradoproyecto> juradoproyectoList;

    public Proyecto() {
    }

    public Proyecto(Integer id) {
        this.id = id;
    }

    public Proyecto(Integer id, String director, String titulo, Date fechainicio, Date fechafin, String resumen, Date fechaanteproyecto, Date fechaproyecto) {
        this.id = id;
        this.director = director;
        this.titulo = titulo;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
        this.resumen = resumen;
        this.fechaanteproyecto = fechaanteproyecto;
        this.fechaproyecto = fechaproyecto;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public Date getFechaanteproyecto() {
        return fechaanteproyecto;
    }

    public void setFechaanteproyecto(Date fechaanteproyecto) {
        this.fechaanteproyecto = fechaanteproyecto;
    }

    public Date getFechaproyecto() {
        return fechaproyecto;
    }

    public void setFechaproyecto(Date fechaproyecto) {
        this.fechaproyecto = fechaproyecto;
    }

    @XmlTransient
    public List<Estudianteproyecto> getEstudianteproyectoList() {
        return estudianteproyectoList;
    }

    public void setEstudianteproyectoList(List<Estudianteproyecto> estudianteproyectoList) {
        this.estudianteproyectoList = estudianteproyectoList;
    }

    @XmlTransient
    public List<Juradoanteproyecto> getJuradoanteproyectoList() {
        return juradoanteproyectoList;
    }

    public void setJuradoanteproyectoList(List<Juradoanteproyecto> juradoanteproyectoList) {
        this.juradoanteproyectoList = juradoanteproyectoList;
    }

    @XmlTransient
    public List<Asesor> getAsesorList() {
        return asesorList;
    }

    public void setAsesorList(List<Asesor> asesorList) {
        this.asesorList = asesorList;
    }

    @XmlTransient
    public List<Juradoproyecto> getJuradoproyectoList() {
        return juradoproyectoList;
    }

    public void setJuradoproyectoList(List<Juradoproyecto> juradoproyectoList) {
        this.juradoproyectoList = juradoproyectoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Proyecto[ id=" + id + " ]";
    }
    
}
