package DTO;

import DTO.Carrera;
import DTO.Estudianteproyecto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-27T16:55:19")
@StaticMetamodel(Estudiante.class)
public class Estudiante_ { 

    public static volatile ListAttribute<Estudiante, Estudianteproyecto> estudianteproyectoList;
    public static volatile SingularAttribute<Estudiante, String> codigo;
    public static volatile SingularAttribute<Estudiante, Date> fechanacimiento;
    public static volatile SingularAttribute<Estudiante, String> apellido;
    public static volatile SingularAttribute<Estudiante, Boolean> genero;
    public static volatile SingularAttribute<Estudiante, Carrera> carrera;
    public static volatile SingularAttribute<Estudiante, String> nombre;

}