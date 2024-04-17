/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany._libreria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author gian
 */
public class ScaffaleTest {
    
    public ScaffaleTest() {
    }

    /**
     * Test of setLibro method, of class Scaffale.
     */
    @Test
    public void testSetLibro() throws Exception 
    {
        Scaffale s1=new Scaffale();
        Libro atteso=new Libro("A","B",12);
        s1.setLibro(atteso, 0, 0);
        Libro attuale;
        attuale=s1.getLibro(0, 0);
        assertEquals(atteso,attuale,"Get libro A,B,!2 pagine");
    }

   
}
