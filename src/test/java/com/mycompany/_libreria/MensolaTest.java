/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany._libreria;

import eccezioni.EccezionePosizioneNonValida;
import eccezioni.EccezionePosizioneOccupata;
import eccezioni.EccezionePosizioneVuota;
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
    
    @Test
    public void testSetVolumePosizioneMaggioreDelMassimo()
    {
       EccezionePosizioneNonValida eccezione = assertThrows(EccezionePosizioneNonValida.class, () ->
            m1.setVolume(l1, 15));
        assertEquals("Posizione non valida", eccezione.toString());
    }
    
    @Test
    public void testSetVolumePosizioneOccupata() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida
    {
       m1.setVolume(l1, 10);
       EccezionePosizioneOccupata eccezione = assertThrows(EccezionePosizioneOccupata.class, () ->
            m1.setVolume(l2, 10));
        assertEquals("Posizione occupata", eccezione.toString());
    }
    
    @Test
    public void testGetVolumePosizioneNegativa() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida
    {
       m1.setVolume(l1, 10);
       EccezionePosizioneNonValida eccezione = assertThrows(EccezionePosizioneNonValida.class, () ->
            m1.getVolume(-1));
        assertEquals("Posizione non valida", eccezione.toString());
    }
    
    @Test
    public void testGetVolumePosizioneMaggioreDelMassimo() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida
    {
       m1.setVolume(l1, 10);
       EccezionePosizioneNonValida eccezione = assertThrows(EccezionePosizioneNonValida.class, () ->
            m1.getVolume(15));
        assertEquals("Posizione non valida", eccezione.toString());
    }
    
    @Test
    public void testGetVolumePosizioneVuota() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida
    {
       m1.setVolume(l1, 10);
       EccezionePosizioneVuota eccezione = assertThrows(EccezionePosizioneVuota.class, () ->
            m1.getVolume(0));
        assertEquals("Posizione vuota", eccezione.toString());
    }
    
    @Test
    public void testRimuoviVolumeCorrettamente() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezionePosizioneVuota
    {
       m1.setVolume(l1, 0);
       m1.rimuoviVolume(0);
       EccezionePosizioneVuota eccezione = assertThrows(EccezionePosizioneVuota.class, () ->
           l1=m1.getVolume(0));
        assertEquals("Posizione vuota", eccezione.toString());
    }
    
    @Test
    public void testRimuoviVolumePosizioneNegativa() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida 
    {
       m1.setVolume(l1, 0);
       EccezionePosizioneNonValida eccezione = assertThrows(EccezionePosizioneNonValida.class, () ->
            m1.rimuoviVolume(-1));
        assertEquals("Posizione non valida", eccezione.toString());
    }
    
    @Test
    public void testRimuoviVolumePosizioneMaggioreDelMassimo() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida 
    {
       m1.setVolume(l1, 0);
       EccezionePosizioneNonValida eccezione = assertThrows(EccezionePosizioneNonValida.class, () ->
            m1.rimuoviVolume(15));
        assertEquals("Posizione non valida", eccezione.toString());
    }
    
    @Test
    public void testRimuoviVolumePosizioneVuota() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida 
    {
       
       EccezionePosizioneVuota eccezione = assertThrows(EccezionePosizioneVuota.class, () ->
            m1.rimuoviVolume(0));
        assertEquals("Posizione vuota", eccezione.toString());
    }
    
    
    @Test
    public void testGetNumMaxVolumi() throws Exception 
    {
        assertEquals(15, m1.getNumMaxVolumi());    
    }
    
    @Test
    public void testGetNumVolumi() throws Exception 
    {
        m1.setVolume(l1, 0);
        m1.setVolume(l2, 5);
        assertEquals(2, m1.getNumVolumi());    
    }
    
    @Test
    public void testPresenzaTitoloPresente() throws Exception 
    {
        m1.setVolume(l1, 0);
        m1.setVolume(l2, 5);
        assertTrue(m1.presenzaTitolo(l2.getTitolo()));    
    }
    
    @Test
    public void testPresenzaTitoloNonPresente() throws Exception 
    {
        m1.setVolume(l1, 0);
        m1.setVolume(l2, 5);
        assertFalse(m1.presenzaTitolo("Titolo che non esiste"));    
    }
    
    @Test
    public void testToString() throws Exception 
    {
        m1.setVolume(l1, 0);
        m1.setVolume(l2, 5);
        assertEquals("\nPosizione: 0\t\nTitolo: libro1\nAutore:autore1\nNumero pagine=100\n\nPosizione: 5\t\nTitolo: libro2\nAutore:autore2\nNumero pagine=200\n",m1.toString());    
    }
    
    @Test
    public void testCostruttoreCopiaEMetodoEqualsConCopiaUguale() throws Exception 
    {
        m1.setVolume(l1, 0);
        m1.setVolume(l2, 5);
        Mensola m2=new Mensola(m1);
        assertEquals(m1,m2);
    }
    
    @Test
    public void testCostruttoreCopia_MetodoEquals_indipendenzaMensole_ConCopiaNonUguale() throws Exception 
    {
        m1.setVolume(l1, 0);
        m1.setVolume(l2, 5);
        Mensola m2=new Mensola(m1);
        m2.rimuoviVolume(0);
        assertFalse(m1.equals(m2));
    }
    
     @Test
    public void testMetodoEqualsNumeroLibriUgualeInPosizioniDiverse() throws Exception 
    {
        m1.setVolume(l1, 0);
        Mensola m2=new Mensola();
        m2.setVolume(l1, 1);
        assertFalse(m1.equals(m2));
    }
    
    @Test
    public void testMetodoEqualsLibriDiversiInPosizioniUguali() throws Exception 
    {
        m1.setVolume(l1, 1);
        Mensola m2=new Mensola();
        m2.setVolume(l2, 1);
        assertFalse(m1.equals(m2));
    }
}
