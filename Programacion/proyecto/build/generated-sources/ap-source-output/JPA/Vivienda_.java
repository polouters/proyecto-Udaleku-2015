package JPA;

import JPA.Direccion;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-22T11:50:44")
@StaticMetamodel(Vivienda.class)
public class Vivienda_ { 

    public static volatile SingularAttribute<Vivienda, Short> idvivienda;
    public static volatile SingularAttribute<Vivienda, String> piso;
    public static volatile SingularAttribute<Vivienda, String> numero;
    public static volatile SingularAttribute<Vivienda, String> mano;
    public static volatile SingularAttribute<Vivienda, String> letra;
    public static volatile CollectionAttribute<Vivienda, Direccion> direccionCollection;

}