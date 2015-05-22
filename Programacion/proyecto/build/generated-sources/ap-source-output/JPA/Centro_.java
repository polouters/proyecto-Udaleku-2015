package JPA;

import JPA.Menor;
import JPA.Provincia;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-22T13:15:21")
@StaticMetamodel(Centro.class)
public class Centro_ { 

    public static volatile SingularAttribute<Centro, Provincia> idprov;
    public static volatile CollectionAttribute<Centro, Menor> menorCollection;
    public static volatile SingularAttribute<Centro, Short> idcentro;
    public static volatile SingularAttribute<Centro, String> nombre;
    public static volatile SingularAttribute<Centro, String> modelo;

}