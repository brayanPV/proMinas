package DTO;

import DTO.Asesor;
import DTO.Estudianteproyecto;
import DTO.Juradoanteproyecto;
import DTO.Juradoproyecto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-27T12:01:14")
@StaticMetamodel(Proyecto.class)
public class Proyecto_ { 

    public static volatile SingularAttribute<Proyecto, Date> fechainicio;
    public static volatile ListAttribute<Proyecto, Estudianteproyecto> estudianteproyectoList;
    public static volatile SingularAttribute<Proyecto, String> director;
    public static volatile ListAttribute<Proyecto, Asesor> asesorList;
    public static volatile SingularAttribute<Proyecto, String> titulo;
    public static volatile SingularAttribute<Proyecto, Date> fechafin;
    public static volatile SingularAttribute<Proyecto, String> resumen;
    public static volatile SingularAttribute<Proyecto, Integer> id;
    public static volatile SingularAttribute<Proyecto, Date> fechaanteproyecto;
    public static volatile ListAttribute<Proyecto, Juradoanteproyecto> juradoanteproyectoList;
    public static volatile SingularAttribute<Proyecto, Date> fechaproyecto;
    public static volatile ListAttribute<Proyecto, Juradoproyecto> juradoproyectoList;

}