/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany._libreria;

import eccezioni.EccezionePosizioneNonValida;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author gian
 */
public class MensolaTest 
{
    
    public MensolaTest() {
    }

    Libro l1=new Libro("libro1","autore1",100);
    Libro l2=new Libro("libro2","autore2",200);
    Mensola m1;
    /**
     * Test of setVolume method, of class Mensola.
     */
    
    @BeforeEach
    public void istanzioMensola()
    {
        m1=new Mensola();
    }
    
    @Test
    public void testCostruttore()
    {
        assertEquals(0, m1.getNumVolumi());
    }
    
    
    @Test
    public void testSetVolumeGetVolumePosizioneValida() throws Exception 
    {
        m1.setVolume(l1, 0);
        assertEquals(l1, m1.getVolume(0));    
    }

    @Test
    public void testSetVolumePosizioneNegativa()
    {
       EccezionePosizioneNonValida eccezione = assertThrows(EccezionePosizioneNonValida.class, () ->
            m1.setVolume(l1, -1));
        assertEquals("Posizione non valida", eccezione.toString());
   
    }
    
    
}
