/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DAO.Conexion;
import DAO.EstudianteJpaController;
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

    public ProMina() {
    }

    public boolean registrarEstudiante(String codigo, String nombre, String apellido, String carrera, String fechaNacimiento, boolean genero) {
        boolean exito = false;

        return exito;
    }
    
    private Date crearFecha(String fecha) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date date = formatter.parse(fecha);
        return date;
    }
}
