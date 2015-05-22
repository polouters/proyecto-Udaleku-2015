package JPA;

import JPA.Centro;
import JPA.Direccion;
import JPA.Inscripcion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-22T11:50:44")
@StaticMetamodel(Menor.class)
public class Menor_ { 

    public static volatile SingularAttribute<Menor, String> ape2;
    public static volatile SingularAttribute<Menor, String> ape1;
    public static volatile SingularAttribute<Menor, Date> fechanac;
    public static volatile SingularAttribute<Menor, String> discapacidad;
    public static volatile SingularAttribute<Menor, Direccion> direccion;
    public static volatile SingularAttribute<Menor, String> sexo;
    public static volatile SingularAttribute<Menor, Short> codmenor;
    public static volatile SingularAttribute<Menor, String> nombre;
    public static volatile SingularAttribute<Menor, Centro> idcentro;
    public static volatile SingularAttribute<Menor, String> dni;
    public static volatile CollectionAttribute<Menor, Inscripcion> inscripcionCollection;

}