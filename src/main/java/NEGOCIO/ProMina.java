/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DAO.CarreraJpaController;
import DAO.Conexion;
import DAO.DocenteJpaController;
import DAO.EstudianteJpaController;
import DAO.ProyectoJpaController;
import DTO.Carrera;
import DTO.Docente;
import DTO.Estudiante;
import DTO.Proyecto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author stive
 */
public class ProMina {

    Conexion con = Conexion.getConexion();
    EstudianteJpaController estudianteDAO = new EstudianteJpaController(con.getBd());
    List<Estudiante> estudiantes = estudianteDAO.findEstudianteEntities();
    CarreraJpaController carDAO = new CarreraJpaController(con.getBd());
    List<Carrera> carreras = carDAO.findCarreraEntities();
    DocenteJpaController doDAO = new DocenteJpaController(con.getBd());
    List<Docente> docentes = doDAO.findDocenteEntities();
    ProyectoJpaController proyectoDAO = new ProyectoJpaController(con.getBd());
    List<Proyecto> proyectos = proyectoDAO.findProyectoEntities();

    public ProMina() {
    }

    public boolean registrarEstudiante(String codigo, String nombre, String apellido, String carrera, String fechaNacimiento, boolean genero) throws ParseException {
        boolean exito = false;
        Estudiante e = new Estudiante();
        e = findEstudiante(codigo);
        if (e == null) {
            Estudiante es = new Estudiante();
            es.setCodigo(codigo);
            es.setNombre(nombre);
            es.setApellido(apellido);
            Carrera ca = new Carrera();
            ca = findCarrera(carrera);
            es.setCarrera(ca);
            es.setFechanacimiento(crearFecha(fechaNacimiento));
            es.setGenero(genero);
            try {
                estudianteDAO.create(es);
                exito = true;
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        }
        return exito;
    }

    public boolean registrarDocente(String codigo, String nombre, String apellido, String carrera, String fechaNacimiento, boolean genero) throws ParseException {
        boolean exito = false;
        Docente d = new Docente();
        d = findDocente(codigo);
        if (d == null) {
            Docente doc = new Docente();
            doc.setCodigo(codigo);
            doc.setNombre(nombre);
            doc.setApellido(apellido);
            Carrera ca = new Carrera();
            ca = findCarrera(carrera);
            doc.setCarrera(ca);
            doc.setFechanacimiento(crearFecha(fechaNacimiento));
            doc.setGenero(genero);
            try {
                doDAO.create(doc);
                exito = true;
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        }
        return exito;
    }

    public boolean registrarCarrera(String codigo, String nombre, int credito, int semestre) {
        boolean exito = false;
        Carrera c = new Carrera();
        c = findCarrera(codigo);
        if (c == null) {
            Carrera ca = new Carrera();
            ca.setCodigo(codigo);
            ca.setNombre(nombre);
            ca.setCredito(credito);
            ca.setSemestre(semestre);
            try {
                carDAO.create(ca);
                exito = true;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return exito;
    }

    public boolean registrarProyecto(String director, String titulo, String fechaInicio, String fechaFin, String resumen) throws ParseException {
        boolean exito = false;
        Proyecto p = new Proyecto();
        p = findProyectoNombre(titulo);
        if (p == null) {
            Proyecto pro = new Proyecto();
            pro.setTitulo(titulo);
            pro.setResumen(resumen);
            Docente d = new Docente();
            d = findDocente(director);
            pro.setFechainicio(crearFecha(fechaInicio));
            pro.setFechafin(crearFecha(fechaFin));
            try {
                proyectoDAO.create(pro);
                exito = true;
            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }
        }
        return exito;
    }

    private Date crearFecha(String fecha) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date date = formatter.parse(fecha);
        return date;
    }

    public Estudiante findEstudiante(String codigo) {
        return estudianteDAO.findEstudiante(codigo);
    }

    public Docente findDocente(String codigo) {
        return doDAO.findDocente(codigo);
    }

    public Carrera findCarrera(String codigo) {
        return carDAO.findCarrera(codigo);
    }

    public Proyecto findProyectoNombre(String titulo) {
        for (Proyecto p : proyectos) {
            if (p.getTitulo().equalsIgnoreCase(titulo)) {
                return p;
            }
        }
        return null;
    }

}
