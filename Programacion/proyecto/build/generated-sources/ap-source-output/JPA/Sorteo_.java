package JPA;

import JPA.Solicitud;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-22T11:50:44")
@StaticMetamodel(Sorteo.class)
public class Sorteo_ { 

    public static volatile CollectionAttribute<Sorteo, Solicitud> solicitudCollection;
    public static volatile SingularAttribute<Sorteo, Date> diafin;
    public static volatile SingularAttribute<Sorteo, Date> diasorteo;
    public static volatile SingularAttribute<Sorteo, Short> idsorteo;
    public static volatile SingularAttribute<Sorteo, Date> diainicio;

}