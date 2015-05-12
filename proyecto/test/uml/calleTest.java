/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uml;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ruben
 */
public class calleTest {
    
    public calleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getNombre method, of class calle.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        calle instance = new calle();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNombre method, of class calle.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "";
        calle instance = new calle();
        instance.setNombre(nombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getlMunicipio method, of class calle.
     */
    @Test
    public void testGetlMunicipio() {
        System.out.println("getlMunicipio");
        calle instance = new calle();
        ArrayList<municipio> expResult = null;
        ArrayList<municipio> result = instance.getlMunicipio();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setlMunicipio method, of class calle.
     */
    @Test
    public void testSetlMunicipio() {
        System.out.println("setlMunicipio");
        ArrayList<municipio> lMunicipio = null;
        calle instance = new calle();
        instance.setlMunicipio(lMunicipio);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getlDireccion method, of class calle.
     */
    @Test
    public void testGetlDireccion() {
        System.out.println("getlDireccion");
        calle instance = new calle();
        ArrayList<direccion> expResult = null;
        ArrayList<direccion> result = instance.getlDireccion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setlDireccion method, of class calle.
     */
    @Test
    public void testSetlDireccion() {
        System.out.println("setlDireccion");
        ArrayList<direccion> lDireccion = null;
        calle instance = new calle();
        instance.setlDireccion(lDireccion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
