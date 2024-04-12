/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany._libreria;

import eccezioni.EccezionePosizioneNonValida;
import eccezioni.EccezionePosizioneOccupata;
import eccezioni.EccezionePosizioneVuota;
import eccezioni.EccezioneRipianoNonValido;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author gian
 */
public class ScaffaleTest 
{
    public static Libro l1,l2;
    
    
    public ScaffaleTest()
    {
        
    }

    @BeforeAll
    public  static void istanziaLibri()
    {
        l1=new Libro("libro1","autore1",100);
        l2=new Libro("libro2","autore2",200);
    }
    
    
    @Test //Costruttore
    public void testCostruttore() throws EccezioneRipianoNonValido
    {
        Scaffale s1=new Scaffale();
        assertTrue(s1.getNumRipiani()==5 && s1.getNumLibri(0)==0 && s1.getNumLibri(1)==0 && s1.getNumLibri(2)==0 && s1.getNumLibri(3)==0 && s1.getNumLibri(4)==0);
    }
    
    @Test 
    public void testSetLibroGetLibroCorretti() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido, EccezionePosizioneVuota
    {
        Scaffale s1=new Scaffale();
        s1.setLibro(l1, 1, 0);
        Libro l3=s1.getLibro(1, 0);
        assertEquals(l1, l3);
    }
   
    
}
