/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uml.direccion;

/**
 *
 * @author jon
 */
public class direccionBDTest {
    
    public direccionBDTest() {
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
     * Test of AñadirDireccion method, of class direccionBD.
     */
    @Test
    public void testAñadirDireccion() {
        System.out.println("A\u00f1adirDireccion");
        direccion drc = null;
        int idVivienda = 0;
        int expResult = 0;
        int result = direccionBD.AñadirDireccion(drc, idVivienda);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
