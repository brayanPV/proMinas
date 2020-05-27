package DTO;

import DTO.Docente;
import DTO.JuradoproyectoPK;
import DTO.Proyecto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-27T12:01:13")
@StaticMetamodel(Juradoproyecto.class)
public class Juradoproyecto_ { 

    public static volatile SingularAttribute<Juradoproyecto, Date> fechaasignacion;
    public static volatile SingularAttribute<Juradoproyecto, JuradoproyectoPK> juradoproyectoPK;
    public static volatile SingularAttribute<Juradoproyecto, Docente> docente1;
    public static volatile SingularAttribute<Juradoproyecto, Integer> nota;
    public static volatile SingularAttribute<Juradoproyecto, Date> fechanota;
    public static volatile SingularAttribute<Juradoproyecto, String> observacion;
    public static volatile SingularAttribute<Juradoproyecto, Proyecto> proyecto1;

}