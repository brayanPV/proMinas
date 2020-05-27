package DTO;

import DTO.Docente;
import DTO.Estudiante;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-26T22:37:48")
@StaticMetamodel(Carrera.class)
public class Carrera_ { 

    public static volatile SingularAttribute<Carrera, String> codigo;
    public static volatile ListAttribute<Carrera, Estudiante> estudianteList;
    public static volatile SingularAttribute<Carrera, Integer> credito;
    public static volatile SingularAttribute<Carrera, Integer> semestre;
    public static volatile SingularAttribute<Carrera, String> nombre;
    public static volatile ListAttribute<Carrera, Docente> docenteList;

}