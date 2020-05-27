package DTO;

import DTO.AsesorPK;
import DTO.Docente;
import DTO.Proyecto;
import DTO.Tipoasesor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-26T22:37:47")
@StaticMetamodel(Asesor.class)
public class Asesor_ { 

    public static volatile SingularAttribute<Asesor, Tipoasesor> tipo;
    public static volatile SingularAttribute<Asesor, Docente> docente1;
    public static volatile SingularAttribute<Asesor, AsesorPK> asesorPK;
    public static volatile SingularAttribute<Asesor, Proyecto> proyecto1;

}