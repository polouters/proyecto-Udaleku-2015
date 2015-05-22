package JPA;

import JPA.Inscripcion;
import JPA.Sorteo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-22T13:15:21")
@StaticMetamodel(Solicitud.class)
public class Solicitud_ { 

    public static volatile SingularAttribute<Solicitud, Date> fecha;
    public static volatile SingularAttribute<Solicitud, Date> hora;
    public static volatile SingularAttribute<Solicitud, Short> nsolicitud;
    public static volatile SingularAttribute<Solicitud, String> situacion;
    public static volatile SingularAttribute<Solicitud, Short> norden;
    public static volatile SingularAttribute<Solicitud, Sorteo> idsorteo;
    public static volatile CollectionAttribute<Solicitud, Inscripcion> inscripcionCollection;

}