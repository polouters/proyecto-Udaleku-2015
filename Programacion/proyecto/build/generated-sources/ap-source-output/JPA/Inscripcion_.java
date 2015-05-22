package JPA;

import JPA.Menor;
import JPA.Solicitud;
import JPA.Tutor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-22T13:15:21")
@StaticMetamodel(Inscripcion.class)
public class Inscripcion_ { 

    public static volatile SingularAttribute<Inscripcion, Short> idins;
    public static volatile SingularAttribute<Inscripcion, Solicitud> nsolicitud;
    public static volatile SingularAttribute<Inscripcion, Menor> codmenor;
    public static volatile SingularAttribute<Inscripcion, Tutor> dni;

}