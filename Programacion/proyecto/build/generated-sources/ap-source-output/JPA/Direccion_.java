package JPA;

import JPA.Calle;
import JPA.DireccionPK;
import JPA.Menor;
import JPA.Vivienda;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-22T13:15:21")
@StaticMetamodel(Direccion.class)
public class Direccion_ { 

    public static volatile SingularAttribute<Direccion, Vivienda> vivienda;
    public static volatile SingularAttribute<Direccion, DireccionPK> direccionPK;
    public static volatile SingularAttribute<Direccion, Calle> calle;
    public static volatile CollectionAttribute<Direccion, Menor> menorCollection;
    public static volatile SingularAttribute<Direccion, String> cp;

}