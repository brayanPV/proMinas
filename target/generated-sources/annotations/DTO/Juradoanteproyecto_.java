package DTO;

import DTO.Docente;
import DTO.JuradoanteproyectoPK;
import DTO.Proyecto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-27T16:55:19")
@StaticMetamodel(Juradoanteproyecto.class)
public class Juradoanteproyecto_ { 

    public static volatile SingularAttribute<Juradoanteproyecto, Date> fechaasignacion;
    public static volatile SingularAttribute<Juradoanteproyecto, Docente> docente1;
    public static volatile SingularAttribute<Juradoanteproyecto, JuradoanteproyectoPK> juradoanteproyectoPK;
    public static volatile SingularAttribute<Juradoanteproyecto, Integer> nota;
    public static volatile SingularAttribute<Juradoanteproyecto, Date> fechanota;
    public static volatile SingularAttribute<Juradoanteproyecto, String> observacion;
    public static volatile SingularAttribute<Juradoanteproyecto, Proyecto> proyecto1;

}