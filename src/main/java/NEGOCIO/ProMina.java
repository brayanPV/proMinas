/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DAO.CarreraJpaController;
import DAO.Conexion;
import DAO.EstudianteJpaController;
import DTO.Carrera;
import DTO.Estudiante;
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

    private Date crearFecha(String fecha) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date date = formatter.parse(fecha);
        return date;
    }

    public Estudiante findEstudiante(String codigo) {
        return estudianteDAO.findEstudiante(codigo);
    }
    
    public Carrera findCarrera(String codigo) {
        return carDAO.findCarrera(codigo);
    }
}
