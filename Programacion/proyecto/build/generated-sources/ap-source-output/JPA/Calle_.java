package JPA;

import JPA.Direccion;
import JPA.Municipio;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-22T11:50:44")
@StaticMetamodel(Calle.class)
public class Calle_ { 

    public static volatile CollectionAttribute<Calle, Direccion> direccionCollection;
    public static volatile SingularAttribute<Calle, Municipio> idmunicipio;
    public static volatile SingularAttribute<Calle, String> nombre;
    public static volatile SingularAttribute<Calle, Short> idcalle;

}