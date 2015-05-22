package JPA;

import JPA.Centro;
import JPA.Municipio;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-22T11:50:44")
@StaticMetamodel(Provincia.class)
public class Provincia_ { 

    public static volatile CollectionAttribute<Provincia, Municipio> municipioCollection;
    public static volatile CollectionAttribute<Provincia, Centro> centroCollection;
    public static volatile SingularAttribute<Provincia, Short> idprov;
    public static volatile SingularAttribute<Provincia, String> nombre;

}