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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "docente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docente.findAll", query = "SELECT d FROM Docente d")
    , @NamedQuery(name = "Docente.findByCodigo", query = "SELECT d FROM Docente d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "Docente.findByNombre", query = "SELECT d FROM Docente d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "Docente.findByApellido", query = "SELECT d FROM Docente d WHERE d.apellido = :apellido")
    , @NamedQuery(name = "Docente.findByFechanacimiento", query = "SELECT d FROM Docente d WHERE d.fechanacimiento = :fechanacimiento")
    , @NamedQuery(name = "Docente.findByGenero", query = "SELECT d FROM Docente d WHERE d.genero = :genero")})
public class Docente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "genero")
    private boolean genero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "docente1")
    private List<Juradoanteproyecto> juradoanteproyectoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "docente1")
    private List<Asesor> asesorList;
    @JoinColumn(name = "carrera", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Carrera carrera;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "docente1")
    private List<Juradoproyecto> juradoproyectoList;

    public Docente() {
    }

    public Docente(String codigo) {
        this.codigo = codigo;
    }

    public Docente(String codigo, String nombre, String apellido, Date fechanacimiento, boolean genero) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechanacimiento = fechanacimiento;
        this.genero = genero;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public boolean getGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
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

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
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
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docente)) {
            return false;
        }
        Docente other = (Docente) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Docente[ codigo=" + codigo + " ]";
    }
    
}
