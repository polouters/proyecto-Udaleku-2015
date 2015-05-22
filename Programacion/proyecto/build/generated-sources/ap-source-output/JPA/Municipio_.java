package JPA;

import JPA.Calle;
import JPA.Provincia;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-22T11:50:44")
@StaticMetamodel(Municipio.class)
public class Municipio_ { 

    public static volatile SingularAttribute<Municipio, Provincia> idprov;
    public static volatile SingularAttribute<Municipio, Short> idmunicipio;
    public static volatile CollectionAttribute<Municipio, Calle> calleCollection;
    public static volatile SingularAttribute<Municipio, String> nombre;

}