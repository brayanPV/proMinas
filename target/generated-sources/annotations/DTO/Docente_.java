package DTO;

import DTO.Asesor;
import DTO.Carrera;
import DTO.Juradoanteproyecto;
import DTO.Juradoproyecto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-26T22:37:47")
@StaticMetamodel(Docente.class)
public class Docente_ { 

    public static volatile SingularAttribute<Docente, String> codigo;
    public static volatile SingularAttribute<Docente, Date> fechanacimiento;
    public static volatile SingularAttribute<Docente, String> apellido;
    public static volatile SingularAttribute<Docente, Boolean> genero;
    public static volatile ListAttribute<Docente, Asesor> asesorList;
    public static volatile SingularAttribute<Docente, Carrera> carrera;
    public static volatile SingularAttribute<Docente, String> nombre;
    public static volatile ListAttribute<Docente, Juradoanteproyecto> juradoanteproyectoList;
    public static volatile ListAttribute<Docente, Juradoproyecto> juradoproyectoList;

}